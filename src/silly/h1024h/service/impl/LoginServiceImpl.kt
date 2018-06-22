package silly.h1024h.service.impl

import silly.h1024h.entity.User

interface LoginServiceImpl {
    /**
     * 获取用户
     */
    fun getUser(user: User): User

    /**
     * 验证用户密码
     */
    fun checkUser(user: User,dbUser:User): String
}