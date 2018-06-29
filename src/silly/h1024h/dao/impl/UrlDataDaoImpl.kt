package silly.h1024h.dao.impl

import silly.h1024h.entity.UrlData


interface UrlDataDaoImpl {

    /**
     * 获取指定版本号和渠道号的数据
     */
    fun findByVersionChannel(versionCode: String, channel: String): List<UrlData>
}