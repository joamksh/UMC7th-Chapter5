package org.example.study.apiPayload.exception.handler;

import org.example.study.apiPayload.code.BaseErrorCode;
import org.example.study.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
