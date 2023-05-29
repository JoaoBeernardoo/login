package controller;

import business.cadastroBusiness;
import entities.usuario;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entities.MessageResponse;
import javax.ws.rs.QueryParam;
import javax.inject.Inject;



@Path("/message")
public class lojacontroller {
   
    @Inject cadastroBusiness business;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MessageResponse getMessage() {
        String message = "Esta é a mensagem do servidor";
        return new MessageResponse(message);
    }

    @POST
    @Path("/cadastro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MessageResponse cadastrarUsuario(usuario Usuario) {
      
        business.salvar(Usuario);
        
        String message = "Usuário cadastrado com sucesso";
        return new MessageResponse(message);
    }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MessageResponse verificarUsuario(usuario Usuario) {
           
   
    boolean loginSuccessful = business.verificar(Usuario);

    if (loginSuccessful) {
    String message = "fooi";
        
    return new MessageResponse(message);
        } 

    else {    
    String message = "errooo";    
    return new MessageResponse(message);
        }
    }


    @GET
    @Path("/poslogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MessageResponse obterNomeUsuario(@QueryParam("login") String login) {
       

       String nomeUsuario = business.exibirNome(login);
        return new MessageResponse(nomeUsuario);
       
    }

    
    @GET
    @Path("/excluir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MessageResponse excluirUsuario(@QueryParam("login") String login) {
           
    
    boolean excluirusuario = business.excluirporlogin(login);

    if (excluirusuario) {
    String message = "sucess";
        
    return new MessageResponse(message);
        } 

    else {    
    String message = "fail";    
    return new MessageResponse(message);
        }
    }
    
}
