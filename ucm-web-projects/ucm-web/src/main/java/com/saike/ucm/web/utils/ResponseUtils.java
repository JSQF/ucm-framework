package com.saike.ucm.web.utils;

import com.saike.ucm.web.domain.AjaxResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huawei on 12/28/15.
 */
public final class ResponseUtils {
    public static void responseJson(HttpServletResponse response, String json) throws IOException {
        if (json == null || response == null) {
            return;
        }
        byte[] data = json.getBytes();
        response.setContentLength(data.length);
        response.setContentType(Constants.MIME_TYPE_JSON);
        response.setCharacterEncoding(Constants.CHARACTER_SET_UTF8);
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }

    public  static <T> AjaxResponse<T> getAjaxResponse(int code, String message, T data) {
        AjaxResponse<T> ajaxResponse = new AjaxResponse<T>();
        ajaxResponse.setCode(code);
        ajaxResponse.setMessage(message);
        ajaxResponse.setData(data);

        return ajaxResponse;
    }
}
