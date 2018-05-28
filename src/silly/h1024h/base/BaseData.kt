package silly.h1024h.base

data class SuccessMap(var msg: Int, var param: String, var data: Map<String, Any>)
data class SuccessDataList(var msg: Int, var param: String, var data: List<Any>)
data class SuccessData(var msg: Int, var param: String, var data: String)
data class FailData(var msg: Int, var param: String)