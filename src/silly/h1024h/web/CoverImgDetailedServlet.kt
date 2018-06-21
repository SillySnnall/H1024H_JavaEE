package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.entity.ResData
import silly.h1024h.service.ResDataService

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CoverImgDetailedServlet", urlPatterns = ["/get_cover_img_detailed"])
class CoverImgDetailedServlet : BaseServlet<ResData>() {
    private val imgResService = ResDataService()
    override fun getModel(): ResData? {
        return ResData()
    }
    /**
     * 获取指定封面详细数据
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: ResData?) {
        successData(imgResService.getDetails(model!!))
    }
}
