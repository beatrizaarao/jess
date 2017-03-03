/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author beatrizaarao
 */
public class PressaoValues implements Sensor,Serializable{

     private HashMap<LocalDate,Vector<Integer>> pressao= new HashMap<>();
    
    @Override
    public Vector<Integer> getNodo(LocalDate d){
        return pressao.get(d);
    }
    
    @Override
    public String media(LocalDate data) {
         if (false != this.pressao.containsKey(data)) {

                    int sum = 0;
                    for (Integer val : this.pressao.get(data)) {
                        sum += val;
                    }

                    return("Média pressao: " + sum/this.pressao.get(data).size());
    }
         return ("");
    }

    @Override
    public String max_minimo(LocalDate data) {
         if ( false != this.pressao.containsKey(data)){
                Vector<Integer> v = this.pressao.get(data);
                    int max = Collections.max(v);
                    int min = Collections.min(v);
                    return("Max pressao: "+max+" Min pressao: "+min);
        }
         return("");
    }

    @Override
     public String ultimos_dias(int dias) {
        int dias_counter = dias-1;
        HashMap<LocalDate, Vector<Integer>> last_values = new HashMap<LocalDate, Vector<Integer>>();
        Vector max_min_values = new Vector();
        LocalDate today = LocalDate.now();
        
        while (dias_counter >= 0) {
         if (false != this.pressao.containsKey(today.minusDays(dias_counter))) 
          {
            Vector<Integer> temp_values = this.pressao.get(today.minusDays(dias_counter));
            max_min_values.add(Collections.max(temp_values));
            max_min_values.add(Collections.min(temp_values));
            last_values.put(today.minusDays(dias_counter), max_min_values);
          }
         dias_counter -= 1;
        }
        
        return("Valores máximos e mínimos: "+last_values.toString());
    }

    @Override
    public String valorAtual() {
        int i = -100;
        if (pressao.containsKey(LocalDate.now())){
            int size = this.pressao.get(LocalDate.now()).size();
            i = this.pressao.get(LocalDate.now()).elementAt(size-1);
        }
        return("Pressao Atmosferica Actual: " + i);
    }
    
    @Override
    public void add(LocalDate d, Vector<Integer> v) {
        pressao.put(d, v);
    }
    
}
