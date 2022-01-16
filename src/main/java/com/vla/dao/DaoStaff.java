package com.vla.dao;

import com.vla.classes.*;

import javax.lang.model.element.NestingKind;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DaoStaff implements StaffDao {

    private DaoStaff() {

    }

    private static class SingletonHelper {
        private static final DaoStaff INSTANCE = new DaoStaff();
    }

    public static DaoStaff getInstance() {
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
        String sql = "SELECT u.id,u.name,sub.name,avg(score),u.role FROM users u " +
                "left join scores s on s.id_user=u.id " +
                "left join user_subject us on us.id_user = s.id_user and us.id_subject =s.id_subject " +
                "left join subjects sub on sub.id=us.id_subject " +
                "group by u.id,sub.name ";

        int id_stuff = 0;
        Map<Integer,Stuff> list = new HashMap<>();
        RoleUser role = RoleUser.ERROR;
        String name = "";
        Double avgScore=0.0;
        String subjectName="";
        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultset = statement.executeQuery(sql);

        while (resultset.next()) {
            id_stuff = resultset.getInt("id");
            name = resultset.getString("name");
            role = RoleUser.values()[resultset.getInt("role")];
            avgScore=resultset.getDouble("avg");
            subjectName=resultset.getString(3);
            Double finalAvgScore = avgScore;
            String finalSubjectName = subjectName;
            list.computeIfPresent(id_stuff,(k, v)-> v.addToSubjects(finalSubjectName,finalAvgScore));
            list.putIfAbsent(id_stuff,new Stuff(id_stuff, name, role,avgScore,subjectName) );
            //new Stuff(id_stuff, name, role,avgScore));
        }
        DataSourceFactory.close(conn);
        return new ArrayList<>(list.values());
    }

    @Override
    public boolean save(Stuff o) throws SQLException {
        String sql = "insert into users(name,role) values (?,?)";
        boolean isInserted = false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, o.getName());
        statement.setInt(2, o.getRole().getValue());
        isInserted = statement.executeUpdate() > 0;
        DataSourceFactory.close(conn);

        return isInserted;
    }

    @Override
    public boolean update(Stuff o) throws SQLException {
        String sql = "update users set name= ?,role=?" +
                "where id=?";

        boolean isInserted = false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, o.getName());
        statement.setInt(2, o.getRole().getValue());
        statement.setInt(3, o.getId());
        isInserted = statement.executeUpdate() > 0;

        DataSourceFactory.close(conn);
        return isInserted;
    }

    @Override
    public boolean delete(Stuff o) throws SQLException {
        String sql = "delete from users " +
                "where id=?";

        boolean isInserted = false;


        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, o.getId());
        isInserted = statement.executeUpdate() > 0;

        DataSourceFactory.close(conn);
        return isInserted;
    }

    public boolean addUserAndScore(Stuff stuff, Score score) throws SQLException {

        String sql;
        boolean isInserted = false;
        Connection conn = DataSourceFactory.getConnection();
        try {
            sql = "Set TRANSACTION ISOLATION  LEVEL REPEATABLE READ; " +
                    "insert into users(name,role) values (?,?);";


            conn.setAutoCommit(false);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, stuff.getName());
            statement.setInt(2, stuff.getRole().getValue());
            isInserted = statement.executeUpdate() > 0;


            int idUser = 0;
            int idSubject = 0;

            if (score.getIdUser() == null && score.getNameUser() != null && !score.getNameUser().equals("")) {

                sql = "select id from users " +
                        "where name=? " +
                        "limit 1";
                statement = conn.prepareStatement(sql);
                statement.setString(1, score.getNameUser());
                ResultSet resultset = statement.executeQuery();

                if (resultset.next())
                    idUser = resultset.getInt("id");

            } else
                idUser = score.getIdUser();

            if (score.getIdSubject() == null && score.getNameSubject() != null && !score.getNameSubject().equals("")) {

                sql = "select id from subjects " +
                        "where name=? " +
                        "limit 1";
                statement = conn.prepareStatement(sql);
                statement.setString(1, score.getNameSubject());
                ResultSet resultset = statement.executeQuery();

                if (resultset.next())
                    idSubject = resultset.getInt("id");

            } else
                idSubject = score.getIdSubject();

            String date = "";
            String dateQuestionMark = "";
            int counterQuestionMark = 0;
            if (score.getDate() != null) {
                date = "date,";
                dateQuestionMark = "?,";
                counterQuestionMark = 1;
            }

            sql = "insert into scores(" + date + "score,id_user,id_subject) values (" + dateQuestionMark + "?,?,?)";
            statement = conn.prepareStatement(sql);
            if (score.getDate() != null)
                statement.setDate(1, (java.sql.Date) score.getDate());

            statement.setDouble(1 + counterQuestionMark, score.getScore());
            statement.setInt(2 + counterQuestionMark, idUser);
            statement.setInt(3 + counterQuestionMark, idSubject);
            isInserted = statement.executeUpdate() > 0;

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException excep) {
                    e.printStackTrace();
                }
            }
        } finally {
            conn.setAutoCommit(true);
        }

        DataSourceFactory.close(conn);

        return isInserted;
    }
}
