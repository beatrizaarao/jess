package client.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author beatrizaarao
 */
public class RMIClient {
    public static void main(String args[]) {
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
    try {
            String name = "Weather";
            Registry registry = LocateRegistry.getRegistry();
            InterfaceServer ds= (InterfaceServer ) registry.lookup(name);
            System.out.println("Cliente Preparado");
            
            Vector<Integer> xdk1_reading_1= new Vector<>();

            while(true)
            {
                ds.getTemperatura();
                ds.getHumidade();
                ds.getPressao();
                ds.getAcustica();
                ds.getLuz();

                xdk1_reading_1.add(ds.getTemperatura_1());
                xdk1_reading_1.add(ds.getHumidade_1());
                xdk1_reading_1.add(ds.getPressao_1());
                xdk1_reading_1.add(ds.getAcustica_1());
                xdk1_reading_1.add(ds.getLuz_1());

                ds.updateValues(xdk1_reading_1);
                System.out.println("--------------------------------");
                System.out.println(ds.get_ValorAtual(ds.getData().getTemperatura()));
                System.out.println(ds.get_max_minimo(LocalDate.now(),ds.getData().getTemperatura()));
                System.out.println(ds.get_max_minimo(LocalDate.now(), ds.getData().getHumidade()));
                System.out.println(ds.get_max_minimo(LocalDate.now(), ds.getData().getLuminosidade()));
                System.out.println(ds.get_max_minimo(LocalDate.now(), ds.getData().getPressao()));
                System.out.println(ds.get_max_minimo(LocalDate.now(), ds.getData().getAudio()));
                System.out.println(ds.get_ValorAtual(ds.getData().getPressao()));
                System.out.println(ds.get_ValorAtual(ds.getData().getHumidade()));
                System.out.println(ds.get_media(LocalDate.now(),ds.getData().getTemperatura()));
                System.out.println(ds.get_ultimos_dias(ds.getData().getTemperatura(),2));
                System.out.println("--------------------------------");

                TimeUnit.SECONDS.sleep(5);
                xdk1_reading_1.clear();
            }

        }  
    catch(Exception ex) { ex.printStackTrace();}
    }
}
