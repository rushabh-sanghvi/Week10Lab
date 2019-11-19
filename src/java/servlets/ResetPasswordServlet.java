package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;
import services.UserService;

/**
 *
 * @author 794473
 */
public class ResetPasswordServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uuid =null;
        uuid=request.getParameter("uuid");
        if(uuid==null){
             getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("uuid", uuid);
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
          
         
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AccountService as = new AccountService();
        
        String uuid = request.getParameter("uuid");
        
        String url = request.getRequestURL().toString();
        
        String email = request.getParameter("email");
        String action= request.getParameter("action");
        
        if(action.equals("reset"))
        {
            try {
                as.resetPassword(email, getServletContext().getRealPath("/WEB-INF"), url);
            } catch (Exception ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(action.equals("resp"))
        {          
                String newpassword=request.getParameter("respassword");
                try {
                    as.changePassword(uuid,newpassword);
                } catch (Exception ex) {
                    Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }


}
