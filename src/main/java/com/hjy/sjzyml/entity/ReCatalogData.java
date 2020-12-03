package com.hjy.sjzyml.entity;

import lombok.Data;

/**
 * (ReCatalogData)表实体类
 *
 * @author makejava
 * @since 2020-12-02 15:00:50
 */
@Data
public class ReCatalogData {

    private String pkCatalogdataId;
    //目录ID
    private String fkCatalogId;
    //数据项ID
    private String fkDataId;

}