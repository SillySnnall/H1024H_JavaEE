package silly.h1024h.service.impl

import silly.h1024h.entity.ResData
import javax.servlet.http.HttpServletRequest

interface GetHotServiceImpl {
    /**
     * 获取热门数据
     */
    fun getHotData(imgRes: ResData):String
}