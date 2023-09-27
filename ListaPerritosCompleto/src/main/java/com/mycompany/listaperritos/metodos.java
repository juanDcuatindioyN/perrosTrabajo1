/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaperritos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author cuati
 */
public class metodos {
     
      ArrayList<Perros> nuPerros = new ArrayList<>(); 

    public void setNuPerros(ArrayList<Perros> nuPerros) {
        this.nuPerros = nuPerros;
    }

    public ArrayList<Perros> getNuPerros() {
        return nuPerros;
    }
      
    
      
      
      public static void guardarDatosDePerrosEnArchivo(ServletContext contexto, ArrayList<Perros> nuPerros) {
         String p="ser/data.ser";
         String path= contexto.getRealPath(p);
         File arc= new File (path);
        try {
           
            FileOutputStream fw = new FileOutputStream(path);
            ObjectOutputStream pw =new ObjectOutputStream(fw);
            pw.writeObject(nuPerros);
            pw.close();
            System.out.println("se cargo exitosamente" + path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ha ocurrido un error" + e.getMessage());
        }
    }

    public static ArrayList<Perros> cargarPerrosDesdeArchivo(ServletContext contexto) {
        
        ArrayList<Perros> nuPerros = new ArrayList<>();
        String p="ser/data.ser";
        String path= contexto.getRealPath(p);
        File arc= new File (path);
        System.out.println("El archivo serializado se encuentra en: "+path);
         try {
               FileInputStream fileIn=new FileInputStream(path);
               ObjectInputStream ois=new ObjectInputStream(fileIn);
             
            
             nuPerros = (ArrayList<Perros>) ois.readObject();
             ois.close();
             System.out.println("Se leyo -----");
             System.out.println("buscando en: " + path);
         }catch (FileNotFoundException ex) {
                
                System.out.println("No se encontró el archivo");
            } catch (IOException ex) {
                
                System.out.println("Error al leer el archivo");
            } catch (ClassNotFoundException ex) {
                
                System.out.println("Clase no encontrada al deserializar");
            }
            
       return nuPerros;
    }
         
    
}
