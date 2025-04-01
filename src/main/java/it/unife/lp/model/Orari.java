package it.unife.lp.model;
import javafx.collections.FXCollections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;




public class Orari {
    
    public final List<String> giorni = FXCollections.observableArrayList(
        "Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi", "Sabato", "Domenica"
    );
    
    // Array di proprietà StringProperty per orario di inizio e fine
    public final String[] orarioInizio = new String[7];
    public final String[] orarioFine = new String[7];
    
    public Orari() {
        // Inizializza le proprietà di orarioInizio e orarioFine per ogni giorno
        for (int i = 0; i < 7; i++) {
            orarioInizio[i] = "";
            orarioFine[i] = "";
        }
    }
    
    // Getter per i giorni
    @JsonGetter("giorni")
    public List<String> getGiorni() {
        return giorni;
    }
    
    // Getter e setter per orario di inizio
    /*public StringProperty orarioInizioProperty(int index) {
        return orarioInizio[index];
    }*/


    @JsonGetter("orarioInizio")
    public String[] getOrarioInizio() {
        return orarioInizio.clone();
    }
    
    @JsonSetter("orarioInizio")
    public void setOrarioInizio(String[] orarioInizio) {
         System.arraycopy(orarioInizio, 0, this.orarioInizio, 0, orarioInizio.length);
    }
    
    // Getter e setter per orario di fine
    /*public StringProperty orarioFineProperty(int index) {
        return orarioFine[index];
    }*/

   @JsonGetter("orarioFine")
    public String[] getOrarioFine() {
        return orarioFine.clone();
    }
    
    @JsonSetter("orarioFine")
    public void setOrarioFine(String[] orarioFine) {
        System.arraycopy(orarioFine, 0, this.orarioFine, 0, orarioFine.length);
    }
}
    






