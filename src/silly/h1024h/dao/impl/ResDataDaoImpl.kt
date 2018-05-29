package silly.h1024h.dao.impl

import silly.h1024h.entity.ResData

interface ResDataDaoImpl {

    /**
     * 分类列表查询
     */
    fun findTypeList(): List<ResData>
}