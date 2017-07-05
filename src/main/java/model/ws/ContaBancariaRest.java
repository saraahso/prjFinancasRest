package model.ws;

import java.util.List;
import model.dao.ClienteDao;
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
import model.dao.ContaBancariaDao;
import model.entities.Cliente;
import model.entities.ContaBancaria;

/**
 *
 * @author sara.so
 */
@Path("/contas")
public class ContaBancariaRest {

    @Inject
    private ClienteDao clienteDao;
    @Inject
    private ContaBancariaDao bancariaDao;

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ContaBancaria> listarTodos() {
        return this.bancariaDao.listarTodos();
    }
    
    @GET
    @Path("/listar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ContaBancaria listarPorId(@PathParam("id") Integer id) {
        return this.bancariaDao.listarPorId(id);
    }
    
    @GET
    @Path("/listarContaClientePorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ContaBancaria> listarContaClientePorId(@PathParam("id") Integer id){
        return this.bancariaDao.listarContaClientePorId(id);
    }
    
    @GET
    @Path("/listarContaClienteRemetente/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ContaBancaria> listarContaClienteRemetente(@PathParam("nome") String nome){
        return this.bancariaDao.listarContaCliente(nome);
    }
    
    @GET
    @Path("/listarContaClienteDestinatario/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ContaBancaria> listarContaClienteDestinatario(@PathParam("nome") String nome){
        return this.bancariaDao.listarContaCliente(nome);
    }

    @POST
    @Path("/incluir")
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(ContaBancaria contaBancaria) {

        final Cliente cliente = this.clienteDao.listarPorId(
                contaBancaria.getCliente().getId());

        if (cliente == null) {
            throw new IllegalArgumentException("Nenhum cliente com ID = "
                    + contaBancaria.getCliente().getId());
        } else {
            contaBancaria.setCliente(cliente);
            this.bancariaDao.save(contaBancaria);
        }
    }

    @PUT
    @Path("/alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void altera(ContaBancaria contaBancaria) {
        this.bancariaDao.save(contaBancaria);
    }

    @DELETE
    @Path("/remover/{id}")
    public void remove(@PathParam("id") Integer id) {
        this.bancariaDao.remover(id);
    }
}
