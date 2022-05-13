package com.itheima.util;
/**
 * 响应JSON结果的类
 *
 * @param <T> 响应的数据的类型
 *
 */
public class ResponseResult <T>{
        private Integer state;
        private String message;
        private T data;

        public ResponseResult() {
            super();
        }

        public ResponseResult(Integer state) {
            super();
            this.state = state;
        }

        public ResponseResult(Integer state, T data) {
            super();
            this.state = state;
            this.data = data;
        }

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }

