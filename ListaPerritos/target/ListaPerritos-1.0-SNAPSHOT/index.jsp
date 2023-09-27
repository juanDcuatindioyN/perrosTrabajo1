<%@page import="com.mycompany.listaperritos.metodos"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.ObjectInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.mycompany.listaperritos.Perros"%>
<%@page import="java.util.ArrayList"%>
<%@include file="templates/header.jsp" %>

<!-- creamos un container para poner  -->
<div class ="container p-1">     
    
    <div class="row">
        <div class="col-md-4">
            <h2>Ingrese un nuevo perro</h2>
    <form action="SvPerros" method="POST" >
        <div class="mb-2">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" name="nombre" aria-describedby="basic-addon1">
        <div id="nombrePerros" class="form-text"></div>
     </div>
     <div class="mb-2">
        <label for="raza" class="form-label">Raza</label>
        <input type="text" class="form-control" name="raza" aria-describedby="basic-addon1">
        <div id="raza" class="form-text"></div>
     </div>
        <div class="mb-2">
        <label for="foto" class="form-label">foto</label>
        <input type="text" class="form-control" name="foto" aria-describedby="basic-addon1">
        <div id="foto" class="form-text"></div>
     </div>
       
            <div class="mb-2">
      <label for="puntos" class="form-label">Puntos</label>
      <select id="disabledSelect" class="form-select" name="puntos">   
            <option selected>seleccionar</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
      </select>
            </div>
        <div class="mb-2">
        <label for="edad" class="form-label">Edad</label>
        <input type="number" class="form-control" name="edad" aria-describedby="basic-addon1">
        <div id="edad" class="form-text"></div>
     </div>         
        <button type="submit" class="btn btn-primary">insertar perro</button>
    </form>
        </div>
        <div class="col-md-8 " >
            <table class="table table-striped table-dark ">
                <thead> 
                    <tr>
                        <th>Nombre</th>
                        <th>Raza</th>
                        <th>Foto</th>
                        <th>Puntos</th>
                        <th>Edad</th>
                        <th>Acciones</th>
                    </tr>   
                </thead>
                <tbody>
                    <% 
                    ArrayList<Perros> nuPerros = new ArrayList<>();
                    ServletContext servletContext = getServletContext();
                    nuPerros = metodos.cargarPerrosDesdeArchivo(servletContext);
                    if (nuPerros!= null && !nuPerros.isEmpty()){
                        for(Perros cPerros: nuPerros) {                        
                       
                        %>
                        <tr>
                            <td><%= cPerros .getNombre() %></td>
                            <td><%= cPerros .getRaza() %></td>
                            <td><%= cPerros .getFoto()%></td>
                            <td><%= cPerros .getPuntos() %></td>
                            <td><%= cPerros .getEdad() %></td>                                         
                       <td>                         
                        <!-- basurero -->
                        <button><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                        <path d="M16 1.75V3h5.25a.75.75 0 0 1 0 1.5H2.75a.75.75 0 0 1 0-1.5H8V1.75C8 .784 8.784 0 9.75 0h4.5C15.216 0 16 .784 16 1.75Zm-6.5 0V3h5V1.75a.25.25 0 0 0-.25-.25h-4.5a.25.25 0 0 0-.25.25ZM4.997 6.178a.75.75 0 1 0-1.493.144L4.916 20.92a1.75 1.75 0 0 0 1.742 1.58h10.684a1.75 1.75 0 0 0 1.742-1.581l1.413-14.597a.75.75 0 0 0-1.494-.144l-1.412 14.596a.25.25 0 0 1-.249.226H6.658a.25.25 0 0 1-.249-.226L4.997 6.178Z"></path><path d="M9.206 7.501a.75.75 0 0 1 .793.705l.5 8.5A.75.75 0 1 1 9 16.794l-.5-8.5a.75.75 0 0 1 .705-.793Zm6.293.793A.75.75 0 1 0 14 8.206l-.5 8.5a.75.75 0 0 0 1.498.088l.5-8.5Z"></path></svg></button>
                        <!-- lapiz -->
                        <button><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                        <path d="M17.263 2.177a1.75 1.75 0 0 1 2.474 0l2.586 2.586a1.75 1.75 0 0 1 0 2.474L19.53 10.03l-.012.013L8.69 20.378a1.753 1.753 0 0 1-.699.409l-5.523 1.68a.748.748 0 0 1-.747-.188.748.748 0 0 1-.188-.747l1.673-5.5a1.75 1.75 0 0 1 .466-.756L14.476 4.963ZM4.708 16.361a.26.26 0 0 0-.067.108l-1.264 4.154 4.177-1.271a.253.253 0 0 0 .1-.059l10.273-9.806-2.94-2.939-10.279 9.813ZM19 8.44l2.263-2.262a.25.25 0 0 0 0-.354l-2.586-2.586a.25.25 0 0 0-.354 0L16.061 5.5Z">
                        </path></svg></button>                       
                        <!-- ojo -->
                        <button><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                        <path d="M15.5 12a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z">                           
                        </path><path d="M12 3.5c3.432 0 6.124 1.534 8.054 3.241 1.926 1.703 3.132 3.61 3.616 4.46a1.6 1.6 0 0 1 0 1.598c-.484.85-1.69 2.757-3.616 4.461-1.929 1.706-4.622 3.24-8.054 3.24-3.432 0-6.124-1.534-8.054-3.24C2.02 15.558.814 13.65.33 12.8a1.6 1.6 0 0 1 0-1.598c.484-.85 1.69-2.757 3.616-4.462C5.875 5.034 8.568 3.5 12 3.5ZM1.633 11.945a.115.115 0 0 0-.017.055c.001.02.006.039.017.056.441.774 1.551 2.527 3.307 4.08C6.691 17.685 9.045 19 12 19c2.955 0 5.31-1.315 7.06-2.864 1.756-1.553 2.866-3.306 3.307-4.08a.111.111 0 0 0 .017-.056.111.111 0 0 0-.017-.056c-.441-.773-1.551-2.527-3.307-4.08C17.309 6.315 14.955 5 12 5 9.045 5 6.69 6.314 4.94 7.865c-1.756 1.552-2.866 3.306-3.307 4.08Z"></path></svg> </button>   
                        
                        
                        </td>

                        </tr>
                        <%
                            }
                }else{ %>
                        <td><% out.println("No hay perros"); %> </td>
<%}
  %>                 
                </tbody>  
            </table>

        </div>
    </div>
  </div>  
<%@include file="templates/footer.jsp" %>

