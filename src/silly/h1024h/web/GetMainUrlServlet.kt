package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.dao.UrlDataDao
import silly.h1024h.entity.UrlData

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "GetMainUrlServlet", urlPatterns = ["/get_main_url"])
class GetMainUrlServlet : BaseServlet<UrlData>() {
    private val urlDataDao = UrlDataDao()
    override fun getModel(): UrlData? {
        return UrlData()
    }
    /**
     * 获取主地址
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: UrlData?) {
        // 判空
        if(model?.version_code?.isEmpty()!! || model.channel.isEmpty()){
            failData(ErrorEnumMsg.error1018,ErrorEnumParam.error1018)
            return
        }
        val findByVersionChannel = urlDataDao.findByVersionChannel(model.version_code, model.channel)
        if(findByVersionChannel.isEmpty()){
            failData(ErrorEnumMsg.error1017,ErrorEnumParam.error1017)
            return
        }
        successData("{\"channel\":\"${findByVersionChannel[0].channel}\",\"version_code\":\"${findByVersionChannel[0].version_code}\",\"type\":\"${findByVersionChannel[0].type}\",\"apk_url\":\"${findByVersionChannel[0].apk_url}\"}")
    }
}
