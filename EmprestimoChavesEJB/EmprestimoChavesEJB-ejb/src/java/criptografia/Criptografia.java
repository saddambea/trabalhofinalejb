/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import static simetrico.CifraDESede.resumo_chave;

/**
 *
 * @author dflenzi
 */
public class Criptografia {
    private static final String palavra_chave = "dados";    
            
    public byte[] getSecretKey(String _palavraChave) { 
        SecretKey secretKey = null;
        
        if(_palavraChave.isEmpty())
            _palavraChave = palavra_chave;
                    
        try{
            byte key[] = resumo_chave(_palavraChave);
            DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            secretKey = keyFactory.generateSecret(desKeySpec);
            return secretKey.getEncoded();
        } catch(Exception e) { return null; }
    }
    
    
    public byte[] getCriptografia(String cripto){
            SecretKey secretKey = null;
            try{
                byte key[] = resumo_chave(palavra_chave);
                DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
                secretKey = keyFactory.generateSecret(desKeySpec);
                
                //Cria uma instância do cifrador
                Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
                desCipher.init(Cipher.ENCRYPT_MODE, secretKey);// modo encriptação

                byte[] encrypted = desCipher.doFinal(cripto.getBytes());
                System.out.println("texto cifrado (string): " + new String(encrypted));
                System.out.println("texto cifrado (array): " + Arrays.toString(encrypted));
                return encrypted;
                
            }catch(Exception e){
                System.out.println("Lixo");
                return null;
            }
    }

    public String getDecriptografa(byte []  encrypted){
        try 
          {
              
            byte key[] = resumo_chave(palavra_chave);
            DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
             //altera o modo do cifrador para DECRIPTAR            
            desCipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decrypted = desCipher.doFinal(encrypted);
                System.out.println("texto decifrado (string): " + new String(decrypted));
                System.out.println("texto decifrado (array): " + Arrays.toString(decrypted));
        return new String (decrypted);
        } catch (Exception e) {
            return  "Erro";
        }
    }
    
     public KeyPair getChaves(){
        KeyPair chaves = null;
        try
        {
           KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");  
           gerador.initialize(1024);  
           chaves = gerador.generateKeyPair();  
        } catch(NoSuchAlgorithmException e) {}
        return chaves;
     }
    
     public byte[] getCriptografadoRSA(PublicKey publica, byte[] conteudo ) {
        Cipher cifra;  
        try {
            cifra = Cipher.getInstance("RSA");
             //Inicializa o algoritmo para criptografiar a mensagem com a chave pública  
            cifra.init(Cipher.ENCRYPT_MODE, publica);  
             // Criptgrafa o texto inteiro  
            byte[] mensagemCifrada = cifra.doFinal(conteudo);  
            //retorna o conteudo criptografado com a chave publica
            return mensagemCifrada;
        
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
     
     public byte[] getDeCriptografadoRSA(PrivateKey privada, byte[] criptografado) {
        Cipher cifra;
        try {
            cifra = Cipher.getInstance("RSA");
            cifra.init(Cipher.DECRYPT_MODE, privada);  
            
            byte[] mensagemOriginal = cifra.doFinal(criptografado);
            
            return mensagemOriginal;
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Criptografia.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return null;
     }
     
    public byte[] getCriptografiaPublicaPrivada(){
         try{
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
            return mensagemOriginal;
         }catch(Exception e){
             System.out.println("Não foi");
             return null;
         }         
    }
    
    
    public byte[] getCriptografiaRSA(String texto) throws Exception{
    
         // Gera um par de chaves RSA de 1024 bits  
         KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");  
         gerador.initialize(1024);  
         KeyPair chaves = gerador.generateKeyPair();  

         // Cria uma implementação do RSA  
         Cipher cifra = Cipher.getInstance("RSA");  

         //Inicializa o algoritmo para criptografiar a mensagem com a chave pública  
         cifra.init(Cipher.ENCRYPT_MODE,chaves.getPublic());  

         // Criptgrafa o texto inteiro  
         byte[] mensagemCifrada = cifra.doFinal(texto.getBytes());  

         // Inicializa o algoritmo para decriptografar com a chave privada
         cifra.init(Cipher.DECRYPT_MODE,chaves.getPrivate());  
         
         // Decifra a mensagem
         byte[] mensagemOriginal = cifra.doFinal(mensagemCifrada);  

         //saídas do exemplo        
         //System.out.println("Chave Publica: "+chaves.getPublic().toString());  
         //System.out.println("Chave Privada: "+chaves.getPrivate().toString());  

         //System.out.print("texto cifrado (string): " + new String(mensagemCifrada));  
         //System.out.print("texto decifrado (string): " + new String(mensagemOriginal));       
         return mensagemCifrada;
    }
    
    public byte[] getDescriptografiaRSA(byte[] cripto) throws Exception{
    
         // Gera um par de chaves RSA de 1024 bits  
         KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");  
         gerador.initialize(1024);  
         KeyPair chaves = gerador.generateKeyPair();  

         // Cria uma implementação do RSA  
         Cipher cifra = Cipher.getInstance("RSA");  

         //Inicializa o algoritmo para criptografiar a mensagem com a chave pública  
         //cifra.init(Cipher.ENCRYPT_MODE,chaves.getPublic());  

         // Criptgrafa o texto inteiro  
         //byte[] mensagemCifrada = cifra.doFinal(texto.getBytes());  

         // Inicializa o algoritmo para decriptografar com a chave privada
         cifra.init(Cipher.DECRYPT_MODE,chaves.getPrivate());  
         
         // Decifra a mensagem
         byte[] mensagemOriginal = cifra.doFinal(cripto);  

         //saídas do exemplo        
         //System.out.println("Chave Publica: "+chaves.getPublic().toString());  
         //System.out.println("Chave Privada: "+chaves.getPrivate().toString());  

         //System.out.print("texto cifrado (string): " + new String(mensagemCifrada));  
         //System.out.print("texto decifrado (string): " + new String(mensagemOriginal));       
         return mensagemOriginal;
    }

    
    
}
