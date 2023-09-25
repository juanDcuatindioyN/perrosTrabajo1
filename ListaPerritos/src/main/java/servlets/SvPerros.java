/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;
import com.mycompany.listaperritos.Perros;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
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
    ArrayList<Perros> inPerros = new ArrayList<>();
    
    @Override
    public void init() throws ServletException{
        super.init();
        
        ServletContext servletContext = getServletContext();
        
        cargarPerrosDesdeArchivo(servletContext);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String nombre = request.getParameter("nombre");
        String raza = request.getParameter("raza");
        String foto = request.getParameter("foto");
        String puntos = request.getParameter("puntos");
        String edad = request.getParameter("edad");
    
        Perros inPerros = new Perros(nombre,raza,foto,puntos,edad);
       inPerros.add(inPerros);
      
       guardarPerrosEnArchivo();
       
       request.setAttribute("inPerros", inPerros);            
    }
    
 
   
    private void cargarPerrosDesdeArchivo(ServletContext servletContext) {
        try {
            String dataPath = servletContext.getRealPath("/data/perros.ser");
            
            File archivo= new File (dataPath);
            if(archivo.exists()){
                FileInputStream fis = new FileInputStream(dataPath);
                ObjectInputStream ois = new ObjectInputStream(fis); 
                
                inPerros = (ArrayList<Perros>)ois.readObject();
                ois.close();
                System.out.println("Datos de los perros cargados exitosamente desde: "+ dataPath);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos de los perros: " + e.getMessage());
        }
    }

    private void guardarPerrosEnArchivo() throws FileNotFoundException {
        try{
            String dataPath = getServletContext().getRealPath("/data");
            File dataFolder = new File(dataPath);
            if(!dataFolder.exists()){
                dataFolder.mkdir();
            }
        String filePath = dataPath + File.separator + "perros.ser";
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(inPerros);
        oos.close();
            System.out.println("Datos de los perros guardados exitosamente en: " +filePath);
        } catch (IOException e) {
           e.printStackTrace();
            System.out.println("Error al guardar los datos de los perror " + e.getMessage());
        }
    }
    public String getServletInfo(){
        return "short description";
    }
   @WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
   @MultipartConfig(location="D:/uploads")public class UploadServlet extends HttpServlet {    
    private static final long serialVersionUID = 1L;        protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {        
    response.setContentType("text/html;charset=UTF-8");        
    Collection<Part> parts = request.getParts();        for(Part part : parts) 
           {part.write(getFileName(part));        }    }    
       @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
       {processRequest(request, response);    }    
       @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {processRequest(request, response);    }    public String getFileName(Part part) 
       {String contentHeader = part.getHeader("content-disposition");        
       String[] subHeaders = contentHeader.split(";");        
       for(String current : subHeaders) {            
           if(current.trim().startsWith("filename")) {            	
               int pos = current.indexOf('=');
               String fileName = current.substring(pos+1);
               return fileName.replace("\"", "");}}return null;}} 
   
}

