package silly.h1024h.service.impl

import silly.h1024h.entity.UrlData

interface MianUrlServiceImpl {

    /**
     * 获取url
     */
    fun getMainUrl(urlData: UrlData): Int
}