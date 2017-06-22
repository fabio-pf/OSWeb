
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class UsuarioDAO<T> extends DAOGenerico<Usuario> implements Serializable {

    public UsuarioDAO(){
        super();        
        super.classePersistente = Usuario.class;
    }
    
    @Override
    public Usuario getObjectById(Integer id) throws Exception {
        Usuario obj = (Usuario) em.find(classePersistente, id);
        obj.getTipos_usuarios().size();
        return obj;
    }
    
    public Usuario localizaPorNomeUsuario(String usuario){
        Usuario obj = (Usuario) em.createQuery("from Usuario where upper(usuario) = :usuario").
                setParameter("usuario", usuario.toUpperCase()).getSingleResult();
        obj.getTipos_usuarios().size();
        return obj;
    }
    
}
