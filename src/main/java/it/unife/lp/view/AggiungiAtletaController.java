package it.unife.lp.view;


import it.unife.lp.Main;
import it.unife.lp.model.Atleta;
import it.unife.lp.util.DataUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AggiungiAtletaController {
    
    @FXML
    public TextField nomeField;

    @FXML
    public TextField cognomeField;

    @FXML
    public TextField dataDiNascitaField;

    @FXML
    public TextField indirizzoField;

    @FXML 
    public TextField mailField;

    @FXML
    public TextField telefonoField;

    @FXML
    public TextField attivitaField;

    public Atleta atleta;
    public Stage dialogStage;
    public boolean salvaClicked = false;
    public Main main;
    public ObservableList<String> attivitaList = FXCollections.observableArrayList();

    public void setmain(Main main){
        this.main = main;
    }

    @FXML
    public void initialize(){ }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAtleta(Atleta atleta){
        this.atleta = atleta;
        nomeField.setText(atleta.getNome());
        cognomeField.setText(atleta.getCognome());
        dataDiNascitaField.setText(DataUtil.format(atleta.getCompleanno()));
        dataDiNascitaField.setPromptText("gg.mm.aaaa");
        indirizzoField.setText(atleta.getIndirizzo());
        mailField.setText(atleta.getMail());
        telefonoField.setText(Integer.toString(atleta.getTelefono()));
        //attivitaList = atleta.getAttivita();
        aggiornaCampo(attivitaField);
        caricaAttivita(atleta);
        /*System.out.println("Attività caricate: " + atleta.getAttivita());*/

    }
    public void caricaAttivita(Atleta atleta){

        if(atleta != null){ 
          this.attivitaList =  FXCollections.observableArrayList(atleta.getAttivita());
        } else{
            this.attivitaList = FXCollections.observableArrayList();
        }

        aggiornaCampo(attivitaField);
    }

    public void aggiornaCampo(TextField attivitaField) {
        
        String[] nuoveAttivita = attivitaField.getText().split(" ");


        /*System.out.println(attivitaField.getText());*/
    
        
        for (String attivita : nuoveAttivita) {
            /*System.out.println(attivitaList);*/
            if(!attivita.isBlank() && !attivitaList.contains(attivita.trim())) {
                attivitaList.add(attivita.trim());
            }
        }
    
        String attivitaConcatenate = String.join(" ", attivitaList);
        attivitaField.setText(attivitaConcatenate);
    }

    public boolean validateAttivita(String[] attivitaArray) {
        for (String attivita : attivitaArray) {
            // Verifica se l'attività non è presente nella lista globale delle attività
            if (!main.getAttivitaData().stream().anyMatch(a -> a.getAttivita().equalsIgnoreCase(attivita.trim()))) {
                return false; // Se una delle attività non esiste, ritorna false
            }
        }
        return true; // Tutte le attività sono valide
    }

    public boolean salvaClick(){
        return salvaClicked;
    }

    @FXML
    public void gestioneSalva(){
        String[] attivitaArray = attivitaField.getText().split(" ");

        // Verifica se tutte le attività sono valide
        if (!validateAttivita(attivitaArray)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Errore nelle Attività");
            alert.setHeaderText(null);
            alert.setContentText("Una o più attività non esistono nella lista globale. Verifica e riprova.");
            alert.showAndWait();
            return; // Non salvare se le attività non sono valide
        }
        atleta.setNome(nomeField.getText());
        atleta.setCognome(cognomeField.getText());
        atleta.setCompleanno(DataUtil.parse(dataDiNascitaField.getText()));
        atleta.setIndirizzo(indirizzoField.getText());
        atleta.setMail(mailField.getText());
        atleta.setTelefono(Integer.parseInt(telefonoField.getText()));
        
        
        attivitaList.clear();
        attivitaList.addAll(attivitaArray);
        atleta.setAttivita(attivitaList);
        /*System.out.println("Attività salvate: " + attivitaList);*/

        salvaClicked = true;
        dialogStage.close();
    }
    

    @FXML
    public void gestioneAnnulla(){
         dialogStage.close();
    }



}