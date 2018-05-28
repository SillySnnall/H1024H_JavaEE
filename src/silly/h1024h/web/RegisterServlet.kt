package silly.h1024h.web

import org.apache.commons.beanutils.BeanUtils
import silly.h1024h.base.BaseServlet
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.entity.User
import silly.h1024h.isentity.IsEmptyUser
import silly.h1024h.service.RegisterService
import silly.h1024h.utils.RedisUtil
import silly.h1024h.utils.Util

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "RegisterServlet", urlPatterns = ["/register"])
class RegisterServlet : BaseServlet<User>() {
    override fun getModel(): User? {
        return User()
    }

    private val registerService = RegisterService()

    /**
     * 注册
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: User?) {
        // 判空
        val isUser = IsEmptyUser.isUser(model!!)
        if (isUser.isNotEmpty()) {
            failData(ErrorEnumMsg.error1002, isUser)
            return
        }
        // 电话号码判断
        if (!Util.isPhone(model.getuName())) {
            // 邮箱判断
            if (!Util.isEmail(model.getuName())) {
                failData(ErrorEnumMsg.error1005, ErrorEnumParam.error1005)
                return
            } else {
                model.setuEmail(model.getuName())
            }
        } else {
            model.setuPhone(model.getuName())
        }
        // 用户已存在
        if (registerService.isUser(model)) {
            failData(ErrorEnumMsg.error1000, ErrorEnumParam.error1000)
            return
        }
        // 验证码过期
        if (RedisUtil.getRu().get(model.getuName()).isNullOrEmpty()) {
            failData(ErrorEnumMsg.error1006, ErrorEnumParam.error1006)
            return
        }
        // 创建token和时间
        model.setuToken(Util.getUUID())
        model.setuCreateTime(Util.getCurrentDate())
        // 用户保存失败
        if (!registerService.saveUser(model)) {
            failData(ErrorEnumMsg.error1001, ErrorEnumParam.error1001)
            return
        }
        // 查找注册用户，返回数据
        val registerUser = registerService.getRegisterUser(model)
        val map = BeanUtils.describe(registerUser)// bean->Map
        map.remove("uPassword")// 移除密码
        map.remove("uCreateTime")// 移除创建时间
        map.remove("class")// 移除map自带字段
        successData(map)
        return
    }
}
