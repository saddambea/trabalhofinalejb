/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AutorizacaoDAO;
import dao.UsuarioDAO;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.ArrayList;
import modelo.Usuario;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import modelo.Autorizacao;
import static simetrico.CifraDESede.resumo_chave;


@Stateful
public class UsuarioControle{

    /**
     * Creates a new instance of UsuarioControle
     */
    @EJB
    private dao.JPADAO conexao;
    
    @EJB
    private UsuarioDAO usuarioDAO;
    
    @EJB
    private AutorizacaoDAO autorizacaoDAO;
    

    public List<Usuario> getUsuarios() throws Exception {
        return usuarioDAO.listarTodos();
    }

    public Boolean excluir(Usuario usuario) {
        try {
            usuarioDAO.excluir(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
        
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

    public SecretKey getCriptografia(String cripto){
            SecretKey secretKey = null;
            try{
                byte key[] = resumo_chave(cripto);
                DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
                secretKey = keyFactory.generateSecret(desKeySpec); 
            }catch(Exception e){
                System.out.println("Lixo");
            }
            
            
            return secretKey;
    }
    
    public boolean salvar(Usuario usuario) {
        try {
            usuario.setEmail(new String(getCriptografia(usuario.getEmail()).getEncoded()));
            usuarioDAO.salvar(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
  
        
    }

    public Usuario buscarUsuario(int idUsuario) throws Exception{
        return usuarioDAO.carregar(idUsuario);
    }

   
    
    public Usuario getUsuarioByCodigo(int codigo){        
        return usuarioDAO.buscarSimples(codigo);
    }
    
    public Usuario getUsuarioAdministrador(){
        try {
            List<Usuario> lista = usuarioDAO.listarTodos();
            for(Usuario usu : lista){
                if(usu.getTipo().getPermUsuario() ||
                   usu.getTipo().getDescricao().equals("Administrador")){
                    return usu;
                }
            }
            
        } catch (Exception e) {
            return null;
        }
        
        return null;
    }
    public List<Autorizacao> getAutorizacoes(Usuario usuario) throws Exception{
       List<Autorizacao> lista = new ArrayList<Autorizacao>();
       List<Autorizacao> todas =  new ArrayList<Autorizacao>();
       todas = autorizacaoDAO.listarTodos();
       for(Autorizacao aut : todas){
           if (aut.getUsuario() != null){
            if(aut.getUsuario().getId() == usuario.getId()){
                 lista.add(aut);
               }
           } 
       }
       return lista;
    }

    
    
    
}
