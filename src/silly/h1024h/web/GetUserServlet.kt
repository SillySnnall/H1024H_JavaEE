package silly.h1024h.web

import silly.h1024h.base.BaseServlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.entity.User
import silly.h1024h.isentity.IsEmptyUser
import silly.h1024h.service.GetUserService


@WebServlet(name = "GetUserServlet", urlPatterns = ["/get_user"])
class GetUserServlet : BaseServlet<User>() {
    override fun getModel(): User? {
        return User()
    }

    private val getUserService = GetUserService()
    /**
     * 提交上传的图片
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: User?) {
        // 判空
        val isGetUser = IsEmptyUser.isGetUser(model!!)
        if (isGetUser.isNotEmpty()) {
            failData(ErrorEnumMsg.error1002, isGetUser)
            return
        }
        // 获取User
        val user = getUserService.getUser(model)
        if(user.isEmpty()){
            failData(ErrorEnumMsg.error1011, ErrorEnumParam.error1011)
            return
        }
        successData(user)
    }
}
