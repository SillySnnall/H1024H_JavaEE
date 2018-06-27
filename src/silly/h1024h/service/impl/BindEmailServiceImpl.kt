package silly.h1024h.service.impl

import silly.h1024h.entity.ResData
import silly.h1024h.entity.User
import javax.servlet.http.HttpServletRequest

interface BindEmailServiceImpl {
    /**
     * 存储email
     */
    fun saveEmail(user: User):Boolean
}