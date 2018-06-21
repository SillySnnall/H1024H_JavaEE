package silly.h1024h.web

import silly.h1024h.base.BaseServlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.entity.HotData
import silly.h1024h.entity.ResData
import silly.h1024h.service.HotCountService


@WebServlet(name = "HotCountServlet", urlPatterns = ["/hot_count"])
class HotCountServlet : BaseServlet<HotData>() {
    override fun getModel(): HotData? {
        return HotData()
    }

    private val hotCountService = HotCountService()
    /**
     * 计数
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: HotData?) {
        val hotCount = hotCountService.hotCount(model!!)
        if(hotCount) successData("计数成功") else failData(ErrorEnumMsg.error1008, ErrorEnumParam.error1008)
    }
}
