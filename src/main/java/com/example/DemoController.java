package com.example;

import org.postgresql.Driver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 */
@RestController
public class DemoController {

    @RequestMapping("/")
    public String home() {
        return "Hello" +
            "<p>Test the database connection through a SOCKS proxy by clicking: <a href=\"/test\">/test</a></p>";
    }

    @RequestMapping("/test")
    public String test() {
        try {
            String jdbUrl = System.getenv("JDBC_DATABASE_URL");
            Connection conn = new Driver().connect(jdbUrl, new Properties());
            DatabaseMetaData md = conn.getMetaData();
            ResultSet tables = md.getTables(null, "%", "%",
                new String[]{"TABLE"});
            List<String> results = new ArrayList<String>();
            while (tables.next()) {
              String tableName = tables.getString("TABLE_NAME");
              results.add(tableName);
            }
            tables.close();
            return "Tables found: " + results.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
