package persistence;
import entities.*;
import javax.inject.Inject;
public class TestandoCadastroGenericDAO {
    @Inject usuarioDAO usudao;
    public static void main(String[] args) {
    String teste = "nana";

    usuarioDAO usu = new usuarioDAO();
    usu.excluirporlogin(teste);
       
    }
}

