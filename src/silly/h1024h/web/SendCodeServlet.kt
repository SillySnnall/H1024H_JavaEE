package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.entity.User
import silly.h1024h.isentity.IsEmptyUser
import silly.h1024h.service.RegisterService
import silly.h1024h.utils.EmailUtil
import silly.h1024h.utils.RedisUtil
import silly.h1024h.utils.Util

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "SendCodeServlet", urlPatterns = ["/send_code"])
class SendCodeServlet : BaseServlet<User>() {
    override fun getModel(): User? {
        return User()
    }

    /**
     * 发送验证码
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: User?) {
        // 判空
        val isSendCode = IsEmptyUser.isSendCode(model!!)
        if (isSendCode.isNotEmpty()) {
            failData(ErrorEnumMsg.error1002, isSendCode)
            return
        }
        // 邮箱判断
        if (!Util.isEmail(model.email)) {
            failData(ErrorEnumMsg.error1003, ErrorEnumParam.error1003)
            return
        }
        // 发送邮箱验证码
        val code = EmailUtil.sendCodeEmail(model.email)
        if(code.isEmpty()){
            failData(ErrorEnumMsg.error1016, ErrorEnumParam.error1016)
            return
        }
        // 存储验证码，30分钟失效
        RedisUtil.getRu().setex(model.account, code, 1800)
        successData("发送成功")
        return
    }
}
