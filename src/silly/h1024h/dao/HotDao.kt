package silly.h1024h.dao

import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.h1024h.base.BaseDao
import silly.h1024h.entity.HotData
import java.sql.SQLException


class HotDao : BaseDao() {
    /**
     * 排序获取热门数据
     */
    fun getSortHotData(pageNum: Int, itemCount: Int): List<HotData> {
        try {
            val sql = "SELECT * FROM hot ORDER BY hot_count DESC LIMIT $pageNum,$itemCount;"
            return getQueryRunner().query(sql, BeanListHandler<HotData>(HotData::class.java))
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return arrayListOf()
    }

    /**
     * 热门计数
     */
    fun countHot(type: String): Boolean {
        try {
            val sql = "UPDATE hot SET hot_count=hot_count+1 WHERE type=$type;"
            getQueryRunner().update(sql)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            System.out.println(e)
        }
        return false
    }

    /**
     * 添加热门原始数据
     */
    fun addHotData(params: Array<Any>) {
        val sql = "INSERT INTO hot ( _id, type, table_name, hot_count ) VALUES ( null, ${params[0]}, ${params[1]}, 0 );"
        getQueryRunner().update(sql)
    }
}