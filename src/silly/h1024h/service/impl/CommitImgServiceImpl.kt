package silly.h1024h.service.impl

import javax.servlet.http.HttpServletRequest

interface CommitImgServiceImpl {
    /**
     * 存储图片
     */
    fun saveImg(imgRes: ImgRes):Boolean

    /**
     * 判断是否有封面
     */
    fun isHaveCover(irDetails: String):Boolean
}