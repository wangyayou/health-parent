package com.itcast.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class AliYunUtils {
    public static  final String MUBAN_VALIDATE_CODE="SMS_195862135";
    public static  final String MUBAN_SUCCESS_CODE="SMS_195862137";
    public static  final String SIGN_NAME="七里香";
    public static final String A_KEY="LTAI4GFWShpXggZCWYpKMmmi";
    public static final String S_KEY="EQP8NzXH6z53xrwrP1n0GjO82AL4pJ";

    public static  void SendValidateCode(String MUBAN_VALIDATE_CODE,String SIGN_NAME,String CODE){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", A_KEY, S_KEY);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "18154177736");
        request.putQueryParameter("SignName", "七里香");
        request.putQueryParameter("TemplateCode", "SMS_195862135");
        request.putQueryParameter("TemplateParam", CODE);
       /* request.putQueryParameter("SmsUpExtendCode", "aaaa");
        request.putQueryParameter("OutId", "wwww");*/
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
