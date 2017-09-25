/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.io.IOException;
import javax.ejb.EJB;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ConstantValues;
import utils.SessionAttributes;
import utils.Validator;
import utils.UrlMappings;

/**
 *
 * @author david
 */
@WebServlet(name = "LoggInnServlet", urlPatterns = {"/" + UrlMappings.LOGGIN_URL}, initParams = {
    @WebInitParam(name = "password", value = "pass123")})
public class LoggInnServlet extends HttpServlet {
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
                
                String errorMessage = "";
                
                if(request.getParameter(ConstantValues.ERR_INVALID_PASSWORD) != null){
                    
                    errorMessage = "Invalid password. Try again.";
                    
                } else if (request.getParameter(ConstantValues.ERR_REQUIRE_LOGGIN) != null) {
                    
                    errorMessage = "Logg in to visit this url.";
                }
                                    
		request.setAttribute("errorMessage", errorMessage);
                
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
                

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String inputPassword = request.getParameter("password");
        String correctPassword = this.getInitParameter("password");
        
        System.out.println("inputpw: " + inputPassword);
        boolean passwordIsValid = Validator.isValidPassword(inputPassword, correctPassword);
        
        if(passwordIsValid){
            //Initialiserer en session.
            HttpSession session = request.getSession(false);
            if(session != null){
                session.invalidate();
            }
            session = request.getSession(true);
            session.setMaxInactiveInterval(120);
            session.setAttribute(SessionAttributes.LOGGED_IN_STATUS, "true");
            response.sendRedirect(UrlMappings.SHOPPING_LIST_URL);
        } else {
            
            response.sendRedirect(UrlMappings.LOGGIN_URL + "?" + ConstantValues.ERR_INVALID_PASSWORD);
        }
        
    }
    
    
    

}
