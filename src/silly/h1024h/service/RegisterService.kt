package silly.h1024h.service

import silly.h1024h.entity.User
import silly.h1024h.dao.UserDao
import silly.h1024h.service.impl.RegisterServiceImpl
import silly.h1024h.utils.Util

class RegisterService : RegisterServiceImpl {

    private val userDao = UserDao()

    override fun saveUser(user: User): String {
        user.create_time = Util.getCurrentDate()
        val params = arrayOf<Any>("'${user.account}'", "'${user.password}'", "'${user.create_time}'")
        return if (userDao.saveUser(params))
            StringBuilder().append("{\"account\":\"${user.account}\",\"create_time\":\"${user.create_time}\"}").toString()
        else ""
    }

    override fun isUser(user: User): Boolean {
        return userDao.findByName(user.account).isNotEmpty()
    }
}