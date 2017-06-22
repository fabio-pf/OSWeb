package br.edu.ifsul.controle;

import br.edu.ifsul.dao.OSDAO;
import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.OS;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.modelo.Usuario;
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
@Named(value = "controleOS")
@SessionScoped
public class ControleOS implements Serializable {

    @EJB
    private OSDAO<OS> dao;
    private OS objeto;
    private Boolean editando;
    @EJB
    private ServicoDAO<Servico> daoSetor;
    @EJB
    private UsuarioDAO<Usuario> daoUsuario;
    

    public ControleOS() {
        editando = false;
    }

    public String listar() {
        editando = false;
        return "/privado/os/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        objeto = new OS();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }

    }

    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
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


    public OS getObjeto() {
        return objeto;
    }

    public void setObjeto(OS objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public OSDAO<OS> getDao() {
        return dao;
    }

    public void setDao(OSDAO<OS> dao) {
        this.dao = dao;
    }

    public ServicoDAO<Servico> getDaoSetor() {
        return daoSetor;
    }

    public void setDaoSetor(ServicoDAO<Servico> daoSetor) {
        this.daoSetor = daoSetor;
    }

    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }
}
