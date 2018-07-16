package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.dao.UrlDataDao
import silly.h1024h.entity.UrlData
import javax.servlet.http.HttpServletResponse

class GetMainUrlService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<UrlData>(response, map, UrlData()) {
    override fun isEmpty(): Boolean {
        if (model.version_code.isEmpty() || model.channel.isEmpty()) return failData(1018, ERROR_MAP[1018]?: "")
        return true
    }

    override fun mainService(): Boolean {
        // 获取app配置信息
        val findByVersionChannel = UrlDataDao().findByVersionChannel(model.version_code, model.channel)
        if (findByVersionChannel.isEmpty()) return failData(1017,ERROR_MAP[1017]?: "")
        return successData("{\"channel\":\"${findByVersionChannel[0].channel}\",\"version_code\":\"${findByVersionChannel[0].version_code}\",\"type\":\"${findByVersionChannel[0].type}\",\"apk_url\":\"${findByVersionChannel[0].apk_url}\"}")
    }

}