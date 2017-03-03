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
public class LuminosidadeValues implements Sensor{

     private HashMap<LocalDate,Vector<Integer>> luz= new HashMap<>();
    
     
    @Override
    public Vector<Integer> getNodo(LocalDate d){
        return luz.get(d);
    }
    
    
    @Override
    public void media(LocalDate data) {
         if (false != this.luz.containsKey(data)) {

                    int sum = 0;
                    for (Integer val : this.luz.get(data)) {
                        sum += val;
                    }

                    System.out.println("Média luminosidade: " + sum/this.luz.get(data).size());
    }
    }

    @Override
    public void max_minimo(LocalDate data) {
         if ( false != this.luz.containsKey(data)){
                Vector<Integer> v = this.luz.get(data);
                    int max = Collections.max(v);
                    int min = Collections.min(v);
                    System.out.println("Max luminosidade: "+max+" Min luminosidade: "+min);
        }
    }

    @Override
     public void ultimos_dias(int dias) {
        int dias_counter = dias-1;
        HashMap<LocalDate, Vector<Integer>> last_values = new HashMap<LocalDate, Vector<Integer>>();
        Vector max_min_values = new Vector();
        LocalDate today = LocalDate.now();
        
        while (dias_counter >= 0) {
         if (false != this.luz.containsKey(today.minusDays(dias_counter))) 
          {
            Vector<Integer> temp_values = this.luz.get(today.minusDays(dias_counter));
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
        if (luz.containsKey(LocalDate.now())){
            int size = this.luz.get(LocalDate.now()).size();
            i = this.luz.get(LocalDate.now()).elementAt(size-1);
        }
        System.out.println("Luminosidade Actual: " + i);
    }
    
    @Override
    public void add(LocalDate d, Vector<Integer> v) {
        luz.put(d, v);
    }
}
