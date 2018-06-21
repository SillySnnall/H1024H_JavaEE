package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.entity.User
import silly.h1024h.isentity.IsEmptyUser
import silly.h1024h.service.RegisterService
import silly.h1024h.utils.RedisUtil
import silly.h1024h.utils.Util
import silly.h1024h.utils.Util.accountRegex
import silly.h1024h.utils.Util.passwordRegex

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "RegisterServlet", urlPatterns = ["/register"])
class RegisterServlet : BaseServlet<User>() {
    override fun getModel(): User? {
        return User()
    }

    private val registerService = RegisterService()

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
        // 帐号正则判断
        if (!accountRegex(model.account)) {
            failData(ErrorEnumMsg.error1009, ErrorEnumParam.error1009)
            return
        }
        // 密码正则判断
        if (!passwordRegex(model.password)) {
            failData(ErrorEnumMsg.error1010, ErrorEnumParam.error1010)
            return
        }
        // 判断用户是否存在
        if (registerService.isUser(model)) {
            failData(ErrorEnumMsg.error1000, ErrorEnumParam.error1000)
            return
        }
        // 存储用户
        val saveUser = registerService.saveUser(model)
        if (saveUser.isEmpty()) failData(ErrorEnumMsg.error1001, ErrorEnumParam.error1001)
        else successData(saveUser)
    }
}
