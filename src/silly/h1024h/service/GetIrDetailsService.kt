package silly.h1024h.service

import silly.h1024h.dao.ResDataDao
import silly.h1024h.service.impl.GetIrDetailsServiceImpl


class GetIrDetailsService : GetIrDetailsServiceImpl {
    private val imgResDao = ResDataDao()
    override fun getIrDetails(): List<String> {
//        return imgResDao.findAllDetailsOnly()
        return arrayListOf()
    }
}