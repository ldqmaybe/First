package com.cn.baselib.net;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author Admin
 * @time 2016/11/24 0024.14:33
 */
class LoggerInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        //获取请求链接的指定key的参数
//        String method = request.url().queryParameter("cmd");
//        String yct = PrefUtils.getString("SERVER_TIME","0");
//        String sign = method + SERVER_KEY + yct;
//        sign = Md5Utils.crypt(sign);
        HttpUrl newUrl = request.url().newBuilder()
                .addQueryParameter("sign","a0038a2efe62c64daa82addedad5d5d8")
                .addQueryParameter("yct","1480328201")//模拟数据
                .addQueryParameter("shopid", "39")
                .build();
        //http://yuecaninfo.com/services/api_boss.php?cmd=get_date&sign=c691934e14bcf492fbd08ef14f1a0bcb&yct=1480042317&shopid=39
        request = request.newBuilder().url(newUrl).build();
        printRequestLog(request);
        Response response = null;
        try {
            response = chain.proceed(request);
            printResponseLog(response);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 打印请求输出
     *
     * @param request 请求
     */
    private void printRequestLog(Request request) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(request.url() + "&");
        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
            for (int i = 0; i < formBody.size(); i++) {
                String name = URLDecoder.decode(formBody.encodedName(i), "utf-8");
                String value = URLDecoder.decode(formBody.encodedValue(i), "utf-8");
                if (!TextUtils.isEmpty(value)) {
                    sb.append(name)
                            .append("=")
                            .append(value)
                            .append("&");
                }
            }
        }
        String url = sb.deleteCharAt(sb.length() - 1).toString();
        Log.i("json", url);
    }

    /**
     * 打印请求返回数据
     *
     * @param response 响应
     */
    private void printResponseLog(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contenType = responseBody.contentType();
        if (contenType != null) {
            charset = contenType.charset(UTF8);
        }
        String msg = buffer.clone().readString(charset).replace("\r\n\r\n", "").replace("\r\n", "");
        Log.i("json", msg);
    }


}
