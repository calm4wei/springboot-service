package com.zqykj.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.zqykj.service.datasource.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    DruidDataSource dataSource;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/v1/index");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        System.out.println("=============" + response.getBody());
        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }

    @Test
    public void getDatasource() throws SQLException {
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println("connection=" + connection);
        String sql = "select * from insight_source_host";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()) {
            String c2 = resultSet.getString(2);
            int c1 = resultSet.getInt(1);
            System.out.println(c1 + ", " + c2);
        }
        //assertThat(resultSet.next(), equalTo(true));
    }

    @Test
    public void getMyDatasource() throws SQLException {
        DruidPooledConnection connection = new DataSource().getInstance().getConnection();
        System.out.println("connection=" + connection);
        String sql = "select * from insight_source_host";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()) {
            String c2 = resultSet.getString(2);
            int c1 = resultSet.getInt(1);
            System.out.println(c1 + ", " + c2);
        }
        //assertThat(resultSet.next(), equalTo(true));
    }


}