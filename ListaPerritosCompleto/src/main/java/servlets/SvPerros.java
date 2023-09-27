/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;
import com.mycompany.listaperritos.Perros;
import com.mycompany.listaperritos.metodos;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author cuati
 */
@WebServlet(name = "SvPerros", urlPatterns = {"/SvPerros"})
public class SvPerros extends HttpServlet{
    ArrayList<Perros> perros = new ArrayList<>();
    
    metodos metodoss= new metodos();
    
   /* @Override
    public void init() throws ServletException{
        super.init();
        
        //ServletContext servletContext = getServletContext();
        
        //metodos.cargarPerrosDesdeArchivo(servletContext,perros);
        
    }*/

    @Override
  
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    ServletContext context= getServletContext();
    
     ArrayList<Perros> perros = metodos.cargarPerrosDesdeArchivo(context);
    
    String nombre = request.getParameter("nombre");
    String raza = request.getParameter("raza");
    String foto = request.getParameter("foto");
    int puntos = Integer.parseInt(request.getParameter("puntos"));
    int edad = Integer.parseInt(request.getParameter("edad"));

        System.out.println(nombre);
        System.out.println(raza);
        System.out.println(foto);
        System.out.println(puntos);
        System.out.println(edad);
    
           
        Perros inPerro= new Perros(nombre,raza,foto,puntos,edad);
        perros.add(inPerro);
        metodoss.setNuPerros(perros);

        
        metodos.guardarDatosDePerrosEnArchivo(context,perros);
        
        request.setAttribute("perros", metodoss.getNuPerros());
        
        request.getRequestDispatcher("index.jsp").forward(request,response);
        
}

 
    
    @Override
    public String getServletInfo(){
       return"Short description" ;
    }

  
}

