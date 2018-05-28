package silly.h1024h.service.impl

import javax.servlet.http.HttpServletRequest

interface UpdateServiceImpl {
    /**
     * 上传图片
     */
    fun updateImg(request: HttpServletRequest):List<String>
}