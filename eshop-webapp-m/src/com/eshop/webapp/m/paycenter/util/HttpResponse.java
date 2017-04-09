package com.eshop.webapp.m.paycenter.util;


import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;

import com.eshop.webapp.m.paycenter.config.WeixinpayConfig;


/**
 * 
 * @ClassName: HttpResponse 
 * @Description: 封装返回结果 
 * @author Jun.Zhang 
 * @date 2015-4-10 上午10:55:49 
 *
 */

public class HttpResponse {

    /**
     * 返回中的Header信息
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String   stringResult;

    /**
     * btye类型的result
     */
    private byte[]   byteResult;

    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public byte[] getByteResult() {
        if (byteResult != null) {
            return byteResult;
        }
        if (stringResult != null) {
            return stringResult.getBytes();
        }
        return null;
    }

    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    public String getStringResult() throws UnsupportedEncodingException {
        if (stringResult != null) {
            return stringResult;
        }
        if (byteResult != null) {
            return new String(byteResult, WeixinpayConfig.charset);
        }
        return null;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

}
