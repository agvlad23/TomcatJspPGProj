package com.vla.controller;

import com.vla.classes.*;
import com.vla.dao.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/Stuff")
public class StuffController extends HttpServlet {


    private static final Logger LOGGER = Logger.getLogger(StuffController.class.getName());
    private final DaoStaff stuffDao= DaoStaff.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        String a2=request.getParameter("action");
        LOGGER.log(Level.SEVERE, a2+"  "+action);
        // action=a2;
        if (a2!=null)
            action=a2;
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
                default:
                    listStuff(request, response);
                    break;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL EXCEPTION");
        }

        //SessionFactory k=DataSourceFactory.getSessionFactory();
       // var k2=k.openSession().get(Stuff.class,2);
       // action=a2;

    }

    private void listStuff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Stuff>listStuff;
        Session session =DataSourceFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Stuff> criteria = builder.createQuery(Stuff.class);
        criteria.from(Stuff.class);
        listStuff = session.createQuery(criteria).getResultList();

        transaction.commit();

        session.close();
        RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/StuffList.jsp");
        request.setAttribute("listStuff",listStuff);
        dispatcher.forward(request,response);

    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        RoleUser role= RoleUser.values()[Integer.parseInt(request.getParameter("role"))];
        Session session=DataSourceFactory.getSessionFactory().openSession();

        Stuff stuff=new Stuff(id,name,role);
        Transaction transaction = session.beginTransaction();
        session.update(stuff);
        transaction.commit();
       // stuffDao.update(stuff);
        session.close();
        response.sendRedirect("Stuff");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
        Integer id=Integer.parseInt(request.getParameter("id"));
        Session session =DataSourceFactory.getSessionFactory().openSession();
        Stuff s= session.get(Stuff.class,id);

        RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/StuffForm.jsp");
        if (s!=null)
            request.setAttribute("stuff",s);
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
       // score.setNameUser(stuff.getName());
      //  score.setNameSubject("DB");
        score.setScore(666);
        stuffDao.addUserAndScore(stuff,score);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
