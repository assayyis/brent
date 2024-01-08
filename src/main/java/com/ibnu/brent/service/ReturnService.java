package com.ibnu.brent.service;

import com.ibnu.brent.dto.request.ReturnRequest;
import com.ibnu.brent.entity.Return;

public interface ReturnService {
    Return create(ReturnRequest returnRequest);
}
