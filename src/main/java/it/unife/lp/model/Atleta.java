package it.unife.lp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;


public class Atleta {

    public final StringProperty nome;
    public final StringProperty cognome;
    public final ObjectProperty<LocalDate> compleanno;
    public final StringProperty indirizzo;
    public final IntegerProperty telefono;
    public final StringProperty mail;
    public ObservableList<String> attivita;
    
        public Atleta() {
            this(null, null);
        }
    
        public Atleta(String nome, String cognome) {
            this.nome = new SimpleStringProperty(nome);
            this.cognome = new SimpleStringProperty(cognome);
    
            // Valori predefiniti
            this.compleanno = new SimpleObjectProperty<>(LocalDate.of(2000, 2, 19));
            this.indirizzo = new SimpleStringProperty("");
            this.telefono = new SimpleIntegerProperty ();
            this.mail = new SimpleStringProperty("");
            this.attivita = FXCollections.observableArrayList("");
        }
        
        @JsonGetter("nome")
        public String getNome() {
            return nome.get();
        }
        
        @JsonSetter("nome")
        public void setNome(String nome) {
            this.nome.set(nome);
        }
    
        public StringProperty nomeProperty() {
            return nome;
        }
    
        // Cognome
        @JsonGetter("cognome")
        public String getCognome() {
            return cognome.get();
        }
        @JsonSetter("cognome")
        public void setCognome(String cognome) {
            this.cognome.set(cognome);
        }
    
        public StringProperty cognomeProperty() {
            return cognome;
        }
    
        // Compleanno
        @JsonGetter("compleanno")
        public LocalDate getCompleanno() {
            return compleanno.get();
        }

        @JsonSetter("compleanno")
        public void setCompleanno(LocalDate compleanno) {
            this.compleanno.set(compleanno);
        }
    
        public ObjectProperty<LocalDate> compleannoProperty() {
            return compleanno;
        }
    
        // Indirizzo
        @JsonGetter("indirizzo")
        public String getIndirizzo() {
            return indirizzo.get();
        }
        @JsonSetter("indirizzo")
        public void setIndirizzo(String indirizzo) {
            this.indirizzo.set(indirizzo);
        }
    
        public StringProperty indirizzoProperty() {
            return indirizzo;
        }
    
        // Telefono
        @JsonGetter("telefono")
        public int getTelefono() {
            return telefono.get();
        }

        @JsonSetter("telefono")
        public void setTelefono(int telefono) {
            this.telefono.set(telefono);
        }
    
        public IntegerProperty telefonoProperty() {
            return telefono;
        }
    
        // Mail
        
        @JsonGetter("mail")
        public String getMail() {
            return mail.get();
        }

        @JsonSetter("mail")
        public void setMail(String mail) {
            this.mail.set(mail);
        }
    
        public StringProperty mailProperty() {
            return mail;
        }
    
        // Attivita
        
        @JsonGetter("attivita")
        public List<String> getAttivita() {
            return new ArrayList<>(attivita);
        }
    
        @JsonSetter("attivita")
        public void setAttivita(List<String> attivita){
            this.attivita.clear();
            this.attivita.addAll(attivita);
        }
        
        public void addAttivita(String nuovaAttivita) {
            attivita.add(nuovaAttivita);
        }

        public void removeAttivita(String attivitaDaRimuovere) {
            attivita.remove(attivitaDaRimuovere);
        }
}


