package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.service.GetIrDetailsService
import silly.h1024h.service.ImgResService

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

@WebServlet(name = "GetIrDetailsServlet",urlPatterns = ["/getIrDetails"])
class GetIrDetailsServlet : BaseServlet<Any>() {
    private val getIrDetailsService = GetIrDetailsService()

    override fun getModel(): Any? {
       return null
    }

    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: Any?) {
        successData(getIrDetailsService.getIrDetails())
    }
}
