package it.unife.lp.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;


public class Attivita {
    
    public final StringProperty nomeAttivita;
    public final StringProperty descrizioneAttivita;
    public Orari orariAttivita;
    
    public Attivita(){
        this(null);
    }
    public Attivita(String nomeAttivita){
        this.nomeAttivita= new SimpleStringProperty(nomeAttivita);
        this.descrizioneAttivita = new SimpleStringProperty("Nessuna descrizione");
        this.orariAttivita = new Orari();
    
    }

    @JsonGetter("nomeAttivita")
    public String getAttivita(){
        return nomeAttivita.get();
    }

    @JsonSetter("nomeAttivita")
    public void setAttivita(String nomeAttivita){
        this.nomeAttivita.set(nomeAttivita);
    }

    public  StringProperty nomeAttivitaProperty(){
        return nomeAttivita;
    }

    @JsonGetter("descrizioneAttivita")
    public String getDescrizioneAttivita(){
        return descrizioneAttivita.get();
    }

    @JsonSetter("descrizioneAttivita")
    public void setDescrizioneAttivita (String descrizioneAttivita){
        this.descrizioneAttivita.set(descrizioneAttivita);
    }

    public StringProperty descrizioneAttivitaProperty(){
        return descrizioneAttivita;
    }
    
    @JsonGetter("orariAttivita")
    public Orari getOrariAttivita(){
        return orariAttivita;
    }
    
    @JsonSetter("orariAttivita")
    public void setOrariAttivita(Orari nuoviOrari) {
        this.orariAttivita = nuoviOrari;
    }
    

}
