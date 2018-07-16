package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.dao.HotDao
import silly.h1024h.dao.ResDataDao
import silly.h1024h.entity.ResData
import javax.servlet.http.HttpServletResponse

class GetHotService(response: HttpServletResponse, map: Map<String, String>) : BaseService<ResData>(response, map, ResData()) {
    override fun isEmpty(): Boolean {
        return true
    }

    override fun mainService(): Boolean {
        // 获取热门数据
        val sortHotData = HotDao().getSortHotData(model.pageNum, model.itemCount)
        if (sortHotData.isEmpty()) return successData("[]")
        // 数据组合
        val builder = StringBuilder().append("[")
        for (data in sortHotData) {
            val resData = ResDataDao().findByTypeCover(data.table_name, data.type)
            builder.append("{\"name\":\"${resData.name}\",\"url\":\"${resData.url}\",\"type\":\"${resData.type}\",\"table_name\":\"${data.table_name}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")

        return successData(builder.toString())
    }
}