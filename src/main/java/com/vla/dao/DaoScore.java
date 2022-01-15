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
    public static DaoScore getInstance(){
        return DaoScore.SingletonHelper.INSTANCE;
    }

    public static void main(String[] args) throws SQLException {
        var k1=new DaoScore();
        var k=k1.find(2);
        System.out.println(k);

    }

    @Override
    public Optional<Score> find(Integer id) throws SQLException {
        String sql = "SELECT u.name,sub.name,s.date,s.score,s.id FROM scores s " +
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
            idScore=resultset.getInt(5);
        }
        DataSourceFactory.close(conn);
        return Optional.of(new Score(idScore,date,score,nameUser,nameSubject));

    }

    @Override
    public List<Score> find(Stuff stuff) throws SQLException {
        String sql = "SELECT u.name,sub.name,s.date,s.score,s.id FROM scores s " +
                "left join users u on s.id_user=u.id " +
                "left join subjects sub on s.id_subject=sub.id " +
                "WHERE u.id = ? OR u.name=?";
        int idScore = 0;
        // int idSubject = 0;
        //  int idUser = 0;
        String nameSubject="";
        String nameUser="";
        double score=0.0;
        java.util.Date date=new Date();
        String name = "";
        List<Score> list=new ArrayList<>();

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, stuff.getId());
        statement.setString(2, stuff.getName());
        ResultSet resultset = statement.executeQuery();

        if (resultset.next()) {
            score = resultset.getDouble("score");
            nameUser = resultset.getString(1);
            nameSubject = resultset.getString(2);
            date=resultset.getDate(3);
            idScore=resultset.getInt(5);
            list.add(new Score(idScore,date,score,nameUser,nameSubject));
        }
        DataSourceFactory.close(conn);
        return list;

    }

    @Override
    public List<Score> find(Subject subject) throws SQLException {
        String sql = "SELECT u.name,sub.name,s.date,s.score,s.id FROM scores s " +
                "left join users u on s.id_user=u.id " +
                "left join subjects sub on s.id_subject=sub.id " +
                "WHERE u.id = ? OR u.name=?";
        int idScore = 0;
        // int idSubject = 0;
        //  int idUser = 0;
        String nameSubject="";
        String nameUser="";
        double score=0.0;
        java.util.Date date=new Date();
        String name = "";
        List<Score> list=new ArrayList<>();

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, subject.getId());
        statement.setString(2, subject.getName());
        ResultSet resultset = statement.executeQuery();

        if (resultset.next()) {
            score = resultset.getDouble("score");
            nameUser = resultset.getString(1);
            nameSubject = resultset.getString(2);
            date=resultset.getDate(3);
            idScore=resultset.getInt(5);
            list.add(new Score(idScore,date,score,nameUser,nameSubject));
        }
        DataSourceFactory.close(conn);
        return list;
    }

    @Override
    public List<Score> findAll() throws SQLException {
        String sql = "SELECT u.name,sub.name,s.date,s.score,s.id FROM scores s " +
                "left join users u on s.id_user=u.id " +
                "left join subjects sub on s.id_subject=sub.id ";
        int idScore = 0;
        String nameSubject="";
        String nameUser="";
        double score=0.0;
        java.util.Date date=new Date();
        List<Score> list=new ArrayList<>();



        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            score = resultset.getDouble("score");
            nameUser = resultset.getString(1);
            nameSubject = resultset.getString(2);
            date=resultset.getDate(3);
            idScore=resultset.getInt(5);
            list.add(new Score(idScore,date,score,nameUser,nameSubject));
        }
        DataSourceFactory.close(conn);
        return list;
    }



    @Override
    public boolean save(Score o) throws SQLException {
        String sql ;
        boolean isInserted=false;
        Connection conn = DataSourceFactory.getConnection();
        int idUser=0;
        int idSubject=0;

        if(o.getIdUser()==null && o.getNameUser()!=null && !o.getNameUser().equals("")) {

            sql = "select id from users " +
                    "where name=? " +
                    "limit 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,o.getNameUser());
            ResultSet resultset = statement.executeQuery();

            if (resultset.next())
                idUser = resultset.getInt("id");

        }else
            idUser=o.getIdUser();

        if(o.getIdSubject()==null && o.getNameSubject()!=null && !o.getNameSubject().equals("")) {

            sql = "select id from subjects " +
                    "where name=? " +
                    "limit 1";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,o.getNameSubject());
            ResultSet resultset = statement.executeQuery();

            if (resultset.next())
                idSubject = resultset.getInt("id");

        }else
            idSubject=o.getIdSubject();

        String date="";
        String dateQuestionMark="";
        int counterQuestionMark=0;
        if (o.getDate()!=null) {
            date = "date,";
            dateQuestionMark="?,";
            counterQuestionMark=1;
        }

        sql= "insert into scores("+date+"score,id_user,id_subject) values ("+dateQuestionMark+"?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        if (o.getDate()!=null)
            statement.setDate(1, (java.sql.Date)o.getDate());

        statement.setDouble(1+counterQuestionMark,o.getScore());
        statement.setInt(2+counterQuestionMark,idUser);
        statement.setInt(3+counterQuestionMark,idSubject);
        isInserted= statement.executeUpdate()>0;
        DataSourceFactory.close(conn);

        return isInserted;
    }

    @Override
    public boolean update(Score o) throws SQLException {
        String sql = "update scores set score=? " +
                "where id=?";

        boolean isInserted=false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDouble(1,o.getScore());
        statement.setInt(2,o.getId());
        isInserted= statement.executeUpdate()>0;

        DataSourceFactory.close(conn);
        return isInserted;
    }

    @Override
    public boolean delete(Score o) throws SQLException {
        String sql = "delete from scores " +
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
