package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.service.ImgResService

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "CoverImgServlet", urlPatterns = ["/getCoverImg"])
class CoverImgServlet : BaseServlet<ImgRes>() {
    private val imgResService = ImgResService()
    override fun getModel(): ImgRes? {
        return ImgRes()
    }
    /**
     * 获取封面
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: ImgRes?) {
        successData(imgResService.getCover(model!!))
    }
}
