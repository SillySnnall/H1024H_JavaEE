package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.dao.UserDao
import silly.h1024h.entity.User
import silly.h1024h.isentity.IsEmptyUser
import silly.h1024h.utils.Util

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "ChangePwdServlet", urlPatterns = ["/change_pwd"])
class ChangePwdServlet : BaseServlet<User>() {
    override fun getModel(): User? {
        return User()
    }

    private val userDao = UserDao()

    /**
     * 注册
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: User?) {
        // 判断帐号密码为空
        val isUser = IsEmptyUser.isChangePwd(model!!)
        if (isUser.isNotEmpty()) {
            failData(ErrorEnumMsg.error1002, isUser)
            return
        }
        // 新密码正则判断
        if (!Util.passwordRegex(model.new_password)) {
            failData(ErrorEnumMsg.error1010, ErrorEnumParam.error1010)
            return
        }
        val user = userDao.findByName(model.account)[0]
        // 判断旧密码
        if (user.password != model.password) {
            failData(ErrorEnumMsg.error1002, "旧密码不正确")
            return
        }
        // 判断旧密码和新密码是否相同
        if (user.password == model.new_password) {
            failData(ErrorEnumMsg.error1002, "新密码和旧密码相同")
            return
        }
        // 更改密码
        if (userDao.updatePwd(model.account, model.new_password)) successData("更改成功")
        else failData(ErrorEnumMsg.error1015, "更改失败")
    }
}
