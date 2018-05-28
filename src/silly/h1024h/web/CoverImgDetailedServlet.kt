package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.service.ImgResService

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CoverImgDetailedServlet", urlPatterns = ["/getCoverImgDetailed"])
class CoverImgDetailedServlet : BaseServlet<ImgRes>() {
    private val imgResService = ImgResService()
    override fun getModel(): ImgRes? {
        return ImgRes()
    }
    /**
     * 获取指定封面详细数据
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: ImgRes?) {
        successData(imgResService.getDetailed(model!!))
    }
}
