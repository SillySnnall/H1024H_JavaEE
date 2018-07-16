package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.dao.UserDao
import silly.h1024h.entity.User
import silly.h1024h.utils.Util
import javax.servlet.http.HttpServletResponse

class ChangePwdService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<User>(response, map, User()) {
    override fun isEmpty(): Boolean {
        if (model.account.isEmpty() || model.password.isEmpty() || model.new_password.isEmpty()) return failData(1018, ERROR_MAP[1018]
                ?: "")
        return true
    }

    override fun mainService(): Boolean {
        val userDao = UserDao()
        // 新密码正则判断
        if (!Util.passwordRegex(model.new_password)) return failData(1010, ERROR_MAP[1010] ?: "")

        val user = userDao.findByName(model.account)[0]
        // 判断旧密码
        if (user.password != model.password) return failData(1002, "旧密码不正确")

        // 判断旧密码和新密码是否相同
        if (user.password == model.new_password) return failData(1002, "新密码和旧密码相同")

        // 更改密码
        if (!userDao.updatePwd(model.account, model.new_password)) return failData(1022, ERROR_MAP[1022] ?: "")

        return successData("更改成功")
    }
}