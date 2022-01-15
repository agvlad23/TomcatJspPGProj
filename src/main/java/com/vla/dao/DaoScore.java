package com.vla.dao;

import com.vla.classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DaoScore implements ScoresDao{
    private DaoScore(){

    }
    private static class SingletonHelper{
        private static final DaoScore INSTANCE=new DaoScore();
    }

    public static void main(String[] args) throws SQLException {
        var k1=new DaoScore();
        var k=k1.find(2);
        System.out.println(k);

    }

    @Override
    public Optional<Score> find(Integer id) throws SQLException {
        String sql = "SELECT u.name,sub.name,s.date,s.score FROM scores s " +
                "left join users u on s.id_user=u.id " +
                "left join subjects sub on s.id_subject=sub.id " +
                "WHERE s.id = ?";
        int idScore = 0;
       // int idSubject = 0;
      //  int idUser = 0;
        String nameSubject="";
        String nameUser="";
        double score=0.0;
        java.util.Date date=new Date();
        String name = "";
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultset = statement.executeQuery();

        if (resultset.next()) {
            score = resultset.getDouble("score");
            nameUser = resultset.getString(1);
            nameSubject = resultset.getString(2);
            date=resultset.getDate(3);
        }
        DataSourceFactory.close(conn);
        return Optional.of(new Score(date,score,nameUser,nameSubject));

    }

    @Override
    public List<Score> find(Stuff stuff) throws SQLException {
       return null;

    }

    @Override
    public List<Score> find(Subject subject) throws SQLException {
        return null;
    }

    @Override
    public List<Score> findAll() throws SQLException {
        String sql = "SELECT * FROM Score";
        int idScore = 0;
        List<Score> list=new ArrayList<>();

        String name = "";

        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            idScore = resultset.getInt("id");
            name = resultset.getString("name");
           // list.add(new com.vla.classes.Score(idScore, name));
        }
        DataSourceFactory.close(conn);
        return list;
    }



    @Override
    public boolean save(Score o) throws SQLException {
        String sql = "insert into Score(name) values (?)";
        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
       // statement.setString(1,o.name);
        isInserted= statement.executeUpdate()>0;
        DataSourceFactory.close(conn);

        return isInserted;
    }

    @Override
    public boolean update(Score o) throws SQLException {
        String sql = "update Score set name= ? " +
                "where id=?";

        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        isInserted= statement.executeUpdate()>0;

        DataSourceFactory.close(conn);
        return isInserted;
    }

    @Override
    public boolean delete(Score o) throws SQLException {
        String sql = "delete from Score " +
                "where id=?";

        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        isInserted= statement.executeUpdate()>0;

        DataSourceFactory.close(conn);
        return isInserted;
    }
}
