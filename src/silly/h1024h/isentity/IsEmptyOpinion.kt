package silly.h1024h.isentity

import silly.h1024h.entity.Opinion
import silly.h1024h.entity.ResData

/**
 * User字段判空类
 */
object IsEmptyOpinion {

    fun isContent(opinion: Opinion): String {
        if (opinion.content.isNullOrEmpty()) return "内容不能为空"
        return ""
    }
}