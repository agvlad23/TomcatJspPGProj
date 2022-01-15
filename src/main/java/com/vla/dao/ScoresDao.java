package com.vla.dao;

import com.vla.classes.*;

import java.sql.SQLException;
import java.util.List;

public interface ScoresDao extends Dao<Score,Integer>{

    List<Score> find(Stuff stuff) throws SQLException;
    List<Score> find(Subject subject) throws SQLException;

   // List<com.vla.classes.Subject> find(Date date) throws SQLException;

}
