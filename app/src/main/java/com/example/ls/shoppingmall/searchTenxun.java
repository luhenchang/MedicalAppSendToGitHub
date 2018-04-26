package com.example.ls.shoppingmall;

import com.example.ls.shoppingmall.utils.HMACSHA1;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;

import java.net.URLEncoder;

/**
 * Created by 路很长~ on 2018/4/25.
 */

public class searchTenxun {
    public static void main(String argus[]) {
     /*   {
            'Action' : 'LexicalSynonym',
                'Nonce' : 345122,
                'Region' : 'sz',
                'SecretId' : 'AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA',
                'Timestamp' : 1408704141,
                'text': '周杰伦结婚'
        }  */

        // GETcvm.api.qcloud.com/v2/index.php?
        //Action=DescribeInstances&Nonce=11886&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1465185768&instanceIds.0=ins-09dx96dg&limit=20&offset=0

        String httpurl = "GETcvm.api.qcloud.com/v2/index.php?";
        String action = "LexicalSynonym";
        int nonce = (int) (Math.random() * 1000000);
        String region = "sz";
        String secretid = "AKIDDMPK80GfIm5HLGtSksqfJm5c5SgjKMK7";
        String secretkey = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qA";
        long timestamp = genTimeStamp();
        String text = "周杰伦结婚";
        String puString = httpurl + "Action=" + action + "&Nonce=" + nonce + "&Region=" + region + "&SecretId=" + secretid + "&Timestamp=" + timestamp + "&text" + text;
        String hah = "GETcvm.api.qcloud.com/v2/index.php?Action=DescribeInstances&Nonce=11886&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1465185768&instanceIds.0=ins-09dx96dg&limit=20&offset=0";
        String hashResult = HmacUtils.hmacSha1Hex(hah, secretkey);
        try {
            byte[] bytes = HMACSHA1.HmacSHA1Encrypt(hah, secretkey);
            System.out.println("1=" + byte2hex(bytes));
            String encodeResults = Base64.encodeBase64String(bytes);
            System.out.println("结果：" + encodeResults);
            String urlEncoder = URLEncoder.encode(encodeResults, "UTF-8");
            System.out.println("编码之后:" + urlEncoder);
            //NSI3UqqD99b%2FUJb4tbG%2FxZpRW64%3D
            //NSI3UqqD99b%2FUJb4tbG%2FxZpRW64%3D

            /*
            * https://wenzhi.api.qcloud.com/v2/index.php?
              Action=LexicalSynonym
              &Nonce=345122
              &Region=sz
              &SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA
              &Timestamp=1408704141
              &Signature=HgIYOPcx5lN6gz8JsCFBNAWp2oQ
              &text=周杰伦结婚
            *
            *
            * */

        } catch (Exception e) {


        }

    }

    //二行制转字符串
    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    // 微信获取时间戳参数
    private static long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }
}
