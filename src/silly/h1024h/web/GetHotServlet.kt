package silly.h1024h.web

import silly.h1024h.base.BaseServlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import silly.h1024h.entity.ResData
import silly.h1024h.service.GetHotService


@WebServlet(name = "GetHotServlet", urlPatterns = ["/get_hot"])
class GetHotServlet : BaseServlet<ResData>() {
    override fun getModel(): ResData? {
        return ResData()
    }

    private val getHotService = GetHotService()
    /**
     * 获取热门数据
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: ResData?) {
        successData(getHotService.getHotData(model!!))
    }
}
