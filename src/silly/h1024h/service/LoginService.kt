package silly.h1024h.service

import silly.h1024h.entity.User
import silly.h1024h.dao.UserDao
import silly.h1024h.service.impl.LoginServiceImpl
import silly.h1024h.utils.Util

class LoginService : LoginServiceImpl {


    private val userDao = UserDao()

    override fun getUser(user: User): User {
        val userList = userDao.findByName(user.account)
        return if (userList.isEmpty()) User() else userList[0]
    }

    override fun checkUser(user: User, dbUser: User): String {
        return if (user.password != dbUser.password)
            ""
        else {
            dbUser.token = Util.getUUID()
            userDao.updateToken(dbUser.account, dbUser.token)
            StringBuilder().append("{\"account\":\"${dbUser.account}\",\"token\":\"${dbUser.token}\",\"create_time\":\"${dbUser.create_time}\"}").toString()
        }


    }
}