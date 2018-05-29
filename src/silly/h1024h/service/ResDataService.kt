package silly.h1024h.service

import silly.h1024h.dao.ResDataDao
import silly.h1024h.service.impl.ResDataServiceImpl

class ResDataService : ResDataServiceImpl {

    private val resDataDao = ResDataDao()
    override fun getTypeList(): String {
        val typeList = resDataDao.findTypeList()
        val builder = StringBuilder().append("[")
        for (type in typeList) {
            builder.append("{\"name\":\"${type.name}\",\"type\":\"${type.type}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")
        return builder.toString()
    }


}