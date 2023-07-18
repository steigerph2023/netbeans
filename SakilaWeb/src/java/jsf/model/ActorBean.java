/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.model;

import controllers.ActorJpaController;
import entities.Actor;
import entities.manager.ActorManager;
import java.util.List;
import jsf.util.PagingInfo;

/**
 *
 * @author steig
 */
public class ActorBean {

    /**
     * Creates a new instance of ActorBean
     */
    private Actor actor;
    private List<Actor> actorList;
    private ActorJpaController jpaController;
    private ActorManager manager;
    private PagingInfo pagingInfo;

    public ActorBean() {
    }

    public PagingInfo getPagingInfo() {
        pagingInfo = new PagingInfo();
        jpaController = new ActorJpaController();
        int itemCount = pagingInfo.getItemCount();
        if (itemCount == -1) {
            pagingInfo.setItemCount(jpaController.getActorCount());
        }
        return pagingInfo;
    }

    public List<Actor> getActorList() {
        if (actorList == null) {
            getPagingInfo();
//            actorList = jpaController.findActorEntities();
            actorList = jpaController.findActorEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return actorList;
    }
    
    public List<Actor> loadAllList(){
        return this.manager.loadAll();
    }
}
