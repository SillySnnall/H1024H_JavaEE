package silly.h1024h.service

import silly.h1024h.dao.ResDataDataDao
import silly.h1024h.service.impl.GetIrDetailsServiceImpl


class GetIrDetailsService : GetIrDetailsServiceImpl {
    private val imgResDao = ResDataDataDao()
    override fun getIrDetails(): List<String> {
        return imgResDao.findAllDetailsOnly()
    }
}