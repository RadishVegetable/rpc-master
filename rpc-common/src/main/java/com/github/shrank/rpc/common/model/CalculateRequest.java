package com.github.shrank.rpc.common.model;

import java.io.Serializable;

/**
 * @author shrank
 * @version 1.0
 * @description 请求
 * @date 2023/4/20 16:12
 */
public class CalculateRequest implements Serializable {
    private static final long serialVersionUID = 1235751004355300996L;

    private int a;

    private int b;

    public CalculateRequest() {
    }

    public CalculateRequest(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "CalculateRequest{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
