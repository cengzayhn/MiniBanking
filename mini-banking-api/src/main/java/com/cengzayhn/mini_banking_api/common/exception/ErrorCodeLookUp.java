package com.cengzayhn.mini_banking_api.common.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeLookUp {

    private ErrorCodeLookUp() {}

    private static final Map<String, String> errorCodes;

    static {
        errorCodes = new HashMap<>();
        errorCodes.put("4001","BAD_REQUEST_WAREHOUSE_EMPTY_NAME_EXCEPTION");
        errorCodes.put("5000", "NOT_FOUND_EXCEPTION");
        errorCodes.put("5001", "CONFLICT_EXCEPTION");
    }

    public static String getMessageKey(String errorCode) {
        return errorCodes.getOrDefault(errorCode, "GENERIC_EXCEPTION");
    }
}