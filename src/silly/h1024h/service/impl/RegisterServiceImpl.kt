package silly.h1024h.service.impl

import silly.h1024h.entity.User

interface RegisterServiceImpl {
    /**
     * 保存注册用户
     */
    fun saveUser(user: User): Boolean

    /**
     * 查询用户是否存在
     */
    fun isUser(user: User): Boolean

    /**
     * 获取注册用户
     */
    fun getRegisterUser(user: User): User?
}