package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.dao.HotDao
import silly.h1024h.dao.ResDataDao
import silly.h1024h.entity.HotData
import silly.h1024h.entity.ResData
import silly.h1024h.entity.User
import javax.servlet.http.HttpServletResponse

class HotCountService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<HotData>(response, map, HotData()) {
    override fun isEmpty(): Boolean {
        if (model.type.isEmpty()) return failData(1018, ERROR_MAP[1018] ?: "")
        return true
    }

    override fun mainService(): Boolean {
        // 计数存入
        if (!HotDao().countHot(model.type)) return failData(1008, ERROR_MAP[1008] ?: "")

        return successData("计数成功")
    }

}