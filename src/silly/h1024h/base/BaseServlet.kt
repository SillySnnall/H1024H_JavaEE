package silly.h1024h.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.commons.beanutils.BeanUtils

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import java.util.HashMap

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
            val map = HashMap<String, Any>()
            val mapParam = request.parameterMap
            val set = mapParam.keys
            System.out.println("----------Start----------")
            System.out.println("URL:${request.requestURL}")
            for (aSet in set) {
                val key = aSet as String
                val value = request.getParameter(key)
                map[key] = value
                System.out.println("$key:$value")
            }
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
        writer.write(Gson().toJson(SuccessMap(0, "msgok", map)))
        writer.flush()
        writer.close()
    }

    /**
     * 返回成功的List数据
     */
    fun successData(list: List<Any>) {
        val writer = response.writer
        writer.write(Gson().toJson(SuccessDataList(0, "msgok", list)))
        writer.flush()
        writer.close()
    }

    /**
     * 返回成功的数据
     */
    fun successData(str: String) {
        val writer = response.writer
        writer.write(Gson().toJson(SuccessData(0, "msgok", str)))
        writer.flush()
        writer.close()
    }

    /**
     * 返回失败的数据
     */
    fun failData(msg: Int, param: String) {
        val writer = response.writer
        writer.write(Gson().toJson(FailData(msg, param)))
        writer.flush()
        writer.close()
    }
}
