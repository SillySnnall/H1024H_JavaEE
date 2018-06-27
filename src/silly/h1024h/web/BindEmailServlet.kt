package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.entity.User
import silly.h1024h.isentity.IsEmptyUser
import silly.h1024h.service.BindEmailService
import silly.h1024h.utils.RedisUtil
import silly.h1024h.utils.Util

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "BindEmailServlet", urlPatterns = ["/bind_email"])
class BindEmailServlet : BaseServlet<User>() {
    override fun getModel(): User? {
        return User()
    }

    private val bindEmailService = BindEmailService()

    /**
     * 注册
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: User?) {
        // 判空
        val bindEmail = IsEmptyUser.isBindEmail(model!!)
        if (bindEmail.isNotEmpty()) {
            failData(ErrorEnumMsg.error1002, bindEmail)
            return
        }
        // 邮箱判断
        if (!Util.isEmail(model.email)) {
            failData(ErrorEnumMsg.error1003, ErrorEnumParam.error1003)
            return
        }
        // 验证码过期
        val codeR = RedisUtil.getRu().get(model.account)
        if (codeR.isNullOrEmpty()) {
            failData(ErrorEnumMsg.error1006, ErrorEnumParam.error1006)
            return
        }
        // 验证码判断
        if (codeR != model.code) {
            failData(ErrorEnumMsg.error1014, ErrorEnumParam.error1014)
            return
        }
        // 保存email
        if (bindEmailService.saveEmail(model)) successData("绑定成功")
        else failData(ErrorEnumMsg.error1015, ErrorEnumParam.error1015)
    }
}
