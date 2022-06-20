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
        User user = (User) session.getAttribute("user");
        String logout=request.getParameter("logout");
        if (logout== null) {
            if (user != null) {
                response.sendRedirect("home");
                return;
            }
            else{
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } 
        else if (logout.equals("")) {
            session.invalidate();
            request.setAttribute("logoutMessage",true);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        if(username == null || username.equals("") || password == null || password.equals("")){
            request.setAttribute("invalidMessage",true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }
         AccountService accountCheckSerivce = new AccountService();
        User user = (User) accountCheckSerivce.login(username,password);
        if(user == null){
            request.setAttribute("invalidMessage",true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            return;
        }
        HttpSession session=request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("username",username);
        response.sendRedirect("home");
        
    }
}
