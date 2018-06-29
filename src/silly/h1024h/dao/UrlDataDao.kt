package silly.h1024h.dao

import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table
import org.apache.commons.dbutils.handlers.BeanListHandler
import silly.h1024h.base.BaseDao
import silly.h1024h.entity.User
import silly.h1024h.dao.impl.RegisterDaoImpl
import silly.h1024h.dao.impl.UrlDataDaoImpl
import silly.h1024h.entity.HotData
import silly.h1024h.entity.ResData
import silly.h1024h.entity.UrlData
import silly.h1024h.utils.Util
import java.sql.SQLException


class UrlDataDao : BaseDao(), UrlDataDaoImpl {
    override fun findByVersionChannel(versionCode: String, channel: String): List<UrlData> {
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