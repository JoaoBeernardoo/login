package business;
import persistence.*;
import javax.inject.Inject;
import entities.usuario;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.transaction.Transactional;
import email.enviaremail;

@Named
@Dependent
public class cadastroBusiness {

   @Inject
   private enviaremail Email;

    @Inject
    private usuarioDAO usuDAO;
     

    public void salvar(usuario Usuario){
       
        try {
            usuDAO.save(Usuario);
            // Código que pode lançar a exceção
            // ...
        } catch (Exception e) {
            // Captura a exceção e imprime o stack trace completo
            e.printStackTrace();
        }

    }


    public boolean verificar(usuario Usuario) {
    

        String login = Usuario.getLogin();

       String senha = Usuario.getSenha();  
          
       String loginExists = usuDAO.verificalogin(login);
       String senhaExists= usuDAO.verificasenha(login);

       


 
       return loginExists != null && senhaExists != null && senhaExists.equals(senha) && loginExists.equals(login);

 }
 
 
 public String exibirNome(String loginfornecido){

   String nomeUsuario = usuDAO.exibirnome(loginfornecido);

    return nomeUsuario;
 }


 public boolean  excluirporlogin(String loginfornecido){

    String email = usuDAO.exibiremail(loginfornecido);
    Email.enviar(email);

    boolean exclusaoRealizada = usuDAO.excluirporlogin(loginfornecido);

    if (exclusaoRealizada) {
        
        // A exclusão foi realizada com sucesso
        return true;
    } else {
        // A exclusão não foi realizada
        return false;
    }
 }

}
