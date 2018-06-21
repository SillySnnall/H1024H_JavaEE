package silly.h1024h.web

import silly.h1024h.base.BaseServlet
import silly.h1024h.entity.ResData
import silly.h1024h.service.ResDataService
import silly.h1024h.service.TypeListService

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 获取分类列表
 */
@WebServlet(name = "GetTypeListServlet", urlPatterns = ["/get_type_list"])
class GetTypeListServlet : BaseServlet<Any>() {
    private val typeListService = TypeListService()
    override fun getModel(): Any? {
        return null
    }
    /**
     * 获取封面
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: Any?) {
        successData(typeListService.getTypeList())
    }
}
