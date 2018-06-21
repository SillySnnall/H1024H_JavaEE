package silly.h1024h.service

import silly.h1024h.dao.TypeListDao
import silly.h1024h.service.impl.TypeListServiceImpl

class TypeListService : TypeListServiceImpl {

    override fun getTypeList(): String {
        val typeList = typeListDao.findTypeList()
        if (typeList.isEmpty()) return ""
        val builder = StringBuilder().append("[")
        for (type in typeList) {
            builder.append("{\"name\":\"${type.name}\",\"type\":\"${type.type}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")
        return builder.toString()
    }

    private val typeListDao = TypeListDao()
}