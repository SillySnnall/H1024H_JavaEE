package silly.h1024h.service

import silly.h1024h.entity.UrlData
import silly.h1024h.service.impl.MianUrlServiceImpl

class MianUrlService : MianUrlServiceImpl {

    /**
     * 0 ->服务器失效
     * 1 ->马甲服务器
     * 2 ->正式服务器
     */
    override fun getMainUrl(urlData: UrlData): Int {
        return if (appNameCheck(urlData.app_name) && appVersionCheck(urlData.app_version) && channelCheck(urlData.channel)) 1 else 2
    }


    private fun appNameCheck(appName: String): Boolean {
        return when (appName) {
            "哈哈" -> true
            "嘻嘻" -> false
            else -> false
        }
    }

    private fun appVersionCheck(appVersion: String): Boolean {
        return when (appVersion) {
            "1.0.3" -> true
            "1.0.4" -> false
            else -> false
        }
    }

    private fun channelCheck(channel: String): Boolean {
        return when (channel) {
            "12054" -> true
            "12520" -> false
            else -> false
        }
    }
}