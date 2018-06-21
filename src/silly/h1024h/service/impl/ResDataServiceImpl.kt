package silly.h1024h.service.impl

import silly.h1024h.entity.ResData

interface ResDataServiceImpl {

    /**
     * 获取封面
     */
    fun getCover(model: ResData): String

    /**
     * 获取详情
     */
    fun getDetails(model: ResData): String
}