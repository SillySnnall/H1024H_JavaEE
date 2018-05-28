package silly.h1024h.dao

import silly.h1024h.base.BaseDao
import silly.h1024h.entity.User
import silly.h1024h.dao.impl.RegisterDaoImpl


class UserDao : BaseDao(), RegisterDaoImpl {
    override fun findFor_uname(user: User): List<User> {
        return getSession().createQuery("from User where uName = ?").setParameter(0, user.getuName()).list() as List<User>
    }

    override fun saveUser(user: User) {
        getSession().save(user)
    }

}