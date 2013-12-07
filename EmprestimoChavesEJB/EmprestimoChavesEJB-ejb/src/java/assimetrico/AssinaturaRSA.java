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
public class AssinaturaRSA {
    public static void main(String [] args) throws Exception
    {
        //define as variáveis
        KeyPairGenerator chaves = null;
        Signature sgn = null;
        Signature vrf = null;
        byte [] assina = null;

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
        
        //saídas parte de assinatura
        System.out.println("Chave privada: " + priv);
        System.out.println("Chave pública: " + pub);
        System.out.println("Mensagem: " + mensagem);
        
        System.out.println("Assinatura (array): " + Arrays.toString(assina));
        System.out.println("Assinatura (string): " + new String(assina));
        
        //*** inicia a verificação ***

        // cria uma instância do RSA com MD5
        vrf = Signature.getInstance("MD5withRSA");
        
        //inicializa uma verificação com a chave pública do parâmetro
        vrf.initVerify(pub);
        
        //adiciona o texto ao algoritmo
        vrf.update(mensagem.getBytes());
        
        //faz a verificação com a assinatura do parâmetro
        boolean ok = vrf.verify(assina);
        
        //saída da parte de verificação
        System.out.println("Validou: " + ok);
    }
}
