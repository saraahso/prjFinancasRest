package model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "contas_bancarias")
@NamedQueries({
    @NamedQuery(name = "ContaBancaria.todos",
            query = "SELECT c FROM ContaBancaria c")
    ,
    @NamedQuery(name = "ContaBancaria.porId",
            query = "SELECT c FROM ContaBancaria c WHERE c.id = :id")
    ,
    @NamedQuery(name = "ContaBancaria.cliente",
            query = "SELECT c FROM ContaBancaria c WHERE c.cliente.nome like :nome")
    ,
    @NamedQuery(name = "ContaBancaria.clientePorId",
            query = "SELECT c FROM ContaBancaria c WHERE c.cliente.id = :id")
})
public class ContaBancaria implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Getter
    @Setter
    @Column(name = "numero_conta", length = 4, unique = true, nullable = false)
    private int numeroConta;
    @Getter
    @Setter
    @Column(name = "digito", length = 1, nullable = false)
    private int digito;
    @Getter
    @Setter
    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;
    @Getter
    @Setter
    @Column(name = "token", length = 6, nullable = false)
    private String token;
    @Setter
    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    public ContaBancaria() {

    }
 }