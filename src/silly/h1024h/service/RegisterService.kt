package silly.h1024h.service

import silly.h1024h.entity.User
import silly.h1024h.dao.UserDao
import silly.h1024h.service.impl.RegisterServiceImpl

class RegisterService : RegisterServiceImpl {


    private val registerDao = UserDao()

    override fun saveUser(user: User): Boolean {
        try {
            registerDao.getTransaction()
            registerDao.saveUser(user)
            registerDao.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            registerDao.rollBack()
            return false
        }
        return true
    }

    override fun isUser(user: User): Boolean {
        try {
            registerDao.getTransaction()
            val userList = registerDao.findFor_uname(user)
            registerDao.commit()
            if(userList.isNotEmpty()) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            registerDao.rollBack()
            return false
        }
        return false
    }

    override fun getRegisterUser(user: User): User? {
        try {
            registerDao.getTransaction()
            val userList = registerDao.findFor_uname(user)
            registerDao.commit()
            if(userList.isEmpty()) {
                return null
            }
            return userList[0]
        } catch (e: Exception) {
            e.printStackTrace()
            registerDao.rollBack()
            return null
        }
    }

}