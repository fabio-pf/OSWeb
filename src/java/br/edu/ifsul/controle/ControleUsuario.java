
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.Tipo_UsuarioDAO;
import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Tipo_Usuario;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "controleUsuario")
@SessionScoped
public class ControleUsuario implements Serializable {
    @EJB
    private UsuarioDAO<Usuario> dao;
    private Usuario objeto;
    private Boolean editando;
    @EJB
    private Tipo_UsuarioDAO<Tipo_Usuario> daoTipo_Usuario;
    private Tipo_Usuario tipo_usuario;
    private Boolean editandoTipo_Usuario;
    
    public ControleUsuario(){
        editando = false;
        editandoTipo_Usuario = false;
    }
    
    public String listar(){
        editando = false;
        editandoTipo_Usuario = false;
        return "/privado/usuario/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        editandoTipo_Usuario = false;
        objeto = new Usuario();        
    }
    
    public void alterar(Integer id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoTipo_Usuario = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+ Util.getMensagemErro(e));
        }   
    }
    
    public void excluir(Integer id){
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "+ Util.getMensagemErro(e));
        }   
    }    
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e){
            Util.mensagemErro("Erro ao persistir objeto: "+Util.getMensagemErro(e));
        }
    }
    
    public void novoTipo_Usuario(){
        editandoTipo_Usuario = true;
    }
    
    public void salvarTipo_Usuario(){
        if(!objeto.getTipos_usuarios().contains(tipo_usuario)){
            objeto.getTipos_usuarios().add(tipo_usuario);
            Util.mensagemInformacao("Tipo de usuario adicionado com sucesso!");
        } else {
            Util.mensagemErro("Usuário já possui esse tipo de usuario!");
        }
        editandoTipo_Usuario = false;
    }
    
    public void removerTipo_Usuario(Tipo_Usuario obj){
        objeto.getTipos_usuarios().remove(obj);
        Util.mensagemInformacao("Permissão removida com sucesso!");
    }

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Usuario objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public Tipo_UsuarioDAO<Tipo_Usuario> getDaoTipo_Usuario() {
        return daoTipo_Usuario;
    }

    public void setDaoTipo_Usuario(Tipo_UsuarioDAO<Tipo_Usuario> daoTipo_Usuario) {
        this.daoTipo_Usuario = daoTipo_Usuario;
    }

    public Tipo_Usuario getTipo_Usuario() {
        return tipo_usuario;
    }

    public void setTipo_Usuario(Tipo_Usuario tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Boolean getEditandoTipo_Usuario() {
        return editandoTipo_Usuario;
    }

    public void setEditandoTipo_Usuario(Boolean editandoTipo_Usuario) {
        this.editandoTipo_Usuario = editandoTipo_Usuario;
    }

}
