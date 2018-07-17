package silly.h1024h.servlet

import silly.h1024h.common.ERROR_MAP
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import silly.h1024h.common.config
import silly.h1024h.common.failData
import silly.h1024h.common.successData
import silly.h1024h.dao.UrlDataDao
import javax.servlet.annotation.WebServlet

@WebServlet(name = "WebServlet", urlPatterns = ["/web"])
class WebServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        doGet(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        config(request, response)// 配置
        val type = request.getParameter("type") ?: ""// 获取加密数据
        val pswd = request.getParameter("pswd") ?: ""// 时间戳
        if ("411600050" != pswd) {
            failData(response, 1002, "密码错误")
            return
        }
        if (UrlDataDao().updateType("1", "app", type)) {
            successData(response, "修改成功")
        } else {
            failData(response, 1002, "修改失败")
        }
    }
}
