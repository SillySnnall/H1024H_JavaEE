package silly.h1024h.dao.impl

import silly.h1024h.entity.HotData


interface HotDaoImpl {

    /**
     * 添加热门原始数据
     */
    fun addHotData(params: Array<Any>)

    /**
     * 热门数据计数
     */
    fun countHot(type: String):Boolean

    /**
     * 排序获取热门数据
     */
    fun getSortHotData(pageNum: Int, itemCount: Int): List<HotData>
}