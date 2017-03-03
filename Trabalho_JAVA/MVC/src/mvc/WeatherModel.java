package mvc;



import java.time.LocalDate;
import java.util.*;

public class WeatherModel implements WeatherModelObserver{

    TemperatureValues temperatura;
    HumidadeValues humidade;
    PressaoValues pressao_atm;
    AudioValues audio;
    LuminosidadeValues luminosidade;

    public WeatherModel() {

        this.humidade = new HumidadeValues();
        this.temperatura = new TemperatureValues();
        this.audio = new AudioValues();
        this.luminosidade = new LuminosidadeValues();
        this.pressao_atm = new PressaoValues();
    }

    public HumidadeValues getHumidade(){
        return this.humidade;
    }
    
    public TemperatureValues getTemperatura(){
        return this.temperatura;
    }    
    
    public AudioValues getAudio(){
        return this.audio;
    }
    
    public LuminosidadeValues getLuminosidade(){
        return this.luminosidade;
    }

    public PressaoValues getPressao(){
            return this.pressao_atm;
    }

    @Override
    public void update(Vector<Integer> values){
        updateT(values.elementAt(0));
        updateH(values.elementAt(1));
        updateP(values.elementAt(2));
        updateA(values.elementAt(3));
        updateL(values.elementAt(4));
    }
    
    public void updateT(int temperature)
    {   
      Vector<Integer> val_temp = temperatura.getNodo(LocalDate.now());
      
      if (val_temp != null)  {val_temp.add(temperature);} 
        else
        {
          Vector<Integer> new_vector  =  new Vector<Integer>();
          new_vector.add(temperature);
          temperatura.add(LocalDate.now(), new_vector);
        }
    }
    
   public void updateH(int hum)
    {
      Vector<Integer> val_humid = humidade.getNodo(LocalDate.now());
      if (val_humid != null)  { val_humid.add(hum);} 
        else
        {
          Vector<Integer> new_vector  =  new Vector<Integer>();
          new_vector.add(hum);
          humidade.add(LocalDate.now(), new_vector);
        }

    }
   
   public void updateP(int pre)
    {
      Vector<Integer> val_pre = pressao_atm.getNodo(LocalDate.now());
      if (val_pre != null)  { val_pre.add(pre);} 
        else
        {
          Vector<Integer> new_vector  =  new Vector<Integer>();
          new_vector.add(pre);
          pressao_atm.add(LocalDate.now(), new_vector);
        }

    }
   
     public void updateA(int aud)
    {
      Vector<Integer> val_audio = audio.getNodo(LocalDate.now());
      if (val_audio != null)  { val_audio.add(aud);} 
        else
        {
          Vector<Integer> new_vector  =  new Vector<Integer>();
          new_vector.add(aud);
          audio.add(LocalDate.now(), new_vector);
        }

    }
   
   public void updateL(int luz)
    {
      Vector<Integer> val_luz = luminosidade.getNodo(LocalDate.now());
      if (val_luz != null)  { val_luz.add(luz);} 
        else
        {
          Vector<Integer> new_vector  =  new Vector<Integer>();
          new_vector.add(luz);
          luminosidade.add(LocalDate.now(), new_vector);
        }

    }
}
