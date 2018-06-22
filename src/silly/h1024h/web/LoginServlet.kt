package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.entity.User
import silly.h1024h.isentity.IsEmptyUser
import silly.h1024h.service.LoginService
import silly.h1024h.utils.Util

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "LoginServlet", urlPatterns = ["/login"])
class LoginServlet : BaseServlet<User>() {
    override fun getModel(): User? {
        return User()
    }

    private val loginService = LoginService()

    /**
     * 注册
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: User?) {
        // 判断帐号密码为空
        val isUser = IsEmptyUser.isUser(model!!)
        if (isUser.isNotEmpty()) {
            failData(ErrorEnumMsg.error1002, isUser)
            return
        }
        // 判断用户是否存在
        val user = loginService.getUser(model)
        if (user.account.isEmpty()) {
            failData(ErrorEnumMsg.error1011, ErrorEnumParam.error1011)
        } else {
            val checkUser = loginService.checkUser(model, user)
            // 判断密码是否正确
            if (checkUser.isEmpty()) failData(ErrorEnumMsg.error1012, ErrorEnumParam.error1012)
            else successData(checkUser)
        }
    }
}
