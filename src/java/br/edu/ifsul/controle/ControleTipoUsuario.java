package br.edu.ifsul.controle;




import br.edu.ifsul.dao.Tipo_UsuarioDAO;
import br.edu.ifsul.modelo.Tipo_Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */

@Named(value = "controleTipoUsuario")
@SessionScoped
public class ControleTipoUsuario implements Serializable {
    @EJB
    private Tipo_UsuarioDAO<Tipo_Usuario> dao;
    private Tipo_Usuario objeto;
    private Boolean editando;
    private Boolean novoObjeto;
    
    public ControleTipoUsuario(){        
        editando = false;
        novoObjeto = false;
    }
    
    public String listar(){
        editando = false;
        novoObjeto = false;
        return "/privado/tipo_usuario/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Tipo_Usuario();
        novoObjeto = true;
    }
    
    public void alterar(String nome){
      try {
            objeto = dao.getObjectById(nome); 
            editando = true;
            novoObjeto = false;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }                
        
    }
    
    public void excluir(String nome){
       try {
            objeto = dao.getObjectById(nome);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro a remover objeto: "+Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {                  
            if (novoObjeto){                
                dao.persist(objeto);            
            } else {                
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");  
            editando = false;        
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir: "+Util.getMensagemErro(e));            
        }                
    }
    
    public Tipo_Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Tipo_Usuario objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public Tipo_UsuarioDAO<Tipo_Usuario> getDao() {
        return dao;
    }

    public void setDao(Tipo_UsuarioDAO<Tipo_Usuario> dao) {
        this.dao = dao;
    }

    public Boolean getNovoObjeto() {
        return novoObjeto;
    }

    public void setNovoObjeto(Boolean novoObjeto) {
        this.novoObjeto = novoObjeto;
    }
}
