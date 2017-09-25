/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Item;

/**
 *
 * @author david
 */
@Stateless
public class ItemEAO {
    
    @PersistenceContext(name = "Oblig1_Dat103PU")
    private EntityManager em;
    
    
    
    public void addItem(Item item){
        em.persist(item);
    }

    public List<Item> getItemList() {
        return em.createNamedQuery("Item.findAll").getResultList();
    }
    
    public void deleteItem(Integer id){
        em.remove(em.find(Item.class, id));
    }
    
    
    public void sayHello(){
        //TypedQuery<Item> qu = em.createQuery("SELECT i FROM Item i", Item.class);
        System.out.println("helloo from ItemEAO");
        System.out.println("entityManager" + em);
        //System.out.println("TypedQuery: " + qu);
        List<Item> itemsLi = (List<Item>)em.createNamedQuery("Item.findAll").getResultList();
        System.out.println("" + em.createNamedQuery("Item.findAll").getResultList().get(0));
        System.out.println("" + itemsLi);
        int count = itemsLi.size();
        
    }
}
