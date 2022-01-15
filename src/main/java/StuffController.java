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

    public static void main(String[] args) throws SQLException {
        var k2=DaoStaff.getInstance();
        var k=k2.findAll();


        System.out.println(k);
    }

    private static final Logger LOGGER = Logger.getLogger(StuffController.class.getName());
    private DaoStaff stuffDao= DaoStaff.getInstance();
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
        RoleUser role=RoleUser.values()[Integer.parseInt(request.getParameter("role"))];

        Stuff stuff=new Stuff(id,name,role);
        stuffDao.update(stuff);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
    Integer id=Integer.parseInt(request.getParameter("id"));
    Optional<Stuff> existingStuff=stuffDao.find(id);
    RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/StuffForm.jsp");
    existingStuff.ifPresent((s)->request.setAttribute("stuff",s));
    dispatcher.forward(request,response);
    }

    private void deleteStuff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    }

    private void insertStuff(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
