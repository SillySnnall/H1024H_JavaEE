package silly.h1024h.servlet

import com.google.gson.Gson
import silly.h1024h.base.FailData
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.common.whenAc
import silly.h1024h.utils.DesUtil
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import silly.h1024h.common.config
import silly.h1024h.common.failData

@WebServlet(name = "AppServlet", urlPatterns = ["/app"])
class AppServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            config(request, response)// 配置
            val sign = request.getParameter("sign") ?: ""// 获取加密数据
            val timestamp = request.getParameter("timestamp") ?: ""// 时间戳
            if (sign.isNotEmpty() && timestamp.isNotEmpty()) {
                // app
                System.out.println("----------Start----------")
                System.out.println("URL:${request.requestURL}")
                val map = decryptData(sign, timestamp)// 解密
                System.out.println("-----------End-----------")
                // 接口判断
                val ac = map["ac"] ?: ""
                if (ac.isEmpty()) {
                    failData(response, 1019, ERROR_MAP[1019] ?: "")
                    return
                }
                // 接口选择
                whenAc(map, response)
            } else {
                //TODO web
//                val map = HashMap<String, Any>()
//                val mapParam = request.parameterMap
//                val set = mapParam.keys
//                System.out.println("----------Start----------")
//                System.out.println("URL:${request.requestURL}")
//                for (aSet in set) {
//                    val key = aSet as String
//                    val value = request.getParameter(key)
//                    map[key] = value
//                    System.out.println("$key:$value")
//                }
//                System.out.println("-----------End-----------")
//                whenAc(map, response)
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
}
