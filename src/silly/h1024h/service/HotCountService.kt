package silly.h1024h.service

import silly.h1024h.dao.HotDao
import silly.h1024h.dao.ResDataDao
import silly.h1024h.entity.HotData
import silly.h1024h.entity.ResData
import silly.h1024h.service.impl.HotCountServiceImpl

class HotCountService : HotCountServiceImpl {

    private val hotDao = HotDao()

    override fun hotCount(model: HotData): Boolean {
        return hotDao.countHot(model.type)
    }

}