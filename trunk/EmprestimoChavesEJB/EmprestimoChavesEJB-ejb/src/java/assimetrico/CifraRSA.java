package assimetrico;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import javax.crypto.Cipher;

public class CifraRSA {
    
   public static void main(String args[]) throws Exception {
       
         // Gera um par de chaves RSA de 1024 bits  
         KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");  
         gerador.initialize(1024);  
         KeyPair chaves = gerador.generateKeyPair();  

         // Cria uma implementação do RSA  
         Cipher cifra = Cipher.getInstance("RSA");  

         //Inicializa o algoritmo para criptografiar a mensagem com a chave pública  
         cifra.init(Cipher.ENCRYPT_MODE,chaves.getPublic());  

         // Criptgrafa o texto inteiro  
         byte[] mensagemCifrada = cifra.doFinal("teste".getBytes());  

         // Inicializa o algoritmo para decriptografar com a chave privada
         cifra.init(Cipher.DECRYPT_MODE,chaves.getPrivate());  
         
         // Decifra a mensagem
         byte[] mensagemOriginal = cifra.doFinal(mensagemCifrada);  

         //saídas do exemplo        
         System.out.println("Chave Publica: "+chaves.getPublic().toString());  
         System.out.println("Chave Privada: "+chaves.getPrivate().toString());  

         System.out.print("texto cifrado (string): " + new String(mensagemCifrada));  
         System.out.print("texto decifrado (string): " + new String(mensagemOriginal));        
  }
   
}