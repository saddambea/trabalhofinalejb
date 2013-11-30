package simetrico;


import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

public class CifraDESede {
    
  public static byte [] resumo_chave(String s) throws Exception {
      //define a variável do hash
      MessageDigest md = null;
      //cria uma instância do gerador de hash
      md = MessageDigest.getInstance("SHA-256");
      //coloca a mensagem no gerador de hash
      md.update(s.getBytes());
      //gera o hash
      byte [] resumo = md.digest();
      //saída do exemplo
      System.out.println("Resumo (array): " + Arrays.toString(resumo));
      System.out.println("Resumo (string): " + new String(resumo));
      
      return resumo;
  }
  

  public static void main(String args[]) throws Exception {
      
	  
	  String texto = "abc";
	  
	 //escolha entre um pass_frase informado ou automático 
	 //String pass_frase = null;
	 String pass_frase = "z";
	 SecretKey secretKey = null;
	  
	 if  (pass_frase == null) {
	    //Cria a chave - gerada
		KeyGenerator kgen = KeyGenerator.getInstance("DESede");        
		secretKey = kgen.generateKey();        
	 }
	 else {
		//Cria a chave - entrada
	    byte key[] = resumo_chave(pass_frase);
	    DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
	    secretKey = keyFactory.generateSecret(desKeySpec);
	 }
  	 System.out.println("chave (string): " + new String(secretKey.getEncoded()));
  	 System.out.println("chave (array): " + Arrays.toString(secretKey.getEncoded()));

    
    
    
    //Cria uma instância do cifrador
    Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
    desCipher.init(Cipher.ENCRYPT_MODE, secretKey);// modo encriptação

    byte[] encrypted = desCipher.doFinal(texto.getBytes());
    System.out.println("texto cifrado (string): " + new String(encrypted));
    System.out.println("texto cifrado (array): " + Arrays.toString(encrypted));
    
     //altera o modo do cifrador para DECRIPTAR
    desCipher.init(Cipher.DECRYPT_MODE, secretKey);

     
    byte[] decrypted = desCipher.doFinal(encrypted);
	System.out.println("texto decifrado (string): " + new String(decrypted));
	System.out.println("texto decifrado (array): " + Arrays.toString(decrypted));
    
  }
}
           