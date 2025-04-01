package it.unife.lp.view;

import it.unife.lp.model.Attivita;
import it.unife.lp.model.Orari;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AggiungiAttivitaController {

    @FXML
    public TextField nomeAttivitaField;

    @FXML
    public TextField descrizioneAttivitaField;

    @FXML
    public TextField[] daFields = new TextField[7];

    @FXML
    public TextField[] aFields = new TextField[7];

    @FXML
    public TextField lunediDaField;

    @FXML
    public TextField martediDaField;

    @FXML
    public TextField mercolediDaField;

    @FXML
    public TextField giovediDaField;

    @FXML
    public TextField venerdiDaField;

    @FXML
    public TextField sabatoDaField;

    @FXML
    public TextField domenicaDaField;

    @FXML
    public TextField lunediAField;

    @FXML
    public TextField martediAField;

    @FXML
    public TextField mercolediAField;  

    @FXML
    public TextField giovediAField;

    @FXML
    public TextField venerdiAField;

    @FXML
    public TextField sabatoAField;

    @FXML
    public TextField domenicaAField;


    public Attivita attivita;
    public Stage dialogStage;
    public boolean salvaClicked = false;

    public void setDialogStage (Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void initialize() {
        // Inizializza un'istanza di Attivita
        attivita = new Attivita();

        daFields[0] = lunediDaField;
        daFields[1] = martediDaField;
        daFields[2] = mercolediDaField;
        daFields[3] = giovediDaField;
        daFields[4] = venerdiDaField;
        daFields[5] = sabatoDaField;
        daFields[6] = domenicaDaField;

        aFields[0] = lunediAField;
        aFields[1] = martediAField;
        aFields[2] = mercolediAField;
        aFields[3] = giovediAField;
        aFields[4] = venerdiAField;
        aFields[5] = sabatoAField;
        aFields[6] = domenicaAField;

    }

    public void setAttivita(Attivita attivita){
        this.attivita = attivita;


        if (attivita != null){
            nomeAttivitaField.setText(attivita.getAttivita());
            descrizioneAttivitaField.setText(attivita.getDescrizioneAttivita());



            Orari orari = attivita.getOrariAttivita();

            for(int i = 0; i<7; i++){
                daFields[i].setText(orari.getOrarioInizio()[i]);
                aFields[i].setText(orari.getOrarioFine()[i]);
            }
        }
    }


    @FXML
    public void salvaDati() {
        // Recupera i dati dai campi di testo e aggiorna l'oggetto Attivita
        attivita.setAttivita(nomeAttivitaField.getText());
        attivita.setDescrizioneAttivita(descrizioneAttivitaField.getText());

        // Crea un nuovo oggetto Orari con i dati inseriti
        String[] orariInizio = new String[7];
        String[] orariFine = new String[7];
        for (int i = 0; i < 7; i++) {
            orariInizio[i]= daFields[i].getText();
            orariFine[i]= aFields[i].getText();
        }

        // Imposta gli orari nell'oggetto Attivita
        Orari orari = new Orari();
        orari.setOrarioInizio(orariInizio);
        orari.setOrarioFine(orariFine);

        attivita.setOrariAttivita(orari);

        // Mostra un messaggio di conferma
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Salvataggio riuscito");
        alert.setHeaderText(null);
        alert.setContentText("Attivita salvata con successo!");
        alert.showAndWait();

        salvaClicked = true;
        dialogStage.close();
    }

    @FXML
    public void annullaDati() {
        dialogStage.close();
    }
}

