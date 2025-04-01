package it.unife.lp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unife.lp.model.Atleta;
import it.unife.lp.model.Attivita;
import it.unife.lp.model.DataManager;
import it.unife.lp.model.Orari;
import it.unife.lp.view.AggiungiAtletaController;
import it.unife.lp.view.AggiungiAttivitaController;
import it.unife.lp.view.AtletaController;
import it.unife.lp.view.AttivitaController;
import it.unife.lp.view.RootViewController;
import it.unife.lp.view.StatisticheController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Atleta> atletaData = FXCollections.observableArrayList();
    private ObservableList<Attivita> attivitaData = FXCollections.observableArrayList();


    public Stage getPrimaryStage () {
        return primaryStage;
    }

    public void initRootLayout() {
        try {
            
            // Carica il layout principale dal file FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/it/unife/lp/view/RootView.fxml")); // Percorso corretto
            rootLayout = loader.load();
            
            // Imposta il controller per il layout root
            RootViewController controller = loader.getController();
            controller.setMain(this); // Passa il riferimento di Main al controller
            
            // Crea la scena con il layout principale
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento del layout principale: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Main() {
    }
    
    public ObservableList <Atleta> getAtletaData(){
        return atletaData;
    }

    public ObservableList<Attivita> getAttivitaData(){
        return attivitaData;
    }

    public void showMainView() {
        try {
            // Carica la vista principale
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/it/unife/lp/view/MainView.fxml")); // Percorso corretto
            AnchorPane mainView = loader.load();
            
            // Imposta la vista principale al centro del layout principale
            rootLayout.setCenter(mainView);

            AtletaController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento della vista principale: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public boolean showAttivitaDialog(Attivita attivita) {
        try {
            // Carica il file FXML per il dialogo
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/it/unife/lp/view/AttivitaView.fxml")); // Percorso corretto
            AnchorPane page = (AnchorPane) loader.load();
    
            // Crea una finestra di dialogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dettagli Attività");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
    
            // Imposta l'attività nel controller
            AttivitaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAttivita(attivita);
    
            // Mostra il dialogo e aspetta che l'utente lo chiuda
            dialogStage.showAndWait();
    
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void showAtletaView(){
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RootViewController.class.getResource("/it/unife/lp/view/MainView.fxml")); // Percorso corretto  
        AnchorPane atletaView = loader.load();


        rootLayout.setCenter(atletaView);
         
        AtletaController controller = loader.getController();
        controller.setMain(this);
    

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void showAttivitaView(){
        try{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RootViewController.class.getResource("/it/unife/lp/view/AttivitaView.fxml")); // Percorso corretto  
        AnchorPane atletaView = loader.load();


        rootLayout.setCenter(atletaView);
          
        AttivitaController controller = loader.getController();
        controller.setMain(this);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean showAggAtletaDialog(Atleta atleta) {
        try {
           
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AggiungiAtletaController.class.getResource("/it/unife/lp/view/AggiungiAtletaDialog.fxml"));
            AnchorPane page = loader.load();
    
           
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Aggiungi Atleta ");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
    
            
            AggiungiAtletaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAtleta(atleta);
            controller.setmain(this);
            
            dialogStage.showAndWait();
    
            return controller.salvaClick();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showAggAttivitaDialog(Attivita attivita){

        try { 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AggiungiAttivitaController.class.getResource("/it/unife/lp/view/AggiungiAttivitiaDialog.fxml"));
        AnchorPane page = loader.load();



        Stage dialogStage = new Stage();
        dialogStage.setTitle("Aggiungi Attivita ");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        AggiungiAttivitaController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAttivita(attivita);

        dialogStage.showAndWait();
        return controller.salvaClicked;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }


    }
    
    public void showStatisticheView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StatisticheController.class.getResource("/it/unife/lp/view/StatisticheView.fxml"));
            VBox page = loader.load();
    
            // Crea la finestra delle statistiche
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statistiche Attività");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
    
            // Configura il controller
            StatisticheController controller = loader.getController();
            controller.setMain(this);
    
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initDefaultData() {
        Atleta atleta1 = new Atleta("Mario", "Rossi");
        atleta1.setCompleanno(LocalDate.of(1990, 5, 10));
        atleta1.setIndirizzo("Via Roma, 1");
        atleta1.setTelefono(123456789);
        atleta1.setMail("mario.rossi@example.com");
        atleta1.addAttivita("Calcio");
    
        Atleta atleta2 = new Atleta("Chiara", "Bianchi");
        atleta2.setCompleanno(LocalDate.of(1985, 3, 15));
        atleta2.setIndirizzo("Via Milano, 20");
        atleta2.setTelefono(987654321);
        atleta2.setMail("chiara.bianchi@example.com");
        atleta2.addAttivita("Pallavolo");
    
        atletaData.add(atleta1);
        atletaData.add(atleta2);
    
        // Creazione Attività
        Attivita attivita1 = new Attivita("Calcio");
        attivita1.setDescrizioneAttivita("Si ricorda di portare la divisa");
    
        Orari orariCalcio = new Orari();
        String[] orariInizioCalcio = {"20:00", "", "", "", "15:00", "", ""};
        String[] orariFineCalcio = {"22:00", "", "", "", "17:00", "", ""};
        orariCalcio.setOrarioInizio(orariInizioCalcio);
        orariCalcio.setOrarioFine(orariFineCalcio);
        attivita1.setOrariAttivita(orariCalcio);
    
        Attivita attivita2 = new Attivita("Pallavolo");
        attivita2.setDescrizioneAttivita("Scadenza adesioni torneo:  19 Ottobre");
    
        Orari orariPallavolo = new Orari();
        String[] orariInizioPallavolo = {"", "", "19:00", "", "", "09:30", ""};
        String[] orariFinePallavolo = {"", "", "21:00", "", "", "12:00", ""};
        orariPallavolo.setOrarioInizio(orariInizioPallavolo);
        orariPallavolo.setOrarioFine(orariFinePallavolo);
        attivita2.setOrariAttivita(orariPallavolo);
    
        attivitaData.add(attivita1);
        attivitaData.add(attivita2);   
    }
    
    private boolean confermaChiusura() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Conferma Chiusura");
    alert.setHeaderText("Sei sicuro di voler chiudere?");
    alert.setContentText("Tutti i dati verranno salvati prima della chiusura.");

    return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

 

    public static void main(String[] args) {
        launch(args); // Avvia l'applicazione JavaFX
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestione Polisportiva");

        DataManager.DataContainer data = DataManager.loadData();
        if (data.getAtleti().isEmpty() && data.getAttivita().isEmpty()) {
            // Inizializza i dati solo se il file JSON è vuoto
            initDefaultData();
        } else {
            atletaData.addAll(data.getAtleti());
            attivitaData.addAll(data.getAttivita());
        }

        // Configura la chiusura dell'applicazione
        primaryStage.setOnCloseRequest(event -> {
        if (confermaChiusura()) {
            // Salva i dati prima di chiudere
            DataManager.saveData(new ArrayList<>(atletaData), new ArrayList<>(attivitaData));
        } else {
            event.consume(); // Annulla la chiusura
        }
        });    

        
        initRootLayout();
        showMainView();

    }
}


