/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Vector;

/**
 *
 * @author beatrizaarao
 */
public interface InterfaceServer extends Remote{
    
    
    String get_media(LocalDate data, Sensor s) throws RemoteException;
    String  get_max_minimo(LocalDate data, Sensor s) throws RemoteException;
    String get_ultimos_dias(Sensor s, int dias) throws RemoteException;
    String get_ValorAtual(Sensor s) throws RemoteException;
    void updateValues(Vector<Integer> values) throws RemoteException;
    
    DataC getData()throws RemoteException;
    View getView() throws RemoteException;
    
    int getHumidade_1() throws RemoteException;
    int getPressao_1() throws RemoteException;
    int getTemperatura_1() throws RemoteException;
    int getAcustica_1() throws RemoteException;
    int getLuz_1() throws RemoteException;
    void getHumidade() throws RemoteException;
    void getPressao() throws RemoteException;
    void getTemperatura() throws RemoteException;
    void getAcustica() throws RemoteException;
    void getLuz() throws RemoteException;
}
