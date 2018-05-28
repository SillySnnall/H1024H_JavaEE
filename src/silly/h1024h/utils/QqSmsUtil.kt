package silly.h1024h.utils

import java.io.IOException
import org.json.JSONException
import com.github.qcloudsms.SmsSingleSenderResult
import com.github.qcloudsms.SmsSingleSender
import com.github.qcloudsms.httpclient.HTTPException


object QqSmsUtil{
    // 短信应用SDK AppID
    var appid = 1400081368 // 1400开头

    // 短信应用SDK AppKey
    var appkey = "fa19738a0d3c5ef759a8a5df160f0921"

    // 需要发送短信的手机号码
    var phoneNumbers = "173749177"

    // 短信模板ID，需要在短信应用中申请
    var templateId = 7839 // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

    // 签名
    var smsSign = "" // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`

    /**
     * 发送短信
     */
    fun sendSMS(){
        try {
            val ssender = SmsSingleSender(appid, appkey)
            val result = ssender.send(0, "60", phoneNumbers,
                    "【腾讯云】您的验证码是: 5678", "", "")
//            val result = ssender.sendWithParam("60", phoneNumbers,
//                    templateId, arrayOf(), smsSign, "", "")  // 签名参数未提供或者为空时，会使用默认签名发送短信
            print(result)
        } catch (e: HTTPException) {
            // HTTP响应码错误
            e.printStackTrace()
        } catch (e: JSONException) {
            // json解析错误
            e.printStackTrace()
        } catch (e: IOException) {
            // 网络IO错误
            e.printStackTrace()
        }

    }
}