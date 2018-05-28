package silly.h1024h.service

import org.apache.commons.fileupload.FileItem
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload
import silly.h1024h.common.Config
import silly.h1024h.service.impl.UpdateServiceImpl
import silly.h1024h.utils.Util
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.servlet.http.HttpServletRequest

class UpdateService : UpdateServiceImpl {
    override fun updateImg(request: HttpServletRequest): List<String> {
        //获得磁盘文件条目工厂
        val factory = DiskFileItemFactory()
//        val imgDir = "/res/img"
        val imgDir = "/uploadfile"
        //获取文件需要上传到的路径
        val path = request.getRealPath(imgDir)
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs()
        }
        factory.repository = file
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory.sizeThreshold = 1024 * 4096
        val upload = ServletFileUpload(factory)
        var out: FileOutputStream? = null
        var fis: InputStream? = null
        try {
            val list = upload.parseRequest(request) as List<FileItem>
            if (list.isEmpty()) return arrayListOf()
            val urlPath = arrayListOf<String>()
            for (item in list) {
                // 拦截不是图片的文件
                if (item.contentType != "image/png" && item.contentType != "image/jpeg") {
                    item.delete()
                    continue
                }
                // 判断表单项是file 类型的
                if (!item.isFormField) {
                    //获取路径名
                    val value = item.name
                    //索引到最后一个反斜杠
                    val start = value.lastIndexOf("\\")
                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，
                    var filename = value.substring(start + 1)
                    filename = Util.getUUID() + filename.substring(filename.lastIndexOf("."))
                    //手动写的
                    out = FileOutputStream(File(path, filename))
                    fis = item.inputStream
                    val buf = ByteArray(1024)
                    var length = fis.read(buf)
                    while (length != -1) {
                        out.write(buf, 0, length)
                        length = fis.read(buf)
                    }
                    out.flush()
                    fis.close()
                    out.close()
                    urlPath.add("$imgDir/$filename")
                }
            }
            return urlPath
        } catch (e: Exception) {
            e.printStackTrace()
            fis?.close()
            out?.close()
            return arrayListOf()
        }
    }
}