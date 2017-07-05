/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "operacoes")
@NamedQueries({
    @NamedQuery(name = "Operacao.todos",
            query = "SELECT o FROM Operacao o")
    ,
    @NamedQuery(name = "Operacao.porId",
            query = "SELECT o FROM Operacao o WHERE o.id =:id")
    ,    
    @NamedQuery(name = "Operacao.clienteRemetente",
            query = "SELECT o FROM Operacao o WHERE o.clienteRemetente.nome like :nome")
    ,    
    @NamedQuery(name = "Operacao.clienteDestinatario",
            query = "SELECT o FROM Operacao o WHERE o.clienteDestinatario.nome like :nome")
    ,
    @NamedQuery(name = "Operacao.contaBancaria",
            query = "SELECT o FROM Operacao o WHERE o.contaBancariaRemetente.numeroConta like :numeroConta")
})
public class Operacao implements Serializable{
    
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_remetente")
    private Cliente clienteRemetente;
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_destinatario")
    private Cliente clienteDestinatario;
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "conta_bancaria_remetente")
    private ContaBancaria contaBancariaRemetente;
    @Getter
    @Setter
    @ManyToOne(optional = true)
    @JoinColumn(name = "conta_bancaria_destinatario")
    private ContaBancaria contaBancariaDestinatario;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_operacao", nullable = false, length = 45)
    private TipoOperacao tipoOperacao;
    @Getter
    @Setter
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    public Operacao() {
    }
}


