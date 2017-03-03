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
public class TemperatureValues implements Sensor{

     private HashMap<LocalDate,Vector<Integer>> temperatura;
    
     
     public TemperatureValues(){
        temperatura = new HashMap<>();
     }
     
    @Override
    public Vector<Integer> getNodo(LocalDate d){
        return temperatura.get(d);
    }

    @Override
    public void media(LocalDate data) {
        if (false != this.temperatura.containsKey(data)) {

                    int sum = 0;
                    for (Integer val : this.temperatura.get(data)) {
                        sum += val;
                    }

                    System.out.println("Média temperatura: " + sum/this.temperatura.get(data).size());
    }
   }
    @Override
    public void max_minimo(LocalDate data) {
        if ( false != this.temperatura.containsKey(data)){
                Vector<Integer> v = this.temperatura.get(data);
                    int max = Collections.max(v);
                    int min = Collections.min(v);
                    System.out.println("Max temperatura: "+max+" Min temperatura: "+min);
        }
    }
    
    @Override
    public void ultimos_dias(int dias) {
        int dias_counter = dias-1;
        HashMap<LocalDate, Vector<Integer>> last_values = new HashMap<LocalDate, Vector<Integer>>();
        Vector max_min_values = new Vector();
        LocalDate today = LocalDate.now();
        
        while (dias_counter >= 0) {
         if (false != this.temperatura.containsKey(today.minusDays(dias_counter))) 
          {
            Vector<Integer> temp_values = this.temperatura.get(today.minusDays(dias_counter));
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
        if (temperatura.containsKey(LocalDate.now())){
            int size = this.temperatura.get(LocalDate.now()).size();
            i = this.temperatura.get(LocalDate.now()).elementAt(size-1);;
        }
        System.out.println("Temperatura Actual: " + i);
    }

       @Override
    public void add(LocalDate d, Vector<Integer> v) {
        temperatura.put(d, v);
    }

    
}
