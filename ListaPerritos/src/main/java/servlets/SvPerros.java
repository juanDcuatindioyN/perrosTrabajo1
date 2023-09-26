/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;
import com.mycompany.listaperritos.Perros;
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
    
    @Override
    public void init() throws ServletException{
        super.init();
        
        ServletContext servletContext = getServletContext();
        
        cargarPerrosDesdeArchivo(servletContext);
        
    }

    @Override
  
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String nombre = request.getParameter("nombre");
    String raza = request.getParameter("raza");
    String foto = request.getParameter("foto");
    String puntos = request.getParameter("puntos");
    String edad = request.getParameter("edad");
    
   
        System.out.println(nombre);
        System.out.println(raza);
        System.out.println(foto);
        System.out.println(puntos);
        System.out.println(edad);
    
        try {
            int edadInt = Integer.parseInt(edad);
            int puntosInt = Integer.parseInt(puntos);
            Perros inPerro= new Perros(nombre,raza,foto,puntosInt,edadInt);
            perros.add(inPerro);
           
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ups!! error..." + e.getMessage());
        }
        guardarDatosDePerrosEnArchivo();
        
        request.setAttribute("generoPerros", perros);
        
        request.getRequestDispatcher("index.jsp").forward(request,response);
        
}

    private void guardarDatosDePerrosEnArchivo() {
        try {
            String dataPath=getServletContext().getRealPath("/data");
            File dataFolder = new File(dataPath);
            if(!dataFolder.exists()){
                dataFolder.mkdirs();
            }
            String filePath = dataPath + File.separator + "perros.ser";
            FileOutputStream fw = new FileOutputStream(filePath);
            ObjectOutputStream pw =new ObjectOutputStream(fw);
            pw.writeObject(perros);
            pw.close();
            System.out.println("se cargo exitosamente" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ha ocurrido un error" + e.getMessage());
        }
    }
    
    @Override
    public String getServletInfo(){
       return"Short description" ;
    }
    private void cargarPerrosDesdeArchivo(ServletContext servletContext) {
        try {
            String dataPath = servletContext.getRealPath("/data/perros.ser");
            File archivo = new File (dataPath);
            
        if(archivo.exists()){
            FileInputStream sw =new FileInputStream(dataPath);
            try(ObjectInputStream jw= new ObjectInputStream(sw)) {
                perros = (ArrayList<Perros>) jw.readObject();
                jw.close();
            } 
               System.out.println("buscando en: " + dataPath); 
            }else{
            System.out.println("no existe"+ dataPath);
            }
        }catch(EOFException e){
            System.out.println("completado");
            e.printStackTrace();
        }catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
         
            System.out.println("error al cargar datos"+ e.getMessage());
        }
    }
  
}

