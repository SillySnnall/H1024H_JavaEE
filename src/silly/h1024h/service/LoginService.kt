package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.entity.User
import silly.h1024h.dao.UserDao
import silly.h1024h.utils.Util
import javax.servlet.http.HttpServletResponse

class LoginService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<User>(response, map, User()) {
    override fun isEmpty(): Boolean {
        if (model.account.isEmpty() || model.password.isEmpty()) return failData(1018, ERROR_MAP[1018] ?: "")
        return true
    }

    override fun mainService(): Boolean {
        val userDao = UserDao()
        // 判断用户是否存在
        val userList = userDao.findByName(model.account)
        if (userList.isEmpty()) return failData(1011, ERROR_MAP[1011] ?: "")

        // 判断密码是否正确
        if (model.password != userList[0].password) return failData(1012, ERROR_MAP[1012] ?: "")
        // 更新数据库中的token
        userList[0].token = Util.getUUID()
        val updateToken = userDao.updateToken(userList[0].account, userList[0].token)
        if (!updateToken) return failData(1020, ERROR_MAP[1020] ?: "")

        return successData("{\"account\":\"${userList[0].account}\",\"email\":\"${userList[0].email}\",\"token\":\"${userList[0].token}\",\"create_time\":\"${userList[0].create_time}\"}")
    }
}