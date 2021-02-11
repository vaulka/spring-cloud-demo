package com.pongsky.cloud.web.request;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * request 工具类
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
public class RequestUtils {

    /**
     * 获取body数据
     *
     * @param request request
     * @return 获取body数据
     */
    public static String getBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                return stringBuilder.toString();
            }
        } catch (Exception ignored) {
        }
        return null;
    }

}
