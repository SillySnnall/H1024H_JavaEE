package silly.h1024h.common

import com.google.gson.Gson
import silly.h1024h.base.FailData
import silly.h1024h.base.SuccessData
import silly.h1024h.utils.DesUtil
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Servlet相应配置
 */
fun config(request: HttpServletRequest, response: HttpServletResponse) {
    request.characterEncoding = "utf-8"
    response.contentType = "text/json;charset=utf-8"
    response.characterEncoding = "utf-8"
    response.addHeader("Access-Control-Allow-Origin", "*")
}

/**
 * 返回失败的数据
 */
fun failData(response: HttpServletResponse, msg: Int, param: String){
    val writer = response.writer
    val encrypt = Gson().toJson(FailData(msg, param))
    writer.write(encrypt)
    writer.flush()
    writer.close()
}


/**
 * 返回成功的数据
 */
fun successData(response: HttpServletResponse,str: String): Boolean {
    val writer = response.writer
    if (str.isNotEmpty() && (str.substring(0, 1) == "[" || str.substring(0, 1) == "{"))
        writer.write("{\"msg\": 0,\"param\": \"msgok\",\"data\": $str}")
    else
        writer.write(Gson().toJson(SuccessData(0, "msgok", str)))
    writer.flush()
    writer.close()
    return true
}