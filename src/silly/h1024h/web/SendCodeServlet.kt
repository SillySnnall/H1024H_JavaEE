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

@WebServlet(name = "SendCodeServlet", urlPatterns = ["/sendCode"])
class SendCodeServlet : BaseServlet<User>() {
    private val registerService = RegisterService()
    override fun getModel(): User? {
        return User()
    }
    /**
     * 发送验证码
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: User?) {
//        // 判空
//        val isSendCode = IsEmptyUser.isSendCode(model!!)
//        if (isSendCode.isNotEmpty()) {
//            failData(ErrorEnumMsg.error1002, isSendCode)
//            return
//        }
//        // 电话号码判断
//        if (!Util.isPhone(model.name)) {
//            // 邮箱判断
//            if (!Util.isEmail(model.name)) {
//                failData(ErrorEnumMsg.error1005, ErrorEnumParam.error1005)
//                return
//            } else {
//                // 用户已存在
//                if (registerService.getUser(model)) {
//                    failData(ErrorEnumMsg.error1000, ErrorEnumParam.error1000)
//                    return
//                }
//                // 发送邮箱验证码
//                val code = EmailUtil.sendCodeEmail(model.name)
//                // 存储验证码，30分钟失效
//                RedisUtil.getRu().setex(model.name, code, 1800)
//                val map = mapOf("uEmail" to model.name)
//                successData(map)
//                return
//            }
//        } else {
//            // 用户已存在
//            if (registerService.getUser(model)) {
//                failData(ErrorEnumMsg.error1000, ErrorEnumParam.error1000)
//                return
//            }
//            // 发送电话验证码
//            return
//        }
    }
}
