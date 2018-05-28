package silly.h1024h.isentity

import silly.h1024h.entity.User

/**
 * User字段判空类
 */
object IsEmptyUser {

    fun isUser(user: User): String {
        if (user.getuName().isNullOrEmpty()) return "请填写用户名"
        if (user.getuPassword().isNullOrEmpty()) return "请填写密码"
        if (user.code == 0) return "请填写验证码"
        return ""
    }

    fun isSendCode(user: User): String {
        if (user.getuName().isNullOrEmpty()) return "请填写用户名"
        return ""
    }
}