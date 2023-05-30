package persistence;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import entities.usuario;
import javax.persistence.TypedQuery;
import entities.usuario;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;






@Named
@Dependent
public class usuarioDAO extends GenericDAO<usuario>{
    

    private static String VERIFICAR_LOGIN = " SELECT login FROM entities.usuario WHERE login = :loginfornecido ";

    
   private static String VERIFICA_SENHA = " SELECT senha FROM entities.usuario WHERE login = :loginfornecido ";
  
   private static String EXIBIR_NOME = " SELECT nome FROM entities.usuario WHERE login = :loginfornecido ";

   private static String EXCLUIR_PELO_LOGIN = "DELETE FROM entities.usuario WHERE login = :loginfornecido";
  
   private static String RECUPERAR_EMAIL  = " SELECT email FROM entities.usuario WHERE login = :loginfornecido ";



   public String exibirnome(final String loginfornecido){
    TypedQuery<String> query = getEntityManager().createQuery(EXIBIR_NOME, String.class);
    query.setParameter("loginfornecido", loginfornecido);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
   }


   public String exibiremail(final String loginfornecido){
    TypedQuery<String> query = getEntityManager().createQuery(RECUPERAR_EMAIL, String.class);
    query.setParameter("loginfornecido", loginfornecido);
    try {
        return query.getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
    
   }

    public String verificalogin(final String loginfornecido) {
        TypedQuery<String> query = getEntityManager().createQuery(VERIFICAR_LOGIN, String.class);
        query.setParameter("loginfornecido", loginfornecido);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
       
    }


    public String verificasenha(final String loginfornecido){
        TypedQuery<String> query = getEntityManager().createQuery(VERIFICA_SENHA, String.class);
        query.setParameter("loginfornecido", loginfornecido);
    

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    

    public boolean excluirporlogin(final String loginfornecido) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        
        Query query = entityManager.createQuery(EXCLUIR_PELO_LOGIN);
        query.setParameter("loginfornecido", loginfornecido);
        
        int linhasAfetadas = query.executeUpdate();


        entityManager.getTransaction().commit();
        entityManager.close();
        
        return linhasAfetadas > 0;
    }
    

}