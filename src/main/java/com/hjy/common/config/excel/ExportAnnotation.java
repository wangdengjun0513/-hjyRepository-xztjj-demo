package com.hjy.common.config.excel;

import java.lang.annotation.*;

/**
 * @author lzy
 * @Date: 2020-04-10
 * @Time: 14:07
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExportAnnotation {

    String value() default "";

    int col() default 0;



}
