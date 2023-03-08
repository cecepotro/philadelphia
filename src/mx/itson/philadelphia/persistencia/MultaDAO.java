/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.philadelphia.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.philadelphia.entidades.Conductor;
import mx.itson.philadelphia.entidades.Multa;
import mx.itson.philadelphia.utilerias.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author jesus
 */
public class MultaDAO {
    
    public static List<Multa> obtenerTodos(){
        List<Multa> multas = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Multa> criteriaQuery = 
                    session.getCriteriaBuilder().createQuery(Multa.class);
            criteriaQuery.from(Multa.class);
            
            multas = session.createQuery(criteriaQuery).getResultList();
        }catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return multas;
    }
}
