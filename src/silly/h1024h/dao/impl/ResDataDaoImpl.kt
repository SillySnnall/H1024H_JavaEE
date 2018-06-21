package silly.h1024h.dao.impl

import silly.h1024h.entity.ResData

interface ResDataDaoImpl {

    /**
     * 创建Res资源表
     */
    fun createResTable(table: String)

    /**
     * 添加Res数据
     */
    fun addResData(table: String, params: Array<Any>)

    /**
     * 分页查找封面
     */
    fun findCover(table: String, pageNum: Int, itemCount: Int): List<ResData>

    /**
     * 分页查找详情
     */
    fun findDetails(table: String, type: String, pageNum: Int, itemCount: Int): List<ResData>

    /**
     * 根据type查找封面
     */
    fun findByTypeCover(table: String, type: String): ResData
}