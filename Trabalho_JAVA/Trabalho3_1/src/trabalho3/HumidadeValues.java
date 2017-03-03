/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author beatrizaarao
 */
public class HumidadeValues implements Sensor{

     private HashMap<LocalDate,Vector<Integer>> humidade;
    
     public HumidadeValues(){
        humidade = new HashMap<>();
     }
     
     @Override
    public Vector<Integer> getNodo(LocalDate d){
        return humidade.get(d);
    }
    
    
    @Override
    public void media(LocalDate data) {
         if (false != this.humidade.containsKey(data)) {

                    int sum = 0;
                    for (Integer val : this.humidade.get(data)) {
                        sum += val;
                    }

                    System.out.println("Média humidade: " + sum/this.humidade.get(data).size());
    }
    }

    @Override
    public void max_minimo(LocalDate data) {
         if ( false != this.humidade.containsKey(data)){
                Vector<Integer> v = this.humidade.get(data);
                    int max = Collections.max(v);
                    int min = Collections.min(v);
                    System.out.println("Max humidade: "+max+" Min humidade: "+min);
        }
    }

    @Override
    public void ultimos_dias(int dias) {
        int dias_counter = dias-1;
        HashMap<LocalDate, Vector<Integer>> last_values = new HashMap<LocalDate, Vector<Integer>>();
        Vector max_min_values = new Vector();
        LocalDate today = LocalDate.now();
        
        while (dias_counter >= 0) {
         if (false != this.humidade.containsKey(today.minusDays(dias_counter))) 
          {
            Vector<Integer> temp_values = this.humidade.get(today.minusDays(dias_counter));
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
        if (humidade.containsKey(LocalDate.now())){
            int size = this.humidade.get(LocalDate.now()).size();
            i = this.humidade.get(LocalDate.now()).elementAt(size-1);
        }
        System.out.println("Humidade Actual: " + i);
    }
    
    @Override
    public void add(LocalDate d, Vector<Integer> v) {
        humidade.put(d, v);
    }
    
}
