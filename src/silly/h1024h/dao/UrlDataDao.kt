package silly.h1024h.dao

import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.h1024h.base.BaseDao
import silly.h1024h.entity.UrlData
import java.sql.SQLException


class UrlDataDao : BaseDao() {
    /**
     * 根据version_code和channel查找APP
     */
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

    /**
     * 根据version_code和channel更新type
     */
    fun updateType(versionCode: String, channel: String, type: String): Boolean {
        try {
            val sql = "UPDATE main_url SET type=$type WHERE version_code=$versionCode AND channel='$channel';"
            getQueryRunner().update(sql)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            System.out.println(e)
        }
        return false
    }
}