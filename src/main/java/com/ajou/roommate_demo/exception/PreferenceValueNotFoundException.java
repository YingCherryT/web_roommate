package com.ajou.roommate_demo.exception;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.exception
 * @CLASS_NAME: PreferenceValueNotFoundException
 * @USER: BTS&ARMY
 * @Date 2024/11/10 11:06
 * @Version 1.0
 */
public class PreferenceValueNotFoundException extends RuntimeException {
    // 构造函数
    public PreferenceValueNotFoundException(String message) {
        super(message);  // 调用父类构造函数
    }
}
