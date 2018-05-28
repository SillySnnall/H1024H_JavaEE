package silly.h1024h.service.impl

import javax.servlet.http.HttpServletRequest

interface GetIrDetailsServiceImpl {
    /**
     * 获取组信息
     */
    fun getIrDetails():List<String>
}