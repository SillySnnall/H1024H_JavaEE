package silly.h1024h.service.impl

import silly.h1024h.entity.Opinion
import silly.h1024h.entity.ResData
import javax.servlet.http.HttpServletRequest

interface OpinionServiceImpl {
    /**
     * 存储意见
     */
    fun saveOpinion(opinion: Opinion): Boolean

    /**
     * 查询全部意见
     */
    fun findOpinion():String
}