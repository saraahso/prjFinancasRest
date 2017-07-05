package model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sara.so
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuario.todos",
            query = "SELECT c FROM Usuario c")
        ,
    @NamedQuery(name = "Usuario.porId",
            query = "SELECT c FROM Usuario c WHERE c.id =:id")
})
public class Usuario implements Serializable {
    
    @Id
    @Getter
    @Setter
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Integer id;
    @Getter
    @Setter
    @Column(name = "login", unique = true, updatable = false, nullable = false)
    private String login;
    @Getter
    @Setter
    @Column(name = "senha", nullable = false)
    private String senha;
}
