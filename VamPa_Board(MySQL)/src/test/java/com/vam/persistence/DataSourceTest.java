package com.vam.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@RunWith(JUnit4ClassRunner.class)
@Configuration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {
 
    @Autowired
    private DataSource dataSource;
    
    @Test
    public void testConnection() {
      
        try(Connection con = dataSource.getConnection();){
            
            System.out.println("con = " + con);
            
        } catch(Exception e) {
            
            e.printStackTrace();
            
        }
        
    }
    
}