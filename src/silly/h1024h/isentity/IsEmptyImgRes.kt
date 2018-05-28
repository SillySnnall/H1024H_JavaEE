package silly.h1024h.isentity

/**
 * User字段判空类
 */
object IsEmptyImgRes {

    fun isCover(imgRes: ImgRes): String{
        if (imgRes.irUrl.isNullOrEmpty()) return "请选择封面图片"
        return ""
    }

    fun isCommitImg(imgRes: ImgRes): String {
        if (imgRes.irDetails.isNullOrEmpty()) return "请填写分组名"
        if (imgRes.urlJson.isNullOrEmpty()) return "上传图片地址不能为空"
        return ""
    }
}