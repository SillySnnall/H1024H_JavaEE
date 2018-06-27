package silly.h1024h.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import silly.h1024h.common.Config.RES_PATH
import silly.h1024h.dao.ResDataDao
import silly.h1024h.dao.UserDao
import silly.h1024h.entity.ResData
import silly.h1024h.entity.User
import silly.h1024h.service.impl.BindEmailServiceImpl
import silly.h1024h.service.impl.CommitImgServiceImpl
import silly.h1024h.utils.FileUtil
import java.io.File

class BindEmailService : BindEmailServiceImpl {

    private val userDao = UserDao()

    override fun saveEmail(user: User): Boolean {
        return userDao.saveEmail(user.account, user.email)
    }
}