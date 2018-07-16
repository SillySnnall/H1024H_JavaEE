package silly.h1024h.dao

import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.h1024h.base.BaseDao
import silly.h1024h.entity.ResData
import java.sql.SQLException


class ResDataDao : BaseDao() {

    /**
     * 根据type查找封面
     */
     fun findByTypeCover(table: String, type: String): ResData {
        try {
            val sql = "SELECT * FROM $table WHERE type=$type AND is_cover=1"
            return getQueryRunner().query(sql, BeanListHandler<ResData>(ResData::class.java))[0]
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return ResData()
    }

    /**
     * 分页查找详情
     */
     fun findDetails(table: String, type: String, pageNum: Int, itemCount: Int): List<ResData> {
        try {
            val sql = "SELECT * FROM $table WHERE type=$type LIMIT $pageNum,$itemCount"
            return getQueryRunner().query(sql, BeanListHandler<ResData>(ResData::class.java))
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return arrayListOf()
    }

    /**
     * 添加资源数据
     */
     fun addResData(table: String, params: Array<Any>) {
        val sql = "INSERT INTO $table ( _id, name, url, type, is_cover ) VALUES ( null, ${params[0]}, ${params[1]}, ${params[2]}, ${params[3]} );"
        getQueryRunner().update(sql)
    }

    /**
     * 创建资源表
     */
     fun createResTable(table: String) {
        val sql = "CREATE TABLE IF NOT EXISTS `$table`(`_id` INT UNSIGNED AUTO_INCREMENT,`name` VARCHAR(255) NOT NULL,`url` VARCHAR(255) NOT NULL,`type` VARCHAR(255) NOT NULL,`is_cover` INT NOT NULL,PRIMARY KEY ( `_id` ))ENGINE=InnoDB DEFAULT CHARSET=utf8;"
        getQueryRunner().update(sql)
    }

    /**
     * 分页查找封面
     */
     fun findCover(table: String, pageNum: Int, itemCount: Int): List<ResData> {
        try {
            val sql = "SELECT * FROM $table WHERE is_cover=1 LIMIT $pageNum,$itemCount"
            return getQueryRunner().query(sql, BeanListHandler<ResData>(ResData::class.java))
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return arrayListOf()
    }


}