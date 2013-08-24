/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package remote;

import javax.ejb.Remote;

/**
 *
 * @author dflenzi
 */
@Remote
public interface AutenticacaoRemote {
    public void login();
    
}
