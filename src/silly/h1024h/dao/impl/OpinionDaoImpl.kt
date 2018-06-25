package silly.h1024h.dao.impl

import silly.h1024h.entity.Opinion
import silly.h1024h.entity.User

interface OpinionDaoImpl {
    /**
     * 存储意见
     */
    fun saveOpinion(params: Array<Any>): Boolean

    /**
     * 查询全部意见
     */
    fun findOpinion(): List<Opinion>
}