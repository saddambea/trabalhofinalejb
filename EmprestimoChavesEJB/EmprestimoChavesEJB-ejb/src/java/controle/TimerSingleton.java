/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Schedules;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import modelo.Emprestimo;
import modelo.Usuario;

/**
 *
 * @author dflenzi
 */
@Startup
@Singleton
public class TimerSingleton {

    
            
    @Resource
    private TimerService timerService;
    private Timer timer;
    @Temporal(TemporalType.TIMESTAMP)
    private SimpleDateFormat data = new SimpleDateFormat("HH:mm:ss");
    
    @EJB
    private EnviarEmail enviaremail;
    
    @EJB
    private EmprestimoControle emprestimocontrole;
    
    @EJB
    private FuncoesGeraisControle funcoesgeraiscontrole;
    
    @EJB
    private UsuarioControle usuariocontrole;
      
    @PostConstruct
    public void iniciarTimer() {
        ScheduleExpression sc = new ScheduleExpression();
        timer = timerService.createCalendarTimer(sc);
        System.out.println("Iniciou o timer");
    }

    @Timeout()
    public void finalizar(Timer timer) {
        System.out.println("Foi o tempo " + data.format(Calendar.getInstance().getTime()));
    }

    @Schedules({
        @Schedule(hour="12"),
        @Schedule(hour="18"),
        @Schedule(hour="22", minute = "30")
    })
    public void executaTimer(Timer timer) throws Exception {
        String corpo = "";
        List<Emprestimo> emprestimosativos =  emprestimocontrole.getEmprestimosAtivos();
        for(Emprestimo emp : emprestimosativos){
            corpo = corpo + "chave : " + emp.getChave().getSigla() 
                    + " emprestada pelo usuário " + emp.getUsuario().getNome()
                    + " na data " + funcoesgeraiscontrole.getDataHora(emp.getDataEmprestimo()) + "\n";
                     
                         
        }
        
        if(!corpo.isEmpty()){
          Usuario usuadm = usuariocontrole.getUsuarioAdministrador();   
          if (usuadm !=null){
              enviaremail.enviarEmail(new String(usuadm.getEmail()), "Chaves emprestadas", corpo);        
              System.out.println("E-mail enviado para o usuario:" + usuadm.getEmail() + ". Chaves emprestadas: " + corpo);
          }
              
            
        }
        
    }
}
