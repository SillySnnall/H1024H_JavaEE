package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.dao.ResDataDao
import silly.h1024h.dao.UserDao
import silly.h1024h.entity.ResData
import silly.h1024h.entity.User
import silly.h1024h.utils.RedisUtil
import silly.h1024h.utils.Util
import javax.servlet.http.HttpServletResponse

class CoverImgDetailedService(response: HttpServletResponse, map: Map<String, String>) : BaseService<ResData>(response, map, ResData()) {

    override fun isEmpty(): Boolean {
        if (model.table_name.isEmpty() || model.type.isEmpty()) return failData(1018, ERROR_MAP[1018]
                ?: "")
        return true
    }

    override fun mainService(): Boolean {
        // 查询封面详情数据
        val findCover = ResDataDao().findDetails(model.table_name, model.type, model.pageNum, model.itemCount)
        if (findCover.isEmpty()) return successData("[]")
        // 数据组合
        val builder = StringBuilder().append("[")
        for (cover in findCover) {
            builder.append("{\"name\":\"${cover.name}\",\"url\":\"${cover.url}\",\"type\":\"${cover.type}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")

        return successData(builder.toString())
    }
}