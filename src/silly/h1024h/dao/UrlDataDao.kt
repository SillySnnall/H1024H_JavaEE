package silly.h1024h.dao

import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.h1024h.base.BaseDao
import silly.h1024h.entity.UrlData
import java.sql.SQLException


class UrlDataDao : BaseDao() {
     fun findByVersionChannel(versionCode: String, channel: String): List<UrlData> {
        try {
            val sql = "SELECT * FROM main_url WHERE version_code=$versionCode AND channel='$channel'"
            return getQueryRunner().query(sql, BeanListHandler<UrlData>(UrlData::class.java))
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return arrayListOf()
    }
}