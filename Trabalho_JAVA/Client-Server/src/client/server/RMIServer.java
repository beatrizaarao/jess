/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author beatrizaarao
 */
public class RMIServer {
    public static void main(String[] args) {
       if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
        }
                    try {
                        String name = "Weather";
                        View v = new View();
                        DataC d = new DataC();
                        InterfaceServer ds= new ServerImpl(d,v);
                        InterfaceServer stub = (InterfaceServer) UnicastRemoteObject.exportObject(ds, 0);
                        Registry registry = LocateRegistry.getRegistry();
                        registry.rebind(name, stub);
                        System.out.println("Servidor preparado");
                    } catch (Exception e) {
                        System.err.println("exception:");
                        e.printStackTrace();
                    }
    } 
}

