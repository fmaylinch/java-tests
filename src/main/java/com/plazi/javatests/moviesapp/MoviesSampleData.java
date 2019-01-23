package com.plazi.javatests.moviesapp;

import com.plazi.javatests.moviesapp.data.DataSourceUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Inits the DB and inserts sample data
 */
public class MoviesSampleData {

    public static void main(String[] args) throws SQLException {

        final DataSource dataSource = DataSourceUtil.getDataSource();

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/init.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/sample-data.sql"));
    }
}
