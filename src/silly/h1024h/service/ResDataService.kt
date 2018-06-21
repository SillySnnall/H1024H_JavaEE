package silly.h1024h.service

import silly.h1024h.dao.ResDataDao
import silly.h1024h.entity.ResData
import silly.h1024h.service.impl.ResDataServiceImpl

class ResDataService : ResDataServiceImpl {
    override fun getDetails(model: ResData): String {
        val findCover = resDataDao.findDetails(model.table_name, model.type, model.pageNum, model.itemCount)
        if (findCover.isEmpty()) return ""
        val builder = StringBuilder().append("[")
        for (cover in findCover) {
            builder.append("{\"name\":\"${cover.name}\",\"url\":\"${cover.url}\",\"type\":\"${cover.type}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")
        return builder.toString()
    }


    override fun getCover(model: ResData): String {
        val findCover = resDataDao.findCover(model.table_name, model.pageNum, model.itemCount)
        if (findCover.isEmpty()) return ""
        val builder = StringBuilder().append("[")
        for (cover in findCover) {
            builder.append("{\"name\":\"${cover.name}\",\"url\":\"${cover.url}\",\"type\":\"${cover.type}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")
        return builder.toString()
    }

    private val resDataDao = ResDataDao()
}