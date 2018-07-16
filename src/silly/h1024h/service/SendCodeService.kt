package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.entity.User
import silly.h1024h.utils.EmailUtil
import silly.h1024h.utils.RedisUtil
import silly.h1024h.utils.Util
import javax.servlet.http.HttpServletResponse

class SendCodeService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<User>(response, map, User()) {

    override fun isEmpty(): Boolean {
        if (model.account.isEmpty() || model.email.isEmpty()) return failData(1018, ERROR_MAP[1018] ?: "")
        return true
    }

    override fun mainService(): Boolean {
        // 邮箱判断
        if (!Util.isEmail(model.email)) return failData(1003, ERROR_MAP[1003] ?: "")

        // 发送邮箱验证码
        val code = EmailUtil.sendCodeEmail(model.email)
        if (code.isEmpty()) return failData(1016, ERROR_MAP[1016] ?: "")

        // Redis存储验证码，30分钟失效
        RedisUtil.getRu().setex(model.account, code, 1800) ?: return failData(1021, ERROR_MAP[1021] ?: "")

        return successData("发送成功")
    }
}