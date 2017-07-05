package model.dao;

import model.entities.Operacao;
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
public class OperacaoDao {
    
    @Inject
    private EntityManager entityManager;
    
    @Transactional
    public void save(Operacao operacao){
        this.entityManager.merge(operacao);
    }
    
    @Transactional
    public void remover(Integer id) {
        this.entityManager.remove(this.entityManager.getReference(Operacao.class, id));
    }
    
    public List<Operacao> listarTodos(){
        return this.entityManager.createNamedQuery(
            "Operacao.todos", Operacao.class).getResultList();               
    }
    
    public Operacao listarPorId(Integer id) {
        return this.entityManager.createNamedQuery("Operacao.porId", Operacao.class)
                .setParameter("id", id)
                .getSingleResult();

    }
    
    public List<Operacao> listarOpContaBancaria(int contaBancariaRemetente){
        return this.entityManager.createNamedQuery(
                "Operacao.contaBancaria", Operacao.class)
                .setParameter("contaBancariaRemetente", "%"+contaBancariaRemetente+"%")
                .getResultList();
                
    } 
    
    public List<Operacao> listarOpClienteRemetente(String nome){
        return this.entityManager.createNamedQuery(
                "Operacao.clienteRemetente", Operacao.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
                
    }   
    
    public List<Operacao> listarOpClienteDestinatario(String nome){
        return this.entityManager.createNamedQuery(
                "Operacao.clienteDestinatario", Operacao.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
                
    }   
    
}
