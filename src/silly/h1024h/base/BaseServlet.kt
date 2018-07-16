package silly.h1024h.base

import com.google.gson.Gson
import org.apache.commons.beanutils.BeanUtils
import silly.h1024h.utils.DesUtil
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.lang.reflect.InvocationTargetException

@WebServlet(name = "BaseServlet")
abstract class BaseServlet<T> : HttpServlet() {

    lateinit var request: HttpServletRequest
    lateinit var response: HttpServletResponse

    abstract fun getModel(): T?

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            config(request, response)
            System.out.println("----------Start----------")
            System.out.println("URL:${request.requestURL}")
            val sign = request.getParameter("sign") ?: ""
            val timestamp = request.getParameter("timestamp") ?: ""
            val map = decryptData(sign, timestamp)// 解密
            System.out.println("----------End----------")



            if (getModel() != null) {
                val model = getModel()
                BeanUtils.populate(model, map)
                doWork(request, response, model)
            } else {
                doWork(request, response, null)
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

    }

    /**
     * 解密数据
     */
    private fun decryptData(sign: String, timestamp: String): Map<String, String> {
        val map = mutableMapOf<String, String>()
        if (sign.isNotEmpty() && timestamp.isNotEmpty()) {
            var decrypt = DesUtil.decrypt(sign)
            decrypt = DesUtil.decrypt(decrypt, timestamp)
            val split = decrypt.split(",")
            for (kvs in split) {
                val kv = kvs.split("=")
                map[kv[0]] = kv[1]
                System.out.println("${kv[0]}:${kv[1]}")
            }
        }
        return map
    }

    abstract fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: T?)

    private fun config(request: HttpServletRequest, response: HttpServletResponse) {
        request.characterEncoding = "utf-8"
        response.contentType = "text/json;charset=utf-8"
        response.characterEncoding = "utf-8"
        response.addHeader("Access-Control-Allow-Origin", "*")
        this.request = request
        this.response = response
    }

    /**
     * 返回成功的Map数据
     */
    fun successData(map: Map<String, Any>) {
        val writer = response.writer
        writer.write(DesUtil.encrypt(Gson().toJson(SuccessMap(0, "msgok", map))))
        writer.flush()
        writer.close()
    }

    /**
     * 返回成功的List数据
     */
    fun successData(list: List<Any>) {
        val writer = response.writer
        writer.write(DesUtil.encrypt(Gson().toJson(SuccessDataList(0, "msgok", list))))
        writer.flush()
        writer.close()
    }

    /**
     * 返回成功的数据
     */
    fun successData(str: String) {
        val writer = response.writer
        if (str.isNotEmpty() && (str.substring(0, 1) == "[" || str.substring(0, 1) == "{"))
            writer.write(DesUtil.encrypt("{\"msg\": 0,\"param\": \"msgok\",\"data\": $str}"))
        else
            writer.write(DesUtil.encrypt(Gson().toJson(SuccessData(0, "msgok", str))))
        writer.flush()
        writer.close()
    }


    /**
     * 返回失败的数据
     */
    fun failData(msg: Int, param: String) {
        val writer = response.writer
        writer.write(DesUtil.encrypt(Gson().toJson(FailData(msg, param))))
        writer.flush()
        writer.close()
    }
}
