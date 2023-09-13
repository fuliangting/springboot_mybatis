package com.fu.springboot.util;

import com.fu.springboot.exceptions.ParamsException;

public class AssertUtil {
    /**
     * 判断结果是否为true
     * 如果结果为true，抛出异常
     * @param msg
     * @return
     */
      public static void isTrue(Boolean flag, String msg) {
          if (flag) {
              throw new ParamsException(msg);
          }
      }
}
