package org.tbwork.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(druidDataSource());
    }


    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();

        servletRegistrationBean.setServlet(new StatViewServlet());

        servletRegistrationBean.addUrlMappings("/druid/*");



        Map<String,String> params = new HashMap<String,String>();

        //账号密码，是否允许重置数据

        params.put("loginUsername","admin");

        params.put("loginPassword","admin");

        params.put("resetEnable","true");

        servletRegistrationBean.setInitParameters(params);

        return servletRegistrationBean;

    }


    @Bean
    public FilterRegistrationBean duridFilterRegistrationBean() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<String, String>();

        //设置忽略请求

        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");

        filterRegistrationBean.setInitParameters(initParams);

        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;

    }



    /**

     * 配置DataSource

     * @return

     * @throws SQLException

     */

    @Bean
    public DruidDataSource druidDataSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUsername("root");

        druidDataSource.setPassword("123456");

        druidDataSource.setUrl("jdbc:mysql://localhost:3306/anole");

        druidDataSource.setMaxActive(100);

        druidDataSource.setFilters("stat,wall");

        druidDataSource.setInitialSize(10);

        druidDataSource.setPoolPreparedStatements(true);
        return druidDataSource;

    }

}