package silly.h1024h.common

import silly.h1024h.service.*
import javax.servlet.http.HttpServletResponse

/**
 * 接口对应业务
 */
fun whenAc(map: Map<String, Any>, response: HttpServletResponse) {
    when (map["ac"]) {
        "get_main_url" -> GetMainUrlService(response, map)// 获取主地址
        "bind_email" -> BindEmailService(response, map)// 绑定邮箱
        "change_pwd" -> ChangePwdService(response,map) // 修改密码
        "get_cover_img_detailed" -> CoverImgDetailedService(response,map) // 获取指定封面详细数据
        "get_cover_img" -> CoverImgService(response,map) // 获取封面
        "get_hot" -> GetHotService(response,map) // 获取热门数据
        "get_type_list" -> TypeListService(response,map) // 获取分类列表
        "get_user" -> GetUserService(response,map) // 获取指定用户信息
        "hot_count" -> HotCountService(response,map) // 热门计数
        "login" -> LoginService(response,map) // 登录
        "register" -> RegisterService(response,map) // 注册
        "opinion" -> OpinionService(response,map) // 意见反馈提交
        "send_code" -> SendCodeService(response,map) // 发送验证码
    }
}