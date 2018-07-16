package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.dao.TypeListDao
import javax.servlet.http.HttpServletResponse

class TypeListService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<Any>(response, map, Any()) {
    override fun isEmpty(): Boolean {
        return true
    }

    override fun mainService(): Boolean {
        // 获取分类
        val typeList = TypeListDao().findTypeList()
        if (typeList.isEmpty()) return successData("[]")
        // 组合数据
        val builder = StringBuilder().append("[")
        for (type in typeList) {
            builder.append("{\"name\":\"${type.name}\",\"type\":\"${type.type}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")

        return successData(builder.toString())
    }
}