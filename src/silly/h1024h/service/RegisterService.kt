package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.entity.User
import silly.h1024h.dao.UserDao
import silly.h1024h.isentity.IsEmptyUser
import silly.h1024h.utils.Util
import javax.servlet.http.HttpServletResponse

class RegisterService(response: HttpServletResponse, map: Map<String, String>) : BaseService<User>(response, map, User()) {
    override fun isEmpty(): Boolean {
        if (model.account.isEmpty() || model.password.isEmpty()) return failData(1018, ERROR_MAP[1018] ?: "")
        return true
    }

    override fun mainService(): Boolean {
        val userDao = UserDao()
        // 帐号正则判断
        if (!Util.accountRegex(model.account)) return failData(1009, ERROR_MAP[1009] ?: "")

        // 密码正则判断
        if (!Util.passwordRegex(model.password)) return failData(1010, ERROR_MAP[1010] ?: "")

        // 判断用户是否存在
        if (userDao.findByName(model.account).isNotEmpty()) return failData(1000, ERROR_MAP[1000] ?: "")

        // 创建时间和token
        model.create_time = Util.getCurrentDate()
        model.token = Util.getUUID()
        // 存储用户
        val params = arrayOf<Any>("'${model.account}'", "'${model.password}'", "'${model.create_time}'", "'${model.token}'")
        if (!userDao.saveUser(params)) return failData(1001, ERROR_MAP[1001] ?: "")

        return successData("{\"account\":\"${model.account}\",\"email\":\"${model.email}\",\"token\":\"${model.token}\",\"create_time\":\"${model.create_time}\"}")
    }
}