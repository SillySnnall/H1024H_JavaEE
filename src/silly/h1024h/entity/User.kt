package silly.h1024h.entity

data class User(
        // 数据库
        var _id: Int = 0,
        var account: String = "",
        var password: String = "",
        var email: String = "",
        var token: String = "",
        var create_time: String = "",
        // 临时
        var code: String = ""
)
