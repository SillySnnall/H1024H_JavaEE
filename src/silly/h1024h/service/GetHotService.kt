package silly.h1024h.service

import silly.h1024h.dao.HotDao
import silly.h1024h.dao.ResDataDao
import silly.h1024h.entity.ResData
import silly.h1024h.service.impl.GetHotServiceImpl

class GetHotService : GetHotServiceImpl {
    override fun getHotData(imgRes: ResData): String {
        val sortHotData = hotDao.getSortHotData(imgRes.pageNum, imgRes.itemCount)
        val builder = StringBuilder().append("[")
        for (data in sortHotData) {
            val resData = resDataDao.findByTypeCover(data.table_name, data.type)
            builder.append("{\"name\":\"${resData.name}\",\"url\":\"${resData.url}\",\"type\":\"${resData.type}\",\"table_name\":\"${data.table_name}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")
        return builder.toString()
    }

    private val hotDao = HotDao()
    private val resDataDao = ResDataDao()

}