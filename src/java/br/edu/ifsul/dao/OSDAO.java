package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.OS;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br Instituto Federal
 * Sul-Rio-Grandense Campus Passo Fundo
 */
@Stateful
public class OSDAO<T> extends DAOGenerico<OS> implements Serializable {

    public OSDAO() {
        super();
        super.setClassePersistente(OS.class);
        super.setOrdem("id");        
    }
 
    @Override
    public OS getObjectById(Integer id) throws Exception {
        OS obj = (OS) super.getEm().find(super.getClassePersistente(), id);
        return obj;
    }       
    
}
