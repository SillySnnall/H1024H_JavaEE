package silly.h1024h.dao

import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table
import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.h1024h.base.BaseDao
import silly.h1024h.entity.User
import silly.h1024h.dao.impl.RegisterDaoImpl
import silly.h1024h.entity.ResData
import silly.h1024h.utils.Util
import java.sql.SQLException


class UserDao : BaseDao(), RegisterDaoImpl {
    override fun findByName(account: String): List<User> {
        try {
            val sql = "SELECT * FROM user WHERE account='$account'"
            return getQueryRunner().query(sql, BeanListHandler<User>(User::class.java))
        } catch (e: SQLException) {
            e.printStackTrace()
            System.out.println(e)
        }
        return arrayListOf()
    }

    override fun saveUser(params: Array<Any>): Boolean {
        try {
            val sql = "INSERT INTO user ( _id, account, password, create_time ) VALUES ( null, ${params[0]}, ${params[1]}, ${params[2]} );"
            getQueryRunner().update(sql)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            System.out.println(e)
        }
        return false
    }

}