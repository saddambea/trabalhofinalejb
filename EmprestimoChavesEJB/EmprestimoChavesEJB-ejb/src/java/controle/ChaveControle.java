/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ChaveDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.util.List;
import javax.crypto.Cipher;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.security.cert.X509Certificate;
import modelo.Chave;



@Stateless
public class ChaveControle{

    /**
     * Creates a new instance of ChaveControle
     */

    @EJB
    private ChaveDAO chaveDAO;



    public List<Chave> getChaves() throws Exception{ 
        try {
          return  chaveDAO.listarTodos();
        } catch (Exception e) {
            return null;
        }
    }

    public Chave getChave(Integer id) throws Exception {
        return chaveDAO.carregar(id);
    }

    public Boolean excluir(Chave chave) throws Exception {
        try {
            chaveDAO.excluir(chave);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean salvar(Chave oChave) throws Exception {
        try {
            File arquivo;   
            arquivo = new File("C:\\Temp\\CriptoComCertificado.txt"); 
            FileOutputStream fos = new FileOutputStream(arquivo); 
            
            //criptgrafar a sigla
            FileInputStream fr = new FileInputStream("C:\\temp\\cert_exportado.cer");
            //cria a instância do certificado
            X509Certificate certif = X509Certificate.getInstance(fr);

            PublicKey publica = certif.getPublicKey();

            fos.write("Sigla da chave: ".getBytes());
            fos.write(oChave.getSigla().getBytes());
            
            fos.write("\n\nSigla criptografada: ".getBytes());
            
            //Verificação do certificado                     
            //Neste caso, auto assinado
            certif.verify(publica);
            //Verifica a validade
            certif.checkValidity();
            //cifrar com a chave pública
            Cipher cifra = Cipher.getInstance("RSA");  
            // Criptografa a mensagem com a chave pública  
            // inicializa o algoritmo para a criptografia  
            cifra.init(Cipher.ENCRYPT_MODE, publica);  
            // criptgrafia o texto inteiro  
            byte[] mensagemCifrada = cifra.doFinal(oChave.getSigla().getBytes());  
            
            fos.write(mensagemCifrada);
            
            //decriptar
            KeyStore ks = KeyStore.getInstance("JKS");
            char[] passPhrase = "bernardo".toCharArray();
            //BASE64Encoder myB64 = new BASE64Encoder();

            File certificateFile = new File("C:\\temp\\keystore1.jks");
            ks.load(new FileInputStream(certificateFile), passPhrase);

            KeyPair kp = getPrivateKey(ks, "aliasnovo", "outrasenha".toCharArray());

            PrivateKey privKey = kp.getPrivate();

            //char[] b64 = Base64Coder.encode(privKey.getEncoded());

            cifra.init(Cipher.DECRYPT_MODE,privKey);  
            byte[] mensagemOriginal = cifra.doFinal(mensagemCifrada);  
            
            fos.write("\n\nSigla DEcriptografada: ".getBytes());
            
            fos.write(mensagemOriginal);
            //fos.write(new String(b64).getBytes());
            
            fos.close();
            
            chaveDAO.salvar(oChave);            
            return true;
        } catch (Exception e) {
            throw e;//return false;
        }
    }
    
    public KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {
     try {
         // Get private key
         Key key = keystore.getKey(alias, password);
         if (key instanceof PrivateKey) {
             // Get certificate of public key
             java.security.cert.Certificate cert = keystore.getCertificate(alias);
 
             // Get public key
             PublicKey publicKey = cert.getPublicKey();
 
             // Return a key pair
             return new KeyPair(publicKey, (PrivateKey)key);
         }
     } catch (UnrecoverableKeyException e) {
     } catch (NoSuchAlgorithmException e) {
     } catch (KeyStoreException e) {
     }
     return null;
 }



    
}
