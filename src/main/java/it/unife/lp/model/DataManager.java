
package it.unife.lp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static final String FILE_PATH = "data.json";
    public static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Configura l'ObjectMapper per Jackson
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // Salva i dati su file
    public static void saveData(List<Atleta> atleti, List<Attivita> attivita) {
        try {
            // Crea un contenitore per entrambi i tipi di dati
            DataContainer dataContainer = new DataContainer(atleti, attivita);
            objectMapper.writeValue(new File(FILE_PATH), dataContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carica i dati da file
    public static DataContainer loadData() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                // Legge e deserializza il file JSON
                return objectMapper.readValue(file, DataContainer.class);
            } else {
                // Ritorna un contenitore vuoto se il file non esiste o è vuoto
                System.out.println("File JSON non trovato o vuoto. Inizializzazione di dati vuoti.");
                return new DataContainer(new ArrayList<>(), new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore durante il caricamento dei dati: " + e.getMessage());
            // In caso di errore, ritorna un contenitore vuoto
            return new DataContainer(new ArrayList<>(), new ArrayList<>());
        }
    }
    

    // Contenitore per salvare atleti e attività insieme
    public static class DataContainer {
        private List<Atleta> atleti;
        private List<Attivita> attivita;

        // Costruttori, getter e setter
        public DataContainer() {
            this.atleti= new ArrayList<>();
            this.attivita= new ArrayList<>();
        }

        public DataContainer(List<Atleta> atleti, List<Attivita> attivita) {
            this.atleti = atleti;
            this.attivita = attivita;
        }

        public List<Atleta> getAtleti() {
            return atleti;
        }

        public void setAtleti(List<Atleta> atleti) {
            this.atleti = atleti;
        }

        public List<Attivita> getAttivita() {
            return attivita;
        }

        public void setAttivita(List<Attivita> attivita) {
            this.attivita = attivita;
        }

        @Override
        public String toString(){
            return "DataContainer{ " + "atleti=" + atleti + ", attivita=" + attivita + "}";
        }
    }
}
