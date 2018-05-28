package silly.h1024h.dao.impl

import silly.h1024h.entity.User

interface RegisterDaoImpl{
    /**
     * 保存注册用户
     */
    fun saveUser(user: User)

    /**
     * 根据用户名查找
     */
    fun findFor_uname(user: User): List<User>
}