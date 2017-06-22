
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Servico;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class ServicoDAO<T> extends DAOGenerico<Servico> implements Serializable {

    public ServicoDAO(){
        super();
        super.classePersistente = Servico.class;
    }
    
    
            
  
}
