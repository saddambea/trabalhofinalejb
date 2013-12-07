/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AutorizacaoDAO;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Autorizacao;

/**
 *
 * @author dflenzi
 */

@Stateless
public class AutorizacaoControle{

    /**
     * Creates a new instance of UsuarioBean
     */
    @EJB
    AutorizacaoDAO autDAO;
    

 
    public AutorizacaoControle() {
    }

    public List<Autorizacao> getAutorizacoes() {
        try {
            return autDAO.listarTodos();
        } catch (Exception e) {
            return null;
        }
        
    }


    public Autorizacao getAutorizacao(Integer id) {        
        try {
            return autDAO.carregar(id);
        } catch (Exception e) {
            return null;
        }
        
    }

    
    public boolean salvar(Autorizacao autorizacao){
        try {
         autDAO.salvar(autorizacao);
         return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean excluir(Autorizacao oAutorizacao) {
        try {
            autDAO.excluir(oAutorizacao);
        } catch (Exception e) {
        }
        return true;      
        
    }
    
    public boolean getAssinaRSA() throws Exception{
        //define as variáveis
        KeyPairGenerator chaves = null;
        Signature sgn = null;
        Signature vrf = null;
        byte [] assina = null;
        
        try{
            //inicializa e gera as chaves
            chaves = KeyPairGenerator.getInstance("RSA");
            chaves.initialize(1024);
            System.out.println("Gerando o par de chaves...");
            KeyPair par = chaves.generateKeyPair(); //gera as chaves        
            PrivateKey priv = par.getPrivate();   //obtem chave privada
            PublicKey pub = par.getPublic();  //obtem chave pública

            //cria uma instância do RSA com MD5
            sgn = Signature.getInstance("MD5withRSA");

            //inicializa o algoritmo de assinatura
            sgn.initSign(priv); 

            //adiciona a mensagem ao algoritmo
            String mensagem = "abc";
            sgn.update(mensagem.getBytes());

            //faz a assinatura
            assina = sgn.sign();
            
            //*** inicia a verificação ***

            // cria uma instância do RSA com MD5
            vrf = Signature.getInstance("MD5withRSA");

            //inicializa uma verificação com a chave pública do parâmetro
            vrf.initVerify(pub);

            //adiciona o texto ao algoritmo
            vrf.update(mensagem.getBytes());

            //faz a verificação com a assinatura do parâmetro
            boolean ok = vrf.verify(assina);
            return ok;
        }catch(Exception e){
            throw new Exception("Erro! Não faça isso");
        }
        
    }
    
    public boolean getAssinaDSA() throws Exception{
        //define as variáveis
        KeyPairGenerator chaves = null;
        Signature sgn = null;
        Signature vrf = null;
        byte [] assina = null;
        
        try{
            //define uma mensagem
            String mensagem = "teste teste";

            //cria uma instância do gerador de chaves 
            chaves = KeyPairGenerator.getInstance("DSA");

            //cria uma instância do DSA
            sgn = Signature.getInstance("DSA");

            //inicializa e gera as chaves
            chaves.initialize(512);
            System.out.println("Gerando o par de chaves...");
            KeyPair par = chaves.generateKeyPair(); //gera as chaves        
            PrivateKey priv = par.getPrivate();   //obtem chave privada
            PublicKey pub = par.getPublic();  //obtem chave pública

            sgn.initSign(priv); //inicializa o algoritmo de assinatura

            //adiciona a mensagem ao algoritmo
            sgn.update(mensagem.getBytes());

            //faz a assinatura
            assina = sgn.sign();
            
            boolean ok;

            //inicia a verificação
            // cria uma instância do DAS
            vrf = Signature.getInstance("DSA");

            //inicializa uma verificação com a chave pública do parâmetro
            vrf.initVerify(pub);

            //adiciona o texto ao algoritmo
            vrf.update(mensagem.getBytes());

            //faz a verificação com a assinatura do parâmetro
            ok = vrf.verify(assina);

            //saída da parte de verificação
            return ok;
        }catch(Exception e){
            throw new Exception("Erro! Não Faça isso!");
        }
        
    }

    
}
