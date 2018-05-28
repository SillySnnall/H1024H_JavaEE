package silly.h1024h.service.impl

import javax.servlet.http.HttpServletRequest

interface HotCountServiceImpl {

    /**
     * 热门计数，统计每一个类别的点击次数
     */
    fun hotCount(model: ImgRes):Boolean
}