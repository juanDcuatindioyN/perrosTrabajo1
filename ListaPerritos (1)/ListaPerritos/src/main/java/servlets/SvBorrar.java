package Servlets;

import com.mycompany.listaperritos.metodos;
import com.mycompany.listaperritos.Perros;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.SvPerros;

/**
 *
 * @author hp
 */
@WebServlet(name = "SvEliminar", urlPatterns = {"/SvEliminar"})
public class SvBorrar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        processRequest(request, response);        
        ServletContext context = getServletContext();
        String nombreEliminar = request.getParameter("nombre");    
        ArrayList<Perros> vPerros = new ArrayList<>();
        try {           
            metodos.cargarPerrosDesdeArchivo(vPerros, context);
        } catch (Exception ex) {
           
            Logger.getLogger(SvPerros.class.getName()).log(Level.SEVERE, null, ex);
        }

      
        metodos.BorrarPerro(vPerros, nombreEliminar, context);

       
        metodos.guardarDatosDePerrosEnArchivo(vPerros, context);
    }
}