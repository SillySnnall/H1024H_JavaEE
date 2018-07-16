package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.dao.OpinionDao
import silly.h1024h.entity.Opinion
import silly.h1024h.utils.Util
import javax.servlet.http.HttpServletResponse

class OpinionService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<Opinion>(response, map, Opinion()) {
    override fun isEmpty(): Boolean {
        if (model.content.isEmpty() || model.account.isEmpty()) return failData(1018, ERROR_MAP[1018] ?: "")
        return true
    }

    override fun mainService(): Boolean {
        // 创建时间
        model.create_time = Util.getCurrentDate()
        // 存储数据
        val params = arrayOf<Any>("'${model.account}'", "'${model.content}'", "'${model.create_time}'")
        if (!OpinionDao().saveOpinion(params)) return failData(1013, ERROR_MAP[1013] ?: "")

        return successData("提交成功")
    }


//    fun findOpinion(): String {
//        val opinion = opinionDao.findOpinion()
//        val builder = StringBuilder().append("[")
//        for (data in opinion) {
//            builder.append("{\"name\":\"${data.account}\",\"url\":\"${data.content}\",\"type\":\"${data.create_time}\"},")
//        }
//        builder.deleteCharAt(builder.length - 1).append("]")
//        return builder.toString()
//    }
}