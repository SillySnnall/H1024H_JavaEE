package silly.h1024h.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import silly.h1024h.common.Config.RES_PATH
import silly.h1024h.dao.OpinionDao
import silly.h1024h.dao.ResDataDao
import silly.h1024h.entity.Opinion
import silly.h1024h.entity.ResData
import silly.h1024h.service.impl.CommitImgServiceImpl
import silly.h1024h.service.impl.OpinionServiceImpl
import silly.h1024h.utils.FileUtil
import silly.h1024h.utils.Util
import java.io.File

class OpinionService : OpinionServiceImpl {

    private val opinionDao = OpinionDao()
    override fun findOpinion(): String {
        val opinion = opinionDao.findOpinion()
        val builder = StringBuilder().append("[")
        for (data in opinion) {
            builder.append("{\"name\":\"${data.account}\",\"url\":\"${data.content}\",\"type\":\"${data.create_time}\"},")
        }
        builder.deleteCharAt(builder.length - 1).append("]")
        return builder.toString()
    }

    override fun saveOpinion(opinion: Opinion): Boolean {
        opinion.create_time = Util.getCurrentDate()
        val params = arrayOf<Any>("'${opinion.account}'", "'${opinion.content}'", "'${opinion.create_time}'")
        return opinionDao.saveOpinion(params)
    }
}