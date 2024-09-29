package com.raticuliin.cashflow.utils;

import com.raticuliin.cashflow.utils.data.ErrorResponse;
import org.springframework.http.HttpStatus;

public class Utils {

    public static ErrorResponse getErrorResponse(Throwable throwable, HttpStatus httpStatus) {

        return ErrorResponse.builder()
                .code(httpStatus.value())
                .message(throwable.getMessage())
                .build();
    }

}
