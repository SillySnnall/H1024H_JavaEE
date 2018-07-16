package silly.h1024h.entity


/**
 * Created by SillySnnall on 2018/5/18.
 * 热门数据
 */
data class HotData(
        // 数据库字段
        var _id: Int = 0,
        var type: String = "",
        var table_name: String = "",
        var hot_count: Int = 0)