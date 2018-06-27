package silly.h1024h.isentity

import silly.h1024h.entity.User

/**
 * User字段判空类
 */
object IsEmptyUser {

    fun isUser(user: User): String {
        if (user.account.isEmpty()) return "请填写用户名"
        if (user.password.isEmpty()) return "请填写密码"
        return ""
    }

    fun isBindEmail(user: User): String {
        if (user.account.isEmpty()) return "请填写用户名"
        if (user.email.isEmpty()) return "请填写邮箱"
        if (user.code.isEmpty()) return "验证码为空"
        return ""
    }

    fun isSendCode(user: User): String {
        if (user.account.isEmpty()) return "请填写用户名"
        if (user.email.isEmpty()) return "请填写邮箱"
        return ""
    }

    fun isGetUser(user: User): String {
        if (user.account.isEmpty()) return "请填写用户名"
        return ""
    }

    fun isChangePwd(user: User): String {
        if (user.account.isEmpty()) return "请填写用户名"
        if (user.password.isEmpty()) return "请填写旧密码"
        if (user.new_password.isEmpty()) return "请填写新密码"
        return ""
    }
}