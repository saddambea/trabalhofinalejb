/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import static simetrico.CifraDESede.resumo_chave;

/**
 *
 * @author dflenzi
 */
public class Criptografia {
    private static String palavra_chave = "dados";    
            
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
    
    
}
