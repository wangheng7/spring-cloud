package com.ultrapower.framework.configuration.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by LXS on 2018/12/3.
 * 配置数据库获取属性方式后会要求每次加载数据源，无法适配native和subversion方式
 * 通过该类加载数据源，入口类屏蔽数据源加载
 *
 * ConditionalOnProperty
 * 通过其两个属性name以及havingValue来实现的，其中name用来从application.properties中读取某个属性值。
 * 如果该值为空，则返回false;
 * 如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。
 * 如果返回值为false，则该configuration不生效；为true则生效。
 */
@Configuration
@ConditionalOnProperty(name = "spring.profiles.active", havingValue="jdbc")
@Import({ DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
public class InitDataSourceConfiguration {

}
