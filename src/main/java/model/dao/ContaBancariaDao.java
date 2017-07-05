package model.dao;

import model.entities.ContaBancaria;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 *
 * @author sara.so
 */
@ApplicationScoped
public class ContaBancariaDao {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public void save(ContaBancaria contaBancaria) {
        this.entityManager.merge(contaBancaria);
    }

    @Transactional
    public void remover(Integer id) {
        this.entityManager.remove(this.entityManager.getReference(ContaBancaria.class, id));
    }
    
    public List<ContaBancaria> listarTodos() {
        return this.entityManager.createNamedQuery(
                "ContaBancaria.todos", ContaBancaria.class).getResultList();
    }
    
    public ContaBancaria listarPorId(Integer id) {
        return this.entityManager.createNamedQuery("ContaBancaria.porId", ContaBancaria.class)
                .setParameter("id", id)
                .getSingleResult();

    }
    
    public List<ContaBancaria> listarContaClientePorId(int id){
        return this.entityManager.createNamedQuery("ContaBancaria.clientePorId", ContaBancaria.class)
                .setParameter("id", id)
                .getResultList();
    }
    
    public List<ContaBancaria> listarContaCliente(String nome){
        return this.entityManager.createNamedQuery(
                "ContaBancaria.cliente", ContaBancaria.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
                
    }
}
