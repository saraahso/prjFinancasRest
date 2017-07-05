package model.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import application.converters.LocalDateDeserializer;
import application.converters.LocalDateSerializer;

/**
 *
 * @author sara.so
 */
@Entity
@Table(name = "clientes")
@NamedQueries({
    @NamedQuery(name = "Cliente.todos",
            query = "SELECT c FROM Cliente c")
    ,
    @NamedQuery(name = "Cliente.porId",
            query = "SELECT c FROM Cliente c WHERE c.id = :id")
    ,
    @NamedQuery(name = "Cliente.nome",
            query = "SELECT c FROM Cliente c WHERE c.nome like :nome")    
})
public class Cliente implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Integer id;
    @Getter
    @Setter
    @Column(name = "nome", length = 90, nullable = false)
    private String nome;
    @Getter
    @Setter
    @Column(name = "cpf", length = 15, unique = true, nullable = false)
    private String cpf;
    @Getter
    @Setter
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @Getter
    @Setter
    @Column(name = "renda", nullable = false)
    private BigDecimal renda;

    public Cliente() {
    }
}
