package model.dao;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import model.entities.Cliente;

/**
 *
 * @author sara.so
 */
@ApplicationScoped
public class ClienteDao {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        return this.entityManager.merge(cliente);
    }

    @Transactional
    public void remover(Integer id) {
        this.entityManager.remove(this.entityManager.getReference(Cliente.class, id));
    }

    public List<Cliente> listarTodos() {
        return this.entityManager.createNamedQuery(
                "Cliente.todos", Cliente.class).getResultList();
    }

    public Cliente listarPorId(Integer id) {
        return this.entityManager.createNamedQuery("Cliente.porId", Cliente.class)
                .setParameter("id", id)
                .getSingleResult();

    }
    
    public List<Cliente> listarCliente(String nome){
        return this.entityManager.createNamedQuery(
                "Cliente.nome", Cliente.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
    }
}
