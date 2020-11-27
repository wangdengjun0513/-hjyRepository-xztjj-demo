package com.hjy.common.config.excel;


import java.lang.annotation.*;

/**
 * @author lzy
 * @Date: 2020-04-14
 * @Time: 10:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExportTypeAnnotation {

    Class<? extends ExportTypeHandler> typeHandler();
}
