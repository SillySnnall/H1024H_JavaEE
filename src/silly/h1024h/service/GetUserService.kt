package silly.h1024h.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import silly.h1024h.base.BaseService
import silly.h1024h.common.ERROR_MAP
import silly.h1024h.dao.ResDataDao
import silly.h1024h.dao.UserDao
import silly.h1024h.entity.ResData
import silly.h1024h.entity.User
import javax.servlet.http.HttpServletResponse

class GetUserService(response: HttpServletResponse, map: Map<String, Any>) : BaseService<User>(response, map, User()) {
    override fun isEmpty(): Boolean {
        if (model.account.isEmpty()) return failData(1018, ERROR_MAP[1018] ?: "")
        return true
    }

    override fun mainService(): Boolean {
        // 获取User数据
        val findByName = UserDao().findByName(model.account)
        // 用户不存在
        if (findByName.isEmpty()) return failData(1011, ERROR_MAP[1011] ?: "")

        return successData("{\"account\":\"${findByName[0].account}\",\"email\":\"${findByName[0].email}\",\"token\":\"${findByName[0].token}\",\"create_time\":\"${findByName[0].create_time}\"}")
    }
}