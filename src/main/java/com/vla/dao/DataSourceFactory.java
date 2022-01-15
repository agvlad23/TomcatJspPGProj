package com.vla.dao;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSourceFactory {
    private final DataSource daso;
    private static final Logger LOGGER= Logger.getLogger(DataSourceFactory.class.getName());

    DataSourceFactory()  {


        PGSimpleDataSource daso;
        try {
             daso= new PGSimpleDataSource();
        }catch (Throwable e){
            LOGGER.log(Level.SEVERE, "Failed create pgsimple", e);
        }
         daso= new PGSimpleDataSource();
        String rootPath=
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                        .getResource("database.properties")).getPath();
        InputStream input=null;

        try {
            input=new FileInputStream(rootPath);
            Properties prop=new Properties();
            prop.load(input);
            daso.setDatabaseName(prop.getProperty("database"));
            daso.setServerNames(new String[]{prop.getProperty("servername")});
            daso.setPortNumbers(new int[]{Integer.parseInt(prop.getProperty("port"))});
            daso.setUser(prop.getProperty("user"));
            daso.setPassword(prop.getProperty("password"));
            daso.setCurrentSchema(prop.getProperty("schema"));
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE,"file properties not found",e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"ioError",e);
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.log(Level.SEVERE, "Failed to close stream", e);
                }
            }
        }

        this.daso=daso;
    }

    public static void close(Connection con) {
        try  {
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Failed to close Connection", e);
        }
    }

    public static Connection getConnection() throws SQLException{
       return SingletonHelper.INSTANCE.daso.getConnection();
    }

    private static class SingletonHelper{
        private static final DataSourceFactory INSTANCE=new DataSourceFactory();
    }
}
