package model.ws;

import java.math.BigDecimal;
import model.dao.ClienteDao;
import model.dao.ContaBancariaDao;
import model.dao.OperacaoDao;
import model.entities.Cliente;
import model.entities.ContaBancaria;
import model.entities.Operacao;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.entities.TipoOperacao;
import static model.entities.TipoOperacao.SAQUE;

/**
 *
 * @author sara.so
 */
@Path("/operacoes")
public class OperacaoRest {

    @Inject
    private ClienteDao clienteDao;

    @Inject
    private ContaBancariaDao contaBancariaDao;

    @Inject
    private OperacaoDao operacaoDao;

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Operacao> listarTodos() {
        return this.operacaoDao.listarTodos();
    }
    
    @GET
    @Path("/listarOpClienteRemetente/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Operacao> listarOpClienteRemetente(@PathParam("nome") String nome){
        return this.operacaoDao.listarOpClienteRemetente(nome);
    }
    
    @GET
    @Path("/listarOpContaBancaria/{contaBancaria}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Operacao> listarOpContaBancaria(@PathParam("contaBancaria") int contaBancaria){
        return this.operacaoDao.listarOpContaBancaria(contaBancaria);
    }
    
    
    @POST
    @Path("/incluir")
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(Operacao operacao) {

        Cliente clienteRemetente = this.clienteDao.listarPorId(
                operacao.getClienteRemetente().getId());
        
        ContaBancaria contaRemetente = this.contaBancariaDao.listarPorId(
                operacao.getContaBancariaRemetente().getId());
        
        BigDecimal saldoRemetente = contaRemetente.getSaldo();
        BigDecimal valor = operacao.getValor();
        
        if( operacao.getTipoOperacao() == TipoOperacao.SAQUE){
             operacao.setClienteRemetente(clienteRemetente);
             contaRemetente.setSaldo(saldoRemetente.subtract(valor));
             this.operacaoDao.save(operacao);   
        } else {
        
            Cliente clienteDestinatario = this.clienteDao.listarPorId(
                    operacao.getClienteDestinatario().getId());

            ContaBancaria contaDestinatario = this.contaBancariaDao.listarPorId(
                    operacao.getContaBancariaDestinatario().getId());

            BigDecimal saldoDestinatario = contaDestinatario.getSaldo();

            operacao.setClienteRemetente(clienteRemetente);
            operacao.setClienteDestinatario(clienteDestinatario);
            contaRemetente.setSaldo(saldoRemetente.subtract(valor));
            contaDestinatario.setSaldo(saldoDestinatario.add(valor));
            this.operacaoDao.save(operacao);
        
           
        }
        

    }

    @PUT
    @Path("/alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void altera(Operacao operacao) {
        this.operacaoDao.save(operacao);

    }

    @DELETE
    @Path("/remover/{id}")
    public void remove(@PathParam("id") Integer id) {
        this.operacaoDao.remover(id);
    }
}
