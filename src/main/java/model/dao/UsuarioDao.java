package model.dao;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import model.entities.Usuario;

/**
 *
 * @author sara.so
 */
@ApplicationScoped
public class UsuarioDao {
    
    @Inject
    private EntityManager entityManager;
    
    @Transactional
    public Usuario salvar(Usuario usuario){
        return this.entityManager.merge(usuario);
    }
    
    @Transactional
    public void remover(Integer id){
        this.entityManager.remove(this.entityManager.getReference(Usuario.class, id));
    }
    
    public List<Usuario> listarTodos(){
        return this.entityManager.createNamedQuery(
            "Usuario.todos", Usuario.class).getResultList();
    }
}
