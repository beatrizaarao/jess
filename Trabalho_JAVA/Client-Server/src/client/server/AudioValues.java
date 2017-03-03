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
public class AudioValues implements Sensor,Serializable{
    
    private HashMap<LocalDate,Vector<Integer>> audio = new HashMap<>();
    
    @Override
    public Vector<Integer> getNodo(LocalDate d){
        return audio.get(d);
    }

    @Override
    public String media(LocalDate data) {
        if (false != this.audio.containsKey(data)) {

                    int sum = 0;
                    for (Integer val : this.audio.get(data)) {
                        sum += val;
                    }

                    return("Média audio: " + sum/this.audio.get(data).size());
    }
        return("");
    }

    @Override
    public String max_minimo(LocalDate data) {
         if ( false != this.audio.containsKey(data)){
                Vector<Integer> v = this.audio.get(data);
                    int max = Collections.max(v);
                    int min = Collections.min(v);
         return("Max audio: "+max+" Min audio: "+min);
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
         if (false != this.audio.containsKey(today.minusDays(dias_counter))) 
          {
            Vector<Integer> temp_values = this.audio.get(today.minusDays(dias_counter));
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
        if (audio.containsKey(LocalDate.now())){
            int size = this.audio.get(LocalDate.now()).size();
            i = this.audio.get(LocalDate.now()).elementAt(size-1);
        }
        return("Audio Actual: " + i);
    }

    @Override
    public void add(LocalDate d, Vector<Integer> v) {
        audio.put(d, v);
    }
    
}
