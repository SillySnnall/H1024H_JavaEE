package silly.h1024h.dao

import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.h1024h.base.BaseDao
import silly.h1024h.entity.TypeList
import java.sql.SQLException


class TypeListDao : BaseDao() {


    /**
     * 添加类别列表数据
     */
     fun addTypeListData(params: Array<Any>) {
        val sql = "INSERT INTO type_list ( _id, name, type ) VALUES ( null, ${params[0]}, ${params[1]} );"
        getQueryRunner().update(sql)
    }

    /**
     * 查找类别列表
     */
     fun findTypeList(): List<TypeList> {
        try {
            val sql = "SELECT * FROM type_list"
            return getQueryRunner().query(sql, BeanListHandler<TypeList>(TypeList::class.java))
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return arrayListOf()
    }
}