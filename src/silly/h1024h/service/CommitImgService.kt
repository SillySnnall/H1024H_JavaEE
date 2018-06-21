package silly.h1024h.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import silly.h1024h.common.Config.RES_PATH
import silly.h1024h.dao.ResDataDao
import silly.h1024h.entity.ResData
import silly.h1024h.service.impl.CommitImgServiceImpl
import silly.h1024h.utils.FileUtil
import java.io.File

class CommitImgService : CommitImgServiceImpl {

    override fun isHaveCover(irDetails: String): Boolean {
//        return imgResDao.findByIrCoverIrDetails(1, irDetails).isNotEmpty()
        return false
    }

    private val imgResDao = ResDataDao()

    override fun saveImg(imgRes: ResData): Boolean {
//        val findByDetails = imgResDao.findByDetails(imgRes.irDetails)
//        // 判断是否有同组
//        var irType = if (findByDetails.isEmpty()) {
//            // 获取组的最大值
//            val irTypeMax = imgResDao.getIrTypeMax()
//            if (irTypeMax.isNotEmpty()) {
//                // 生成新的组id
//                irTypeMax[0].irType + 1
//            } else {
//                // 第一次提交图片
//                1
//            }
//        } else {
//            // 使用这一组
//            findByDetails[0].irType
//        }
//        // url json -> list
//        val urlList = Gson().fromJson<List<String>>(imgRes.urlJson, object : TypeToken<List<String>>() {}.type)
//        val imgResList = arrayListOf<ImgRes>()
//
//        for (url in urlList) {
//            // 判断中是设置封面
//            val imgResC = ImgRes(url, irType, if (url == imgRes.irUrl) 1 else 0, imgRes.irDetails)
//            val irUrl = imgResC.irUrl
//            imgResC.irUrl = irUrl.replace("/uploadfile", "/res/img")
//            if (!File("$RES_PATH/res/img").exists()) File("$RES_PATH/res/img").mkdirs()// 创建文件夹
//            if (FileUtil.cutFile(RES_PATH + irUrl, RES_PATH + imgResC.irUrl)) imgResList.add(imgResC)
//        }
//        return imgResDao.saveImg(imgResList)
        return false
    }
}