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
        
        Part fotoPart =request.getPart("foto");
        String fileName= fotoPart.getSubmittedFileName();
        String uploadDirectory = getServletContext().getRealPath("fotos");
        String filePath = uploadDirectory + File.separator +fileName;
        try (InputStream input =fotoPart.getInputStream();
                 OutputStream output = new FileOutputStream(filePath)) {
            
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            System.out.println("Imagen guardada en: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar el error en caso de que ocurra una excepción al guardar la imagen
        }
        
     
        
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String nombre = request.getParameter("nombre");
        Perros perro = metodos.cargarPerrosDesdeArchivo(nuPerros); // Implementa la lógica para buscar el perro en tu lista de perros
        if (perro != null) {
            // Genera la respuesta HTML con los detalles del perro
            String perroHtml = "<h2>Nombre: " + perro.getNombre() + "</h2>" +
                               "<p>Raza: " + perro.getRaza() + "</p>" +
                               "<p>Puntos: " + perro.getPuntos() + "</p>" +
                               "<p>Edad (meses): " + perro.getEdad() + "</p>" +
                               "<img src='imagenes/" + perro.getFoto() + "' alt='" + perro.getNombre() + "' width='100%'/>";
            response.setContentType("text/html");
            response.getWriter().write(perroHtml);
        } else {
            // Maneja el caso en el que no se encuentra el perro
            response.setContentType("text/plain");
            response.getWriter().write("Perro no encontrado");
        }
    }
    


 
    
    @Override
    public String getServletInfo(){
       return"Short description" ;
    }

 
}

