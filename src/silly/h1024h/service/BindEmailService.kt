package silly.h1024h.service

import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.dao.UserDao
import silly.h1024h.entity.User
import silly.h1024h.utils.RedisUtil
import silly.h1024h.utils.Util
import javax.servlet.http.HttpServletResponse

class BindEmailService(response: HttpServletResponse, map: Map<String, String>) : BaseService<User>(response, map, User()) {

    override fun isEmpty(): Boolean {
        if (model.account.isEmpty() || model.email.isEmpty() || model.code.isEmpty()) return failData(1018, ERROR_MAP[1018]
                ?: "")
        return true
    }

    override fun mainService(): Boolean {
        // 邮箱格式判断
        if (!Util.isEmail(model.email)) return failData(1003, ERROR_MAP[1003] ?: "")

        // 验证码过期
        val codeR = RedisUtil.getRu().get(model.account)
        if (codeR.isNullOrEmpty()) return failData(1006, ERROR_MAP[1006] ?: "")

        // 验证码判断
        if (codeR != model.code) return failData(1014, ERROR_MAP[1014] ?: "")

        // 保存email
        if (!UserDao().saveEmail(model.account, model.email)) return failData(1015, ERROR_MAP[1015] ?: "")

        return successData("绑定成功")
    }
}