package com.myproject.utils;//package cn.ewhale.box.Util;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 * <p>
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class AlSmsHelper {
    //    private  static final Logger logger =  LoggerFactory.getLogger(AlSmsHelper.class);
    //用户注册
    private static final String REGISTERATIONAL_TEMPLATE_CODE = "SMS_174435373";
    //身份确认
    private static final String CONFIRM_TEMPLATE_CODE = "SMS_169540111";
    //修改密码
    private static final String UPDATEPASSWORD_TEMPLATE_CODE = "SMS_182671599";
    //    private static final String UPDATEPASSWORD_TEMPLATE_CODE = "SMS_169540107";
    //重要信息变更
    private static final String UPDATE_TEMPLATE_CODE = "SMS_169540106";
    //登录
    private static final String LOGIN_TEMPLATE_CODE = "SMS_169540110";

    /**
     * 发送短信
     *
     * @param phone    手机号码
     * @param code     验证码
     * @param codeType 短信模板
     * @return 发送状态
     * @throws Exception 错误类型
     */
    public static String sendSms(String phone, String code, String codeType, String name, String reason)
            throws Exception {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", AliSmsConfig.ACCESS_KEY_ID, AliSmsConfig.ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", AliSmsConfig.PRODUCT, AliSmsConfig.DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("鑫彩全卫设计师");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(codeType);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"customer\":\"Tom\", \"code\":\"" + code + "\"}");

        request.setTemplateParam("{\"code\":\"" + code + "\",\"name\":\"" + name + "\",\"reason\":\"" + reason + "\"}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //hint 此处可能会抛出异常，注意catch
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println("sendSmsResponse = " + sendSmsResponse.getCode());
            System.out.println("sendSmsResponse = " + sendSmsResponse.getMessage());

            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                System.out.println("成功");
                return "200";
            } else {
                System.out.println("失败");
                return "发送验证码失败";
            }
        } catch (Exception e) {
            return "发送验证码失败";
        }

    }

    public static String sendRegisterationCaptcha(String phoneNum, String verify) throws Exception {
        return sendSms(phoneNum, verify, REGISTERATIONAL_TEMPLATE_CODE, null, null);
    }

    public static String sendConfirmCaptcha(String phoneNum, String verify) throws Exception {
        return sendSms(phoneNum, verify, CONFIRM_TEMPLATE_CODE, null, null);
    }

    public static String sendUpdatePasswordCaptcha(String phoneNum, String verify) throws Exception {
        return sendSms(phoneNum, verify, UPDATEPASSWORD_TEMPLATE_CODE, null, null);
    }

    public static String sendUpdateCaptcha(String phoneNum, String verify) throws Exception {
        return sendSms(phoneNum, verify, UPDATE_TEMPLATE_CODE, null, null);
    }

    public static String sendLoginCaptcha(String phoneNum, String verify) throws Exception {
        return sendSms(phoneNum, verify, LOGIN_TEMPLATE_CODE, null, null);
    }

    /**
     * 生成6位随机数字的字符串
     *
     * @return String 6位数字字符串
     */
    public static String generate6BitDigital() {
        return ("" + (Math.random() + 0.1) * 1000000).substring(0, 6);
    }


    public static void main(String[] args) throws Exception {
        sendUpdatePasswordCaptcha("13612447497", "123456");
    }

}
