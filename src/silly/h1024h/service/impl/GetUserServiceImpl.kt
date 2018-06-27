package silly.h1024h.service.impl

import silly.h1024h.entity.ResData
import silly.h1024h.entity.User
import javax.servlet.http.HttpServletRequest

interface GetUserServiceImpl {
    /**
     * 获取用户信息
     */
    fun getUser(user: User):String
}