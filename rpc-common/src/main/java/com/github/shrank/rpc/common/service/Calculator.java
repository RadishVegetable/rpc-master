package com.github.shrank.rpc.common.service;

import com.github.shrank.rpc.common.model.CalculateRequest;
import com.github.shrank.rpc.common.model.CalculateResponse;

/**
 * @author shrank
 * @version 1.0
 * @description
 * @date 2023/4/20 16:17
 */
public interface Calculator {

    CalculateResponse sum(CalculateRequest request);
}
