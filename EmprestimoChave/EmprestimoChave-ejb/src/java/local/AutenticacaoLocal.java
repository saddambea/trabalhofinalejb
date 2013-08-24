/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import remote.AutenticacaoRemote;

/**
 *
 * @author dflenzi
 */
public class AutenticacaoLocal implements AutenticacaoRemote{

    @Override
    public void login() {
        System.out.println("chamou o login");
    }
    
    
}
