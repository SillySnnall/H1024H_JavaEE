package silly.h1024h.base

import com.google.gson.Gson
import org.apache.commons.beanutils.BeanUtils
import silly.h1024h.utils.DesUtil
import javax.servlet.http.HttpServletResponse

abstract class BaseService<T>(private val response: HttpServletResponse, private var map: Map<String, Any>, var model: T) {

    init {
        init()
    }

    private fun init() {
        BeanUtils.populate(model, map)
        if (isEmpty()) mainService()
    }

    /**
     * 判空
     */
    abstract fun isEmpty(): Boolean

    /**
     * 业务处理
     */
    abstract fun mainService(): Boolean

    /**
     * 返回成功的数据(加密)
     */
    fun successData(str: String): Boolean {
        val writer = response.writer
        if (str.isNotEmpty() && (str.substring(0, 1) == "[" || str.substring(0, 1) == "{"))
            writer.write(DesUtil.encrypt("{\"msg\": 0,\"param\": \"msgok\",\"data\": $str}"))
        else
            writer.write(DesUtil.encrypt(Gson().toJson(SuccessData(0, "msgok", str))))
        writer.flush()
        writer.close()
        return true
    }


    /**
     * 返回失败的数据(加密)
     */
    fun failData(msg: Int, param: String): Boolean {
        val writer = response.writer
        val encrypt = DesUtil.encrypt(Gson().toJson(FailData(msg, param)))// 加密
        writer.write(encrypt)
        writer.flush()
        writer.close()
        return false
    }
}
