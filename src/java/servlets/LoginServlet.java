package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String logout = request.getParameter("logout");
        if(logout != null) {
            request.setAttribute("message", "You have successfully logged out.");
            //invalidate the session, which will destroy the session object
            //any attributes in the session object will also be destroyed
            session.invalidate();
            
        } else if(session.getAttribute("username") != null ){
            response.sendRedirect("home");
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        // if user and password are empty
        if (user.equals("") || password.equals("")) {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
        
        //get a new session
        HttpSession session = request.getSession();
        
        AccountService service = new AccountService();
        User login = service.login(user, password);
        
        if (login != null) {
            session.setAttribute("username", user); // username = user;
            response.sendRedirect("home");
            return;
        } else {
            request.setAttribute("message", "Please type the right login information!");
            request.setAttribute("userid", user);
            request.setAttribute("userpassword", password);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        

    }

}
