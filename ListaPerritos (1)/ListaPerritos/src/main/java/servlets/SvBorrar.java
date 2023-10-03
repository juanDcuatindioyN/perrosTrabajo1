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


import java.util.List;

@WebServlet("/SvBorrar")
public class SvBorrar extends HttpServlet {
    // Simulación de una lista de perros
    private     ArrayList<Perros> vPerros = new ArrayList<>();

    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el parámetro 'nombre' de la solicitud
        String nombre = request.getParameter("nombre");
        ServletContext context= getServletContext();

        // Buscar y eliminar el perro con el nombre especificado
        boolean eliminado = metodos.BorrarPerro(vPerros, nombre, context);

        if (eliminado) {
            // Enviar una respuesta de éxito
            response.getWriter().write("Perro eliminado con éxito");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            // Enviar una respuesta de error si no se pudo encontrar el perro
            response.getWriter().write("No se encontró un perro con ese nombre");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private boolean eliminarPerroPorNombre(String nombre) {
        for (Perros perros : vPerros) {
            if (perros.getNombre().equals(nombre)) {
                // Eliminar el perro si se encuentra en la lista
                vPerros.remove(perros);
                return true;
            }
        }
        return false; // Retornar falso si el perro no se encontró
    }
}

