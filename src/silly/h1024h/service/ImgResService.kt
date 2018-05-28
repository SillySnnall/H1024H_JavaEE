package silly.h1024h.service

import silly.h1024h.dao.ResDataDataDao
import silly.h1024h.service.impl.ImgResServiceImpl

class ImgResService : ImgResServiceImpl {
    private val imgResDao = ResDataDataDao()
    override fun getCover(imgRes: ImgRes): List<ImgRes> {
        return imgResDao.getCover(imgRes.pageNum, imgRes.itemCount)
    }

    override fun getDetailed(imgRes: ImgRes): List<ImgRes> {
        return imgResDao.getDetailed(imgRes.irType, imgRes.pageNum, imgRes.itemCount)
    }
}