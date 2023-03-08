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
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author jesus
 */
public class ConductorDAO {
    
    /**
     * Obtiene todos los registros de tipo Conductor de la base de datos..
     * @return Lista de tipo Conductor.
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
    
    /**
     * Guarda un nuevo registro de tipo Condcutor en laa base de datos.
     * @param nombre Nombre del conductor.
     * @param numeroLicencia
     * @param fechaAlta, 
     * @return Retorna true si el registro fue guardado correctamente; de lo contrario, retorna false;
     */
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
    
   
    public Conductor obtenerPorId(int id){
        Conductor conductor = null;
        try {
             Session session = HibernateUtil.getSessionFactory().openSession();
             conductor = session.get(Conductor.class, id);
        } catch(HibernateException ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return conductor;
    }
    
    public boolean editar(int id, String nombre, String numeroLicencia, Date fechaAlta){
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Conductor conductor = obtenerPorId(id);
            if(conductor != null){
                conductor.setNombre(nombre);
                conductor.setNumeroLicencia(numeroLicencia);
                conductor.setFechaAlta(fechaAlta);
                
                session.saveOrUpdate(conductor);
                session.getTransaction().commit();
                resultado = true;
            }
            
        } catch(HibernateException ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public boolean eliminar(int id){
        boolean resultado = false;
        try {
             Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Conductor conductor = obtenerPorId(id);
            if(conductor != null){
                session.delete(conductor);
                session.getTransaction().commit();
                resultado = true;
            }
         } catch(HibernateException ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }
}
