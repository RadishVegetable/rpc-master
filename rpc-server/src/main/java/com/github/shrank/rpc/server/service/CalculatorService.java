package com.github.shrank.rpc.server.service;

import com.github.shrank.rpc.common.model.CalculateRequest;
import com.github.shrank.rpc.common.model.CalculateResponse;
import com.github.shrank.rpc.common.service.Calculator;

/**
 * @author shrank
 * @version 1.0
 * @description
 * @date 2023/4/20 16:37
 */
public class CalculatorService implements Calculator {
    @Override
    public CalculateResponse sum(CalculateRequest request) {
        int sum = request.getA() + request.getB();
        return new CalculateResponse(true, sum);
    }
}
