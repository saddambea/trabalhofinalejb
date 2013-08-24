/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import dao.JPADAO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author dflenzi
 */
@WebListener
public class EmprestimoChaveListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JPADAO.inicializar("PraticasJPAPU");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JPADAO.getInstancia().fechar();
    }
    
    
}
