package silly.h1024h.web

import silly.h1024h.base.BaseServlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.service.UpdateService


@WebServlet(name = "UpdateImgServlet", urlPatterns = ["/updateImg"])
class UpdateImgServlet : BaseServlet<Any>() {
    override fun getModel(): Any? {
        return null
    }

    private val updateService = UpdateService()
    /**
     * 上传图片
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: Any?) {
        val imgUrlList = updateService.updateImg(request)
        if (imgUrlList.isEmpty()) failData(ErrorEnumMsg.error1007, ErrorEnumParam.error1007)
        else successData(imgUrlList)
    }
}
