package br.edu.ifsul.controle;




import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "controleServico")
@SessionScoped
public class ControleServico implements Serializable {
    @EJB
    private ServicoDAO<Servico> dao;
    private Servico objeto;
    private Boolean editando;
   
    
    public ControleServico(){        
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/servico/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Servico();
        
    }
    
    public void alterar(Integer id){
      try {
            objeto = dao.getObjectById(id); 
            editando = true;
            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }                
        
    }
    
    
    public void excluir(Integer id){
       try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro a remover objeto: "+Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
           try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + Util.getMensagemErro(e));
        }           
    }
    
    public Servico getObjeto() {
        return objeto;
    }

    public void setObjeto(Servico objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public ServicoDAO<Servico> getDao() {
        return dao;
    }

    public void setDao(ServicoDAO<Servico> dao) {
        this.dao = dao;
    }
}
