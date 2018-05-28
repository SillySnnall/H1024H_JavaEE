package silly.h1024h.web

import silly.h1024h.base.BaseServlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import silly.h1024h.common.ErrorEnumMsg
import silly.h1024h.common.ErrorEnumParam
import silly.h1024h.isentity.IsEmptyImgRes
import silly.h1024h.service.CommitImgService


@WebServlet(name = "CommitImgServlet", urlPatterns = ["/commitImg"])
class CommitImgServlet : BaseServlet<ImgRes>() {
    override fun getModel(): ImgRes? {
        return ImgRes()
    }

    private val commitImgService = CommitImgService()
    /**
     * 提交上传的图片
     */
    override fun doWork(request: HttpServletRequest, response: HttpServletResponse, model: ImgRes?) {
        // 判空
        val isImgRes = IsEmptyImgRes.isCommitImg(model!!)
        if (isImgRes.isNotEmpty()) {
            failData(ErrorEnumMsg.error1002, isImgRes)
            return
        }
        val isCover = IsEmptyImgRes.isCover(model)
        // 判断这个组里有没有封面
        val haveCover = commitImgService.isHaveCover(model.irDetails)
        if(!haveCover){
            if(isCover.isNotEmpty()){
                failData(ErrorEnumMsg.error1002, isCover)
            }
        }else{
            // 已经有封面，不设置
            model.irUrl = ""
        }
        // 存储数据
        if (commitImgService.saveImg(model)) successData("上传成功")
        else failData(ErrorEnumMsg.error1007, ErrorEnumParam.error1007)
    }
}
