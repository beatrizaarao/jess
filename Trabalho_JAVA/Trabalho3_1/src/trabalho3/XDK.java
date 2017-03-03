/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

import java.time.LocalDate;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class XDK {

    private int humidade;//0-60%
    private int pressao;//altitude 0-200m -> pressao 760-742
    private int temperatura;//10-38
    private int acustica; //20-20000
    private int luz;//0-90%

    public XDK() {
        this.humidade = 0;
        this.pressao = 0;
        this.temperatura = 0;
        this.acustica = 0;
        this.luz = 0;
    }
    
    public int getHumidade_1() {
        return humidade;
    }

    public int getPressao_1() {
        return pressao;
    }

    public int getTemperatura_1() {
        return temperatura;
    }

    public int getAcustica_1() {
        return acustica;
    }
    
    public int getLuz_1() {
        return luz;
    }

    public void getHumidade() {
        int i = 0;
        int f = 60;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        humidade = valor;
    }

    public void getPressao() {
        int i = 742;
        int f = 760;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        pressao = valor;
    }

    public void getTemperatura() {
        int i = 10;
        int f = 28;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        temperatura = valor;
    }

    public void getAcustica() {
        int i = 20;
        int f = 20000;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        acustica = valor;
    }

    public void getLuz() {
        int i = 0;
        int f = 90;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        luz = valor;
    }
   
    
    public static void main(String args[]) throws InterruptedException {

        XDK xdk = new XDK();
        Vector<Integer> xdk1_reading_1= new Vector<>();
        WeatherStation iot = new WeatherStation();
        Views v = new Views();
        while(true)
        {
            xdk.getTemperatura();
            xdk.getHumidade();
            xdk.getPressao();
            xdk.getAcustica();
            xdk.getLuz();
            
            xdk1_reading_1.add(xdk.getTemperatura_1());
            xdk1_reading_1.add(xdk.getHumidade_1());
            xdk1_reading_1.add(xdk.getPressao_1());
            xdk1_reading_1.add(xdk.getAcustica_1());
            xdk1_reading_1.add(xdk.getLuz_1());
            
            iot.update(xdk1_reading_1);
            v.mostra_ValorAtual(iot.getTemperatura());
            v.mostra_ValorAtual(iot.getHumidade());
            
            TimeUnit.SECONDS.sleep(5);
            xdk1_reading_1.clear();
        }
    }
}

