package org.example.study.service.TempService;

import lombok.RequiredArgsConstructor;
import org.example.study.apiPayload.code.status.ErrorStatus;
import org.example.study.apiPayload.exception.handler.TempHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("tempQueryServiceImpl")
public class TempQueryServiceImpl implements TempQueryService{
    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
