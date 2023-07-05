package com.tztang.common.util;

import com.tztang.common.contanst.CodeConst;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultResponse extends HashMap<String, Object> {

    private static String code = "code";

    private static String msg = "msg";

    private static String data = "data";

    public static ResultResponse success(Object result) {
        ResultResponse map = new ResultResponse();
        map.put(code, CodeConst.ok);
        map.put(msg, "ok");
        map.put(data, result);
        return map;
    }

    public static ResultResponse error() {
        ResultResponse map = new ResultResponse();
        map.put(code, CodeConst.error);
        map.put(msg, "error");
        return map;
    }

    public static ResultResponse badRequest(String message) {
        ResultResponse map = new ResultResponse();
        map.put(code, CodeConst.badRequest);
        map.put(msg, message);
        return map;
    }

}
