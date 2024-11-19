package org.example.study.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("tempCommandServiceImpl")
public class TempCommandServiceImpl implements TempCommandService {
}
