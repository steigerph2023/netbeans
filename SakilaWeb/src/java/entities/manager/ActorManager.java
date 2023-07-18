/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.manager;

import entities.Actor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author steig
 */
@Stateless
public class ActorManager {

    @PersistenceContext
    private EntityManager entityManager;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public ActorManager() {
    }
    
    public List<Actor> loadAll(){
        return this.entityManager.createQuery("SELECT a FROM Actor a", Actor.class).getResultList();
    }
}
