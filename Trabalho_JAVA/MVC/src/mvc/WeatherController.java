package mvc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import java.util.Vector;

/**
 *
 * @author beatrizaarao
 */
public class WeatherController {
    
    private WeatherModel model;
    private WeatherView view;
    
    
    public WeatherController(WeatherModel m, WeatherView v){
        model=m;
        view=v;
    }
    /*-----------VIEW---------------*/
    public void get_media(LocalDate data, Sensor s){
        view.mostra_media(data,s);
    }
    
    public void  get_max_minimo(LocalDate data, Sensor s){
        view.mostra_max_minimo(data,s);
    }
    
    public void get_ultimos_dias(Sensor s, int dias) {
        view.mostra_ultimos_dias(s,dias);
    }
    
    public void get_ValorAtual(Sensor s){
        view.mostra_ValorAtual(s);
    }
    /*-------------------------------*/
    
    /*-------------MODEL-------------*/
    public void updateValues(Vector<Integer> values){
        model.update(values);
    }
    /*-------------------------------*/

}
