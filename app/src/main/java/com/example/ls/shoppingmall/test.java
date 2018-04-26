package com.example.ls.shoppingmall;

/**
 * Created by 路很长~ on 2018/4/25.
 */

import com.example.ls.shoppingmall.user.utils.MD5Util;
import com.example.ls.shoppingmall.utils.Base64UrlSafe;
import com.umeng.socialize.net.utils.Base64;

import java.io.UnsupportedEncodingException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class test {
    public static void main(String[] args) {
        /*
        * $secretKey = 'Gu5t9xGARNpq86cd98joQYCN3Cozk1qA';
          $srcStr = 'GETcvm.api.qcloud.com/v2/index.php?Action=DescribeInstances&Nonce=11886&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1465185768&instanceIds.0=ins-09dx96dg&limit=20&offset=0';
          $signStr = base64_encode(hash_hmac('sha1', $srcStr, $secretKey, true));
echo $signStr;
        *
        *
        * */

        String key = "Gu5t9xGARNpq86cd98joQYCN3Cozk1qA";
        String toHash = "GET" + "\n" + "Thu, 09 Aug 2012 13:33:46 +0000" + "\n" + "/ApiChannel/Report.m";
        String hah ="GETcvm.api.qcloud.com/v2/index.php?Action=DescribeInstances&Nonce=11886&Region=gz&SecretId=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA&Timestamp=1465185768&instanceIds.0=ins-09dx96dg&limit=20&offset=0";
        //String toHashUtf8 = URLEncoder.encode(toHash, "UTF-8");
        String res = hmac_sha1(hah, key);
       //$signStr = base64_encode(hash_hmac('sha1', $srcStr, $secretKey, true));
        System.out.print(res + "\n");

        String signature;
        try {
            //http://grepcode.com/file/repo1.maven.org/maven2/org.ops4j.pax.wicket/pax-wicket-service/0.7.15/org/apache/wicket/util/crypt/Base64UrlSafe.java/
            signature = Base64.encodeBase64String(res.getBytes());
            //  signature = new String(Base64UrlSafe.encodeBase64(res.getBytes()),"UTF-8");
            System.out.println(signature);
            signature = appendEqualSign(signature);
            System.out.print(signature);
            //NSI3UqqD99b%2FUJb4tbG%2FxZpRW64%3
           // MzUyMjM3NTJhYTgzZjdkNmZmNTA5NmY4YjViMWJmYzU5YTUxNWJhZQ==%3D
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hmac_sha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            String hexBytes = byte2hex(rawHmac);
            return hexBytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String byte2hex(final byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0xFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
        }
        return hs;
    }

    private static String appendEqualSign(String s) {
        int len = s.length();
        int appendNum = 8 - (int) (len / 8);
        for (int n = 0; n < appendNum; n++) {
            s += "%3D";
        }
        return s;
    }
}
