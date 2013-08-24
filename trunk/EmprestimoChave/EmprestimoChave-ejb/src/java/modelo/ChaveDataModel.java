/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.List;  
import javax.faces.model.ListDataModel;  
import modelo.Chave;

import org.primefaces.model.SelectableDataModel;  
  
public class ChaveDataModel extends ListDataModel<Chave> implements SelectableDataModel<Chave> {    
  
    public ChaveDataModel() {  
    }  
  
    public ChaveDataModel(List<Chave> data) {  
        super(data);  
    }  
      
    @Override  
    public Chave getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Chave> chaves = (List<Chave>) getWrappedData();  
          
        for(Chave chave : chaves) {  
            if(chave.getId() == Integer.parseInt(rowKey))  
                return chave;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Chave chave) {  
        return chave.getId();
    }  
}  