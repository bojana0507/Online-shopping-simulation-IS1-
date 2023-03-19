/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pomocna;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PlatiPomocna implements Serializable{

    public List<Integer> idArt;
    public List<Integer> kolicina;
    public List<BigDecimal> cena;
    public String adresa;
    public int grad;

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
