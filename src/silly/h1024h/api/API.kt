package silly.h1024h.api


object InterF {
    val interf = mapOf(
            "register" to "注册",
            "sendCode" to "发送验证码",
            "getCoverImg" to "获取封面图片",
            "getCoverImgDetailed" to "获取这个封面中的详细图片",
            "updateImg" to "上传图片",
            "commitImg" to "提交上传图片",
            "getIrDetails" to "获取分组名字"
    )
}
// 参数名 to 作用,是否必传,备注
object Param {
    val register = mapOf(
            "uName" to "用户名,是,",
            "uPassword" to "密码,是,",
            "code" to "验证码,是,"
    )

    val sendCode = mapOf(
            "uName" to "用户名,是,"
    )

    val getCoverImg = mapOf(
            "pageNum" to "页码,否,从0开始",
            "itemCount" to "条目数,否,从1开始"
    )

    val getCoverImgDetailed = mapOf(
            "irType" to "封面标识,是",
            "pageNum" to "页码,否,从0开始",
            "itemCount" to "条目数,否,从1开始"
    )

    val updateImg = mapOf<String,String>()

    val commitImg = mapOf(
            "irDetails" to "分组名字,是,",
            "irUrl" to "作为封面的图片地址,否,第一次上传为必填参数",
            "urlJson" to "已上传的图片url集合json数据,是,"
    )

    val getIrDetails = mapOf<String,String>()
}