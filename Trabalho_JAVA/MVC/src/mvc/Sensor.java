/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.time.LocalDate;
import java.util.Vector;

/**
 *
 * @author beatrizaarao
 */
public interface Sensor {
    public Vector<Integer> getNodo(LocalDate d);
    public void add(LocalDate d, Vector<Integer> v);
    public void media(LocalDate data);
    public void max_minimo(LocalDate data);
    public void ultimos_dias(int dias);
    public void valorAtual();
}
