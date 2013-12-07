/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assimetrico;
import java.security.*;
import java.util.Arrays;
/**
 *
 * @author abaehr
 */
public class AssinaturaDSA {
   public static void main(String [] args) throws Exception
    {
        //define as variáveis
        KeyPairGenerator chaves = null;
        Signature sgn = null;
        Signature vrf = null;
        byte [] assina = null;

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
        
        //saídas parte de assinatura
        System.out.println("Chave privada: " + priv);
        System.out.println("Chave pública: " + pub);
        System.out.println("Mensagem: " + mensagem);
        
        System.out.println("Assinatura (array): " + Arrays.toString(assina));
        System.out.println("Assinatura (string): " + new String(assina));
        
        
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
        System.out.println("Validou: " + ok);
    } 
}
