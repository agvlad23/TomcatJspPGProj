package com.vla.controller;

import com.vla.classes.*;
import com.vla.dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreController extends HttpServlet {


    private static final Logger LOGGER = Logger.getLogger(ScoreController.class.getName());
    private final DaoScore scoreDao = DaoScore.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        LOGGER.log(Level.SEVERE, "  "+action);
        // action=a2;
        if (action==null)
            action="list";
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertScore(request, response);
                    break;
                case "delete":
                    deleteScore(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateScore(request, response);
                    break;
                case "users":
                    showUsers(request, response);
                default:
                    listScore(request, response);
                    break;
            }

        } catch (SQLException | ParseException e) {
            LOGGER.log(Level.SEVERE, "SQL EXCEPTION");
        }

    }

    private void listScore(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/ScoreList.jsp");
        List<Score> listScore= scoreDao.findAll();
        request.setAttribute("listScore",listScore);
        dispatcher.forward(request,response);

    }

    private void updateScore(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
        int id=Integer.parseInt(request.getParameter("id"));
        Double scoreNumber=Double.parseDouble(request.getParameter("score"));


        Score score=new Score(id,scoreNumber);
        scoreDao.update(score);
        response.sendRedirect("Score");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
        Integer id=Integer.parseInt(request.getParameter("id"));
        Optional<Score> existingStuff= scoreDao.find(id);
        RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/ScoreForm.jsp");
        existingStuff.ifPresent((s)->request.setAttribute("score",s));
        dispatcher.forward(request,response);
    }

    private void deleteScore(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));

        Score score=new Score(id);
        scoreDao.delete(score);
        response.sendRedirect("Score");
    }

    private void insertScore(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        Date date=null;

        String idUserString=request.getParameter("idUser");
        String idSubjectString=request.getParameter("idSubject");
        Integer idUser=null;
        Integer idSubject=null;
        if(idUserString!="")
            idUser=Integer.parseInt(idUserString);
        if(idSubjectString!="")
            idSubject=Integer.parseInt(idSubjectString);

        double valueScore=Double.parseDouble(request.getParameter("score"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dd=request.getParameter("date");
        if (request.getParameter("date")!="")
            date= new Date(format.parse(request.getParameter("date")).getTime());


        Score score=new Score(date,valueScore,idUser,idSubject);
        score.setNameUser(request.getParameter("nameUser"));
        score.setNameSubject(request.getParameter("nameSubject"));
        scoreDao.save(score);
        response.sendRedirect("Score");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/ScoreForm.jsp");
        dispatcher.forward(request,response);
    }
    private void showUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        response.sendRedirect("Stuff");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
