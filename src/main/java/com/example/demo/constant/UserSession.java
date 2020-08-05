package com.example.demo.constant;

public class UserSession {
    public final static ThreadLocal<String> LOGIN_INFO = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "admin";
        }
    };
}
