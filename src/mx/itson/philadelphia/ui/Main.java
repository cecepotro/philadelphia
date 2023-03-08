/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.philadelphia.ui;

import java.util.Date;
import mx.itson.philadelphia.persistencia.ConductorDAO;
import mx.itson.philadelphia.persistencia.MultaDAO;

/**
 *
 * @author jesus
 */
public class Main {
    
    public static void main(String[] args) {
        //ConductorDAO c = new ConductorDAO();
        //c.obtenerTodos();
        //c.guardar("Javier Quintero", "4535565", new Date());
        
        MultaDAO.obtenerTodos();
    }
}
