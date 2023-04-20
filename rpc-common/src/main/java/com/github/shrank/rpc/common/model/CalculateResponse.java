package com.github.shrank.rpc.common.model;

import java.io.Serializable;

/**
 * @author shrank
 * @version 1.0
 * @description 响应
 * @date 2023/4/20 16:13
 */
public class CalculateResponse implements Serializable {
    private static final long serialVersionUID = -1972014736222511341L;
    private boolean success;
    private int sum;

    public CalculateResponse() {
    }

    public CalculateResponse(boolean success, int sum) {
        this.success = success;
        this.sum = sum;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "CalculateResponse{" +
                "success=" + success +
                ", sum=" + sum +
                '}';
    }
}
