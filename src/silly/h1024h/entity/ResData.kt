package silly.h1024h.entity

import java.io.Serializable

/**
 * Created by SillySnnall on 2018/5/18.
 * 详情数据
 */
data class ResData(
        // 数据库字段
        var _id: Int = 0,
        var name: String = "",
        var url: String = "",
        var type: String = "",
        var is_cover: Int = 0,

        // 临时字段
        var table_name: String = "",
        var pageNum: Int = 0,
        var itemCount: Int = 0) {
    override fun toString(): String {
        return super.toString()
    }
}