package silly.h1024h.dao

import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.h1024h.base.BaseDao
import silly.h1024h.dao.impl.ResDataDaoImpl
import silly.h1024h.entity.ResData
import java.sql.SQLException


class ResDataDao : BaseDao(), ResDataDaoImpl {

    override fun findTypeList(): List<ResData> {
        try {
            val sql = "SELECT * FROM type_list"
            return getQueryRunner().query(sql, BeanListHandler<ResData>(ResData::class.java))
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return arrayListOf()
    }
}