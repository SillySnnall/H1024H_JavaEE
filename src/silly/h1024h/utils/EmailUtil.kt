package silly.h1024h.utils

import java.util.*
import javax.mail.Session
import javax.mail.internet.MimeMessage
import javax.mail.internet.InternetAddress

import javax.mail.Message.RecipientType
import javax.mail.Transport


object EmailUtil {
    private var session: Session? = null


    init {
        // 创建邮件配置
        val props = Properties()
        props.setProperty("mail.transport.protocol", "smtp") // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", "smtp.gmail.com") // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", "465")
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props.setProperty("mail.smtp.auth", "true") // 需要请求认证
        props.setProperty("mail.smtp.ssl.enable", "true")// 开启ssl
        // 根据邮件配置创建会话，注意session别导错包
        session = Session.getDefaultInstance(props)
        // 开启debug模式，可以看到更多详细的输入日志
        session?.debug = true
    }


    /**
     * 发送验证码邮件
     */
    fun sendCodeEmail(toEmail: String): String {
        val randNumber: String
        val transport: Transport?
        try {// 获取6位随机数
            randNumber = Util.getRandNumber()
            // 根据会话创建邮件
            val msg = MimeMessage(session)
            // address邮件地址, personal邮件昵称, charset编码方式
            val fromAddress = InternetAddress("sillysnnall@gmail.com", "H1024H管理员", "utf-8")
            // 设置发送邮件方
            msg.setFrom(fromAddress)
            // 设置邮件接收方
            msg.setRecipient(RecipientType.TO, InternetAddress(toEmail, "", "utf-8"))
            // 设置邮件标题
            msg.setSubject("【H1024H】安全验证", "utf-8")
            msg.setText("您好，\n\n" +
                    "【H1024H】安全验证: $randNumber\n" +
                    "出于安全原因，该验证码将于30分钟后失效。请勿将验证码透露给他人。\n" +
                    "如果您没有进行该操作，请立即修改登录密码。\n\n" +
                    "H1024H团队\n" +
                    "http://www.h1024h.top")
            // 设置显示的发件时间
            msg.sentDate = Date()
            // 保存设置
            msg.saveChanges()
            //获取传输通道
            transport = session?.transport
            transport?.connect("smtp.gmail.com", "sillysnnall@gmail.com", "C0mputer0HK")
            //连接，并发送邮件
            transport?.sendMessage(msg, msg.allRecipients)
            transport?.close()
            System.out.println("验证码发送成功")
            return randNumber
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

}