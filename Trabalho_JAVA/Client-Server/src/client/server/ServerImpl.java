package client.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author beatrizaarao
 */
public class ServerImpl implements InterfaceServer {
    
    private int humidade;//0-60%
    private int pressao;//altitude 0-200m -> pressao 760-742
    private int temperatura;//10-38
    private int acustica; //20-20000
    private int luz;//0-90%
    private DataC model;
    private View view;

    @Override
    public int getHumidade_1() throws RemoteException{
        return humidade;
    }

    @Override
    public int getPressao_1() throws RemoteException{
        return pressao;
    }

    @Override
    public int getTemperatura_1() throws RemoteException{
        return temperatura;
    }

    @Override
    public int getAcustica_1() throws RemoteException{
        return acustica;
    }
    
    @Override
    public int getLuz_1() throws RemoteException{
        return luz;
    }

    @Override
    public void getHumidade() throws RemoteException{
        int i = 0;
        int f = 60;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        humidade = valor;
    }

    @Override
    public void getPressao() throws RemoteException{
        int i = 742;
        int f = 760;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        pressao = valor;
    }

    @Override
    public void getTemperatura() throws RemoteException{
        int i = 10;
        int f = 28;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        temperatura = valor;
    }

    @Override
    public void getAcustica() throws RemoteException{
        int i = 20;
        int f = 20000;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        acustica = valor;
    }

    @Override
    public void getLuz() throws RemoteException{
        int i = 0;
        int f = 90;
        int dif = f-i;
        Random coisinha = new Random();
        int valor = coisinha.nextInt(dif);
        valor+=i;
        luz = valor;
    }

    
    public ServerImpl(DataC m, View v) throws RemoteException{
        model=m;
        view=v;
        this.humidade = 0;
        this.pressao = 0;
        this.temperatura = 0;
        this.acustica = 0;
        this.luz = 0;
    }
    
    /*-----------VIEW---------------*/
    @Override
    public String get_media(LocalDate data, Sensor s) throws RemoteException{
        return view.mostra_media(data,s);
    }
    
    @Override
    public String  get_max_minimo(LocalDate data, Sensor s)throws RemoteException{
        return view.mostra_max_minimo(data,s);
    }
    
    @Override
    public String get_ultimos_dias(Sensor s, int dias) throws RemoteException{
        return view.mostra_ultimos_dias(s,dias);
    }
    
    @Override
    public String get_ValorAtual(Sensor s)throws RemoteException{
        return view.mostra_ValorAtual(s);
    }
    /*-------------------------------*/
    
    /*-------------MODEL-------------*/
    @Override
    public void updateValues(Vector<Integer> values) throws RemoteException{
        model.update(values);
    }
    /*-------------------------------*/

    @Override
    public DataC getData() throws RemoteException{
        return this.model;
    }

    public View getView() throws RemoteException{
        return this.view;
    }

}
