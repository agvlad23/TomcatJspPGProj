package com.vla.controller;

import com.vla.classes.*;
import com.vla.dao.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StuffController extends HttpServlet {


    private static final Logger LOGGER = Logger.getLogger(StuffController.class.getName());
    private final DaoStaff stuffDao= DaoStaff.getInstance();
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
                    insertStuff(request, response);
                    break;
                case "delete":
                    deleteStuff(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateStaff(request, response);
                    break;
                case "scores":
                    showScores(request,response);
                case "secret":
                    insertUserAndScore(request,response);
                default:
                    listStuff(request, response);
                    break;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL EXCEPTION");
        }

    }

    private void listStuff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/StuffList.jsp");
        List<Stuff> listStuff=stuffDao.findAll();
        request.setAttribute("listStuff",listStuff);
        dispatcher.forward(request,response);

    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        RoleUser role= RoleUser.values()[Integer.parseInt(request.getParameter("role"))];

        Stuff stuff=new Stuff(id,name,role);
        stuffDao.update(stuff);
        response.sendRedirect("Stuff");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
    Integer id=Integer.parseInt(request.getParameter("id"));
    Optional<Stuff> existingStuff=stuffDao.find(id);
    RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/StuffForm.jsp");
    existingStuff.ifPresent((s)->request.setAttribute("stuff",s));
    dispatcher.forward(request,response);
    }

    private void deleteStuff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));

        Stuff stuff=new Stuff(id);
        stuffDao.delete(stuff);
        response.sendRedirect("Stuff");
    }

    private void insertStuff(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
        String name=request.getParameter("name");
        RoleUser role= RoleUser.values()[Integer.parseInt(request.getParameter("role"))];

        Stuff stuff=new Stuff(0,name,role);
        stuffDao.save(stuff);
        response.sendRedirect("Stuff");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/StuffForm.jsp");
        dispatcher.forward(request,response);
    }
    private void showScores(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        response.sendRedirect("Score");
    }
    private void insertUserAndScore(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Stuff stuff=new Stuff("secret Name",RoleUser.Teacher);
        Score score=new Score();
        score.setNameUser(stuff.getName());
        score.setNameSubject("DB");
        score.setScore(666);
        stuffDao.addUserAndScore(stuff,score);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
