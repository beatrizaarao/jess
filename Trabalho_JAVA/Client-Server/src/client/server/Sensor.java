/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;

import java.time.LocalDate;
import java.util.Vector;

/**
 *
 * @author beatrizaarao
 */
public interface Sensor {
    public Vector<Integer> getNodo(LocalDate d);
    public void add(LocalDate d, Vector<Integer> v);
    public String media(LocalDate data);
    public String max_minimo(LocalDate data);
    public String ultimos_dias(int dias);
    public String valorAtual();
}
