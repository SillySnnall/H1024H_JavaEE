package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.entity.ResData
import silly.h1024h.entity.UrlData
import silly.h1024h.service.MianUrlService
import silly.h1024h.service.ResDataService

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "GetMainUrlServlet", urlPatterns = ["/get_main_url"])
class GetMainUrlServlet : BaseServlet<UrlData>() {
    private val mianUrlService = MianUrlService()
    override fun getModel(): UrlData? {
        return UrlData()
    }
    /**
     * 获取封面
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: UrlData?) {
        successData(mianUrlService.getMainUrl(model!!).toString())
    }
}
