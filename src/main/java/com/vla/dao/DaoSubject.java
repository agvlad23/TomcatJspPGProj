package com.vla.dao;

import com.vla.classes.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoSubject implements SubjectsDao{
    private DaoSubject(){

    }
    private static class SingletonHelper{
        private static final DaoSubject INSTANCE=new DaoSubject();
    }

    @Override
    public Optional<Subject> find(Integer id) throws SQLException {
        String sql = "SELECT * FROM subjects WHERE id = ?";
        int idSubject = 0;
        String name = "";
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultset = statement.executeQuery();

        if (resultset.next()) {
            idSubject = resultset.getInt("id");
            name = resultset.getString("name");
        }
        DataSourceFactory.close(conn);
        return Optional.of(new Subject(idSubject, name));

    }

    @Override
    public List<Subject> findAll() throws SQLException {
        String sql = "SELECT * FROM subjects";
        int idSubject = 0;
        List<Subject> list=new ArrayList<>();

        String name = "";

        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            idSubject = resultset.getInt("id");
            name = resultset.getString("name");
            list.add(new Subject(idSubject, name));
        }
        DataSourceFactory.close(conn);
        return list;
    }



    @Override
    public boolean save(Subject o) throws SQLException {
        String sql = "insert into subjects(name) values (?)";
        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,o.getName());
        isInserted= statement.executeUpdate()>0;
        DataSourceFactory.close(conn);

        return isInserted;
    }

    @Override
    public boolean update(Subject o) throws SQLException {
        String sql = "update subjects set name= ? " +
                "where id=?";

        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,o.getName());
        statement.setInt(2,o.getId());
        isInserted= statement.executeUpdate()>0;

        DataSourceFactory.close(conn);
        return isInserted;
    }

    @Override
    public boolean delete(Subject o) throws SQLException {
        String sql = "delete from subjects " +
                "where id=?";

        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,o.getId());
        isInserted= statement.executeUpdate()>0;

        DataSourceFactory.close(conn);
        return isInserted;
    }
}
