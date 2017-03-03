/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author beatrizaarao
 */
public class AudioValues implements Sensor{
    
    private HashMap<LocalDate,Vector<Integer>> audio = new HashMap<>();
    
    @Override
    public Vector<Integer> getNodo(LocalDate d){
        return audio.get(d);
    }

    @Override
    public void media(LocalDate data) {
        if (false != this.audio.containsKey(data)) {

                    int sum = 0;
                    for (Integer val : this.audio.get(data)) {
                        sum += val;
                    }

                    System.out.println("Média audio: " + sum/this.audio.get(data).size());
    }
    }

    @Override
    public void max_minimo(LocalDate data) {
         if ( false != this.audio.containsKey(data)){
                Vector<Integer> v = this.audio.get(data);
                    int max = Collections.max(v);
                    int min = Collections.min(v);
                    System.out.println("Max audio: "+max+" Min audio: "+min);
        }
    }

    @Override
     public void ultimos_dias(int dias) {
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
        
        System.out.println("Valores máximos e mínimos: "+last_values.toString());
    }

    @Override
    public void valorAtual() {
        int i = -100;
        if (audio.containsKey(LocalDate.now())){
            int size = this.audio.get(LocalDate.now()).size();
            i = this.audio.get(LocalDate.now()).elementAt(size-1);
        }
        System.out.println("Audio Actual: " + i);
    }

    @Override
    public void add(LocalDate d, Vector<Integer> v) {
        audio.put(d, v);
    }
    
}
