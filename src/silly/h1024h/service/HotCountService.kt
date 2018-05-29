package silly.h1024h.service

import silly.h1024h.dao.ResDataDao
import silly.h1024h.service.impl.HotCountServiceImpl

class HotCountService : HotCountServiceImpl {

    private val imgResDao = ResDataDao()

    override fun hotCount(model: ImgRes): Boolean {
        val findByIrCoverIrType = imgResDao.findByIrCoverIrType(1, model.irType)
        if (findByIrCoverIrType.isEmpty()) return false
        for (irCoverIrType in findByIrCoverIrType) {
            irCoverIrType.irHotCount = ++irCoverIrType.irHotCount
        }
        return imgResDao.saveImg(findByIrCoverIrType)
    }

}