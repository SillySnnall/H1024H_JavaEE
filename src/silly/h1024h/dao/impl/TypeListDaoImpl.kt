package silly.h1024h.dao.impl

import silly.h1024h.entity.TypeList


interface TypeListDaoImpl {

    /**
     * 分类列表查询
     */
    fun findTypeList(): List<TypeList>

    /**
     * 添加分类列表数据
     */
    fun addTypeListData(params: Array<Any>)
}