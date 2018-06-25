package silly.h1024h.web

import silly.h1024h.base.BaseServlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.entity.Opinion
import silly.h1024h.isentity.IsEmptyOpinion
import silly.h1024h.service.OpinionService


@WebServlet(name = "OpinionServlet", urlPatterns = ["/opinion"])
class OpinionServlet : BaseServlet<Opinion>() {
    override fun getModel(): Opinion? {
        return Opinion()
    }

    private val opinionService = OpinionService()
    /**
     * 意见反馈提交
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: Opinion?) {
        // 判空
        val isOpinion = IsEmptyOpinion.isContent(model!!)
        if (isOpinion.isNotEmpty()) {
            failData(ErrorEnumMsg.error1002, isOpinion)
            return
        }
        // 存储数据
        if (opinionService.saveOpinion(model)) successData("提交成功")
        else failData(ErrorEnumMsg.error1013, ErrorEnumParam.error1013)
    }
}
