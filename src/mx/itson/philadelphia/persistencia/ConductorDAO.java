/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.philadelphia.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.philadelphia.entidades.Conductor;
import mx.itson.philadelphia.utilerias.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author jesus
 */
public class ConductorDAO {
    
    /**
     * 
     * @return 
     */
    public static List<Conductor> obtenerTodos(){
        List<Conductor> conductores = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Conductor> criteriaQuery = 
                    session.getCriteriaBuilder().createQuery(Conductor.class);
            criteriaQuery.from(Conductor.class);
            
            conductores = session.createQuery(criteriaQuery).getResultList();
        }catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return conductores;
    }
    
    public boolean guardar(String nombre, String numeroLicencia, Date fechaAlta){
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Conductor c= new Conductor();
            c.setNombre(nombre);
            c.setNumeroLicencia(numeroLicencia);
            c.setFechaAlta(fechaAlta);
            
            session.save(c);
            
            session.getTransaction().commit();
            
            resultado = c.getId() !=0;
        }catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }
    
}
