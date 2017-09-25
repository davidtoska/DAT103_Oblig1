
package Controller;

import dataaccess.ItemEAO;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;

import utils.ConstantValues;
import utils.SessionAttributes;
import utils.UrlMappings;

/**
 *
 * @author david
 */
@WebServlet(name = "ShoppingListServlet", urlPatterns = {"/" + UrlMappings.SHOPPING_LIST_URL})
public class ShoppingListServlet extends HttpServlet {


    @EJB
    private ItemEAO itemEAO;
    
    //@EJB
    //private ShoppingList shoppingList;
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        //If user try to access without a valid session.
        if(session == null){
            response.sendRedirect(UrlMappings.LOGGIN_URL + "?" + ConstantValues.ERR_REQUIRE_LOGGIN);
        }
        else {
            String stat = (String) session.getAttribute(SessionAttributes.LOGGED_IN_STATUS);
            session.setAttribute("itemEAO", itemEAO);
            
            request.getRequestDispatcher("WEB-INF/shoppingList.jsp").forward(request, response);
        }
        

        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Fetching data from request.
        HttpSession session = request.getSession(false);
        String deleteItemParameter= (String) request.getParameter("deleteItem");
        Boolean sessionExciste = (session != null);
        
        //itemEAO.sayHello();
        
        //If user try to access without a valid session.
        if(!sessionExciste){
            response.sendRedirect(UrlMappings.LOGGIN_URL + "?" + ConstantValues.ERR_REQUIRE_LOGGIN);
        }
        else 
        {
            
            String stat = (String) session.getAttribute(SessionAttributes.LOGGED_IN_STATUS);
            
            if(deleteItemParameter != null){
                itemEAO.deleteItem(Integer.parseInt(deleteItemParameter));
                
            } else {
                
                String newItem = request.getParameter("newItem");
                this.createItem(newItem);

            }
            
            session.setAttribute("itemEAO", itemEAO);
            request.getRequestDispatcher("WEB-INF/shoppingList.jsp").forward(request, response);
            
            
        }
        
        
    }
    
    
    private void createItem(String newItemName){
        boolean isEmpty = (newItemName.equals(""));
        
        if(!isEmpty){
            Item newItem = new Item();
            newItem.setIname(newItemName);
            itemEAO.addItem(newItem);
        }
        
    }
    
    
    


}
