package silly.h1024h.dao

import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanListHandler
import redis.clients.jedis.Transaction
import silly.h1024h.base.BaseDao
import silly.h1024h.dao.impl.ResDataDaoImpl
import silly.h1024h.entity.ResData
import silly.h1024h.utils.C3P0Utils
import java.sql.SQLException
import javax.mail.Session


class ResDataDataDao : BaseDao(), ResDataDaoImpl {

    fun findTypeList(){
        try {
            // 2.编写sql语句
            val sql = "select * from type_list"
            // 3.执行查询操作
            val users = getQueryRunner().query(sql, BeanListHandler<ResData>(ResData::class.java))
            // 4.对结果集集合进行遍历
            println(users.toString())
            //            for (ImgRes user : users) {
            //                System.out.println(user.getIr_details() + " : " + user.getIr_url());
            //            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun findAllDetailsOnly(): List<String> {



        val session = HibernateUtils.getCurrentSession()
        val beginTransaction = session.beginTransaction()
        return try {
            val list = session.createQuery("select irDetails from ImgRes GROUP BY irDetails")
                    .list() as List<String>
            beginTransaction.commit()
            list
        } catch (e: Exception) {
            e.printStackTrace()
            beginTransaction.rollback()
            session.close()
            arrayListOf()
        }
    }

    override fun getIrTypeMax(): List<ImgRes> {
        val session = HibernateUtils.getCurrentSession()
        val beginTransaction = session.beginTransaction()
        return try {
            val list = session.createQuery("from ImgRes where irType=(select max(irType) from ImgRes)")
                    .list() as List<ImgRes>
            beginTransaction.commit()
            list
        } catch (e: Exception) {
            e.printStackTrace()
            beginTransaction.rollback()
            session.close()
            arrayListOf()
        }
    }


    override fun findByDetails(irDetails: String): List<ImgRes> {
        val session = HibernateUtils.getCurrentSession()
        val beginTransaction = session.beginTransaction()
        return try {
            val list = session.createQuery("from ImgRes where irDetails = ?")
                    .setParameter(0, irDetails)
                    .list() as List<ImgRes>
            beginTransaction.commit()
            list
        } catch (e: Exception) {
            e.printStackTrace()
            beginTransaction.rollback()
            session.close()
            arrayListOf()
        }
    }

    override fun findByIrCoverIrDetails(irCover: Int, irDetails: String): List<ImgRes> {
        val session = HibernateUtils.getCurrentSession()
        val beginTransaction = session.beginTransaction()
        return try {
            val list = session.createQuery("from ImgRes where irCover = ? and irDetails = ?")
                    .setParameter(0, irCover)
                    .setParameter(1, irDetails)
                    .list() as List<ImgRes>
            beginTransaction.commit()
            list
        } catch (e: Exception) {
            e.printStackTrace()
            beginTransaction.rollback()
            session.close()
            arrayListOf()
        }
    }

    override fun saveImg(imgResList: List<ImgRes>): Boolean {
        var session: Session? = null
        var beginTransaction: Transaction? = null
        return try {
            for (imgRes in imgResList) {
                session = HibernateUtils.getCurrentSession()
                beginTransaction = session.beginTransaction()
                session.saveOrUpdate(imgRes)
                beginTransaction.commit()
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            beginTransaction?.rollback()
            session?.close()
            false
        }
    }

    override fun getCover(pageNum: Int, itemCount: Int): List<ImgRes> {
        val session = HibernateUtils.getCurrentSession()
        val beginTransaction = session.beginTransaction()
        return try {
            val list = session.createQuery("from ImgRes where irCover = ?").setParameter(0, 1)
                    .setFirstResult(pageNum)
                    .setMaxResults(itemCount).list() as List<ImgRes>
            beginTransaction.commit()
            list
        } catch (e: Exception) {
            beginTransaction.rollback()
            session.close()
            arrayListOf()
        }
    }

    override fun getDetailed(irType: Int, pageNum: Int, itemCount: Int): List<ImgRes> {
        val session = HibernateUtils.getCurrentSession()
        val beginTransaction = session.beginTransaction()
        return try {
            val list = session.createQuery("from ImgRes where irType = ?").setParameter(0, irType)
                    .setFirstResult(pageNum)
                    .setMaxResults(itemCount).list() as List<ImgRes>
            beginTransaction.commit()
            list
        } catch (e: Exception) {
            beginTransaction.rollback()
            session.close()
            arrayListOf()
        }
    }


    override fun findByIrCoverIrType(irCover: Int, irType: Int): List<ImgRes> {
        val session = HibernateUtils.getCurrentSession()
        val beginTransaction = session.beginTransaction()
        return try {
            val list = session.createQuery("from ImgRes where irCover = ? and irType = ?")
                    .setParameter(0, irCover)
                    .setParameter(1, irType)
                    .list() as List<ImgRes>
            beginTransaction.commit()
            list
        } catch (e: Exception) {
            e.printStackTrace()
            beginTransaction.rollback()
            session.close()
            arrayListOf()
        }
    }
}