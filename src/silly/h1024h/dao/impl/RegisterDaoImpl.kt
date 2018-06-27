package silly.h1024h.dao.impl

import silly.h1024h.entity.User

interface RegisterDaoImpl {
    /**
     * 保存注册用户
     */
    fun saveUser(params: Array<Any>): Boolean

    /**
     * 根据用户名查找
     */
    fun findByName(account: String): List<User>

    /**
     * 更新Token
     */
    fun updateToken(account: String, token: String): Boolean

    /**
     * 更新密码
     */
    fun updatePwd(account: String, password: String): Boolean

    /**
     * 存储email
     */
    fun saveEmail(account: String, email: String): Boolean
}