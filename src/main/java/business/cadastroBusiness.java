package business;
import persistence.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.inject.Inject;
import entities.usuario;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Dependent
public class cadastroBusiness {
    
    private static final Logger logger = LogManager.getLogger(usuario.class);
   
    @Inject
    private usuarioDAO usuDAO;
     

    public void salvar(usuario Usuario){
       
        usuDAO.save(Usuario);
    }


    public boolean verificar(usuario Usuario) {
    

        String login = Usuario.getLogin();

       String senha = Usuario.getSenha();     
       String loginExists = usuDAO.verificalogin(login);
       String senhaExists= usuDAO.verificasenha(login);

       logger.warn(loginExists, senhaExists);


 
       return loginExists != null && senhaExists != null && senhaExists.equals(senha) && loginExists.equals(login);

 }
 
 
 public String exibirNome(String loginfornecido){

   String nomeUsuario = usuDAO.exibirnome(loginfornecido);

    return nomeUsuario;
 }


 public boolean  excluirporlogin(String loginfornecido){

    boolean exclusaoRealizada = usuDAO.excluirporlogin(loginfornecido);
    
    logger.warn(exclusaoRealizada);
    if (exclusaoRealizada) {
        // A exclusão foi realizada com sucesso
        return true;
    } else {
        // A exclusão não foi realizada
        return false;
    }
 }

}
