package com.zqykj.service.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;

/**
 * Created by fengwei on 17/5/19.
 */
public class DataSource {

    public static DruidDataSource instance = null;

    private DataSource() {

    }

    public static DruidDataSource getInstance() {
        if (null == instance) {
            synchronized (DataSource.class) {
                if (null == instance) {
                    // TODO
                }
            }
        }
        return instance;
    }
}
