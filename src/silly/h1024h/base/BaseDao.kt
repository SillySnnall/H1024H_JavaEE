package silly.h1024h.base

import org.apache.commons.dbutils.QueryRunner
import silly.h1024h.utils.C3P0Utils

open class BaseDao {

   fun getQueryRunner(): QueryRunner {
       return QueryRunner(C3P0Utils.getDataSource())
   }
}