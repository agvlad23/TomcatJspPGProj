package com.vla.dao;

import com.vla.classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoStaff implements StaffDao{

    private DaoStaff(){

    }
    private static class SingletonHelper{
        private static final DaoStaff INSTANCE=new DaoStaff();
    }
    public static DaoStaff getInstance(){
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Stuff> find(Integer id) throws SQLException {
        String sql = "SELECT id,name,role FROM users WHERE id = ?";
        int id_stuff = 0;
        RoleUser role = RoleUser.ERROR;
        String name = "";
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultset = statement.executeQuery();

        if (resultset.next()) {
            id_stuff = resultset.getInt("id");
            name = resultset.getString("name");
            role = RoleUser.values()[resultset.getInt("role")];
        }
        DataSourceFactory.close(conn);
        return Optional.of(new Stuff(id_stuff, name, role));

    }

    @Override
    public List<Stuff> findAll() throws SQLException {
        String sql = "SELECT id,name,role FROM users";
        int id_stuff = 0;
        List<Stuff> list=new ArrayList<>();
        RoleUser role = RoleUser.ERROR;
        String name = "";

        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            id_stuff = resultset.getInt("id");
            name = resultset.getString("name");
            role = RoleUser.values()[resultset.getInt("role")];
            list.add(new Stuff(id_stuff, name, role));
        }
        DataSourceFactory.close(conn);
        return list;
    }

    @Override
    public boolean save(Stuff o) throws SQLException {
        String sql = "insert into users(name,role) values (?,?)";
        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,o.getName());
        statement.setInt(2,o.getRole().getValue());
        isInserted= statement.executeUpdate()>0;
        DataSourceFactory.close(conn);

        return isInserted;
    }

    @Override
    public boolean update(Stuff o) throws SQLException {
        String sql = "update users set name= ?,role=?" +
                "where id=?";

        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,o.getName());
        statement.setInt(2,o.getRole().getValue());
        statement.setInt(3,o.getId());
        isInserted= statement.executeUpdate()>0;

        DataSourceFactory.close(conn);
        return isInserted;
    }

    @Override
    public boolean delete(Stuff o) throws SQLException {
        String sql = "delete from users " +
                "where id=?";

        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,o.getId());
        isInserted= statement.executeUpdate()>0;

        DataSourceFactory.close(conn);
        return isInserted;
    }

    public boolean addUserAndScore(Stuff stuff,Score score){

        return true;
    }
}
