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
import silly.h1024h.service.impl.GetUserServiceImpl
import silly.h1024h.utils.FileUtil
import java.io.File

class GetUserService : GetUserServiceImpl {

    private val userDao = UserDao()

    override fun getUser(user: User): String {
        val findByName = userDao.findByName(user.account)
        if (findByName.isEmpty()) return ""
        return "{\"account\":\"${findByName[0].account}\",\"email\":\"${findByName[0].email}\",\"token\":\"${findByName[0].token}\",\"create_time\":\"${findByName[0].create_time}\"}"
    }
}