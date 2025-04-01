package it.unife.lp.view;

import java.util.HashMap;
import java.util.Map;

import it.unife.lp.Main;
import it.unife.lp.model.Attivita;
import it.unife.lp.model.Orari;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AttivitaController {
   public Stage dialogStage;
   public Attivita attivita;
   public boolean okClicked = false;


    // Riferimenti agli elementi dell'interfaccia utente definiti nel file FXML
    @FXML
   public TableView<Attivita> attivitaTable;  // Tabella che visualizza le attività

    @FXML
   public TableColumn<Attivita, String> nomeColumn;  // Colonna nome attività

    @FXML
   public Label descrizioneLabel;  // Colonna per la descrizione dell'attività
    
    @FXML
   public Label orarioInizioLunedi, orarioInizioMartedi, orarioInizioMercoledi, orarioInizioGiovedi, orarioInizioVenerdi, orarioInizioSabato, orarioInizioDomenica;
    
    @FXML
   public Label orarioFineLunedi, orarioFineMartedi, orarioFineMercoledi, orarioFineGiovedi, orarioFineVenerdi, orarioFineSabato, orarioFineDomenica;
    

    @FXML
   public Button aggiungiButton;  // Pulsante per aggiungere una nuova attività

    @FXML
   public Button modificaButton;  // Pulsante per modificare l'attività selezionata

    @FXML
   public Button eliminaButton;  // Pulsante per eliminare l'attività selezionata

   public Main main;  // Riferimento alla classe Main per accedere ai dati

   public Map<String, Label> orariInizioMap = new HashMap<>();
   public Map<String, Label> orariFineMap = new HashMap<>();
    // Metodo per inizializzare il controller e configurare la vista
    public void initialize() {
        orariInizioMap.put("Lunedi", orarioInizioLunedi);
        orariInizioMap.put("Martedi", orarioInizioMartedi);
        orariInizioMap.put("Mercoledi", orarioInizioMercoledi);
        orariInizioMap.put("Giovedi", orarioInizioGiovedi);
        orariInizioMap.put("Venerdi", orarioInizioVenerdi);
        orariInizioMap.put("Sabato", orarioInizioSabato);
        orariInizioMap.put("Domenica", orarioInizioDomenica);



        orariFineMap.put("Lunedi" ,orarioFineLunedi);
        orariFineMap.put("Martedi", orarioFineMartedi);
        orariFineMap.put("Mercoledi", orarioFineMercoledi);
        orariFineMap.put("Giovedi", orarioFineGiovedi);
        orariFineMap.put("Venerdi", orarioFineVenerdi);
        orariFineMap.put("Sabato", orarioFineSabato);
        orariFineMap.put("Domenica", orarioFineDomenica);

        // Inizializzare le colonne della tabella
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeAttivitaProperty());

        // Impostare un comportamento per quando viene selezionata una riga nella tabella
        attivitaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAttivitaDetails(newValue));
    }

    // Metodo per configurare la vista con i dettagli dell'attività selezionata
   public void showAttivitaDetails(Attivita attivita) {
        if (attivita != null) {
            Orari orariList= attivita.getOrariAttivita(); 
            for (int i = 0; i < 7; i++) {
                /*orariList = orariList.get(i); // Ottieni l'elemento corrente*/                
                setLabelOrarioInizio(orariList.getGiorni().get(i), orariList.getOrarioInizio()[i]);
                setLabelOrarioFine(orariList.getGiorni().get(i), orariList.getOrarioFine()[i]);
            }
            descrizioneLabel.setText(attivita.getDescrizioneAttivita());
        } else{
                String[] giorniSettimana = {"Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi", "Sabato", "Domenica"}; 
                for (int i = 0; i < 7; i++) {
                    /*orariList = orariList.get(i); // Ottieni l'elemento corrente*/                
                    setLabelOrarioInizio(giorniSettimana[i], ""); 
                    setLabelOrarioFine(giorniSettimana[i], "");
                }
                
                descrizioneLabel.setText("");
            }
        }

        public void setLabelOrarioInizio(String giorno, String orario) {
            Label label = orariInizioMap.get(giorno);
            if (label != null) {
                label.setText(orario);
            }
        }

        public void setLabelOrarioFine(String giorno, String orario){
            Label label = orariFineMap.get(giorno);
    
            if (label != null){
                label.setText(orario);
            }
        }

        public void setMain(Main main) {
            this.main = main;
            attivitaTable.setItems(main.getAttivitaData());  // Impostare i dati delle attività nella tabella
        }
        public void setDialogStage(Stage dialogStage) {
            this.dialogStage = dialogStage;
        }
    
        public void setAttivita(Attivita attivita) {
            this.attivita = attivita;
        }
    
        public boolean isOkClicked() {
            return okClicked;
        }

        @FXML
       public void handleAggiungi(){
            Attivita nuovaAttivita = new Attivita( );
            boolean salvaClick = main.showAggAttivitaDialog(nuovaAttivita);
            main.getAttivitaData().add(nuovaAttivita);
        }
    
        // Metodo per modificare l'attività selezionata
        @FXML
       public void handleModifica() {
            Attivita attivitaSelezionata = attivitaTable.getSelectionModel().getSelectedItem();
            if (attivitaSelezionata != null) {
                boolean salvaClick = main.showAggAttivitaDialog(attivitaSelezionata);
                if(salvaClick){
                    showAttivitaDetails(attivitaSelezionata);
                }
            } else {
            Alert alert = new Alert (AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Nessuna selezione");
            alert.setHeaderText("Nessuna attivita selezionata");
            alert.setContentText("Si prega di selezionare un' attivita in tabella");

            alert.showAndWait();
            }

        }
    
        // Metodo per eliminare l'attività selezionata
        @FXML
       public void handleElimina() {
            int selectedIndex = attivitaTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                attivitaTable.getItems().remove(selectedIndex);  // Rimuove l'attività selezionata
            }
        }
}






