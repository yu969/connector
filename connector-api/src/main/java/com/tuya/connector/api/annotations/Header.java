package com.tuya.connector.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author qiufeng.yu@tuya.com
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Header {
  String value();
}
