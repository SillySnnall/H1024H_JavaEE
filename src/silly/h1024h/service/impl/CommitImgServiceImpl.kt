package silly.h1024h.service.impl

import silly.h1024h.entity.ResData
import javax.servlet.http.HttpServletRequest

interface CommitImgServiceImpl {
    /**
     * 存储图片
     */
    fun saveImg(imgRes: ResData):Boolean

    /**
     * 判断是否有封面
     */
    fun isHaveCover(irDetails: String):Boolean
}