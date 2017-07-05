package model.ws;

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
import model.dao.ClienteDao;
import model.entities.Cliente;

/**
 *
 * @author sara.so
 */
@Path("/clientes")
public class ClienteRest {

    @Inject
    private ClienteDao clienteDao;

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarTodos() {
        return this.clienteDao.listarTodos();
    }

    @GET
    @Path("/listar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente listarPorId(@PathParam("id") Integer id) {
        return this.clienteDao.listarPorId(id);
    }

    @GET
    @Path("/listarCliente/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarCliente(@PathParam("nome") String nome){
        return this.clienteDao.listarCliente(nome);
    }
    
    @POST
    @Path("/incluir")
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente salvar(Cliente cliente) {
        return this.clienteDao.salvar(cliente);
    }

    @PUT
    @Path("/alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterar(Cliente cliente) {
        this.clienteDao.salvar(cliente);
    }

    @DELETE
    @Path("/remover/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void remover(@PathParam("id") Integer id) {
        this.clienteDao.remover(id);
    }
}
