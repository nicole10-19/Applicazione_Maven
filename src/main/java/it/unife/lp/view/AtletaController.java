package it.unife.lp.view;
import it.unife.lp.Main;
import it.unife.lp.model.Atleta;
import it.unife.lp.util.DataUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class AtletaController {
    @FXML
    public TableView<Atleta> atletaTable;

    @FXML
    public TableColumn<Atleta, String> nomeColumn;

    @FXML
    public TableColumn<Atleta, String> cognomeColumn;

    @FXML
    public Label nomeLabel;

    @FXML
    public Label cognomeLabel;

    @FXML
    public Label dataDiNascitaLabel;

    @FXML
    public Label indirizzoLabel;

    @FXML
    public Label telefonoLabel;

    @FXML
    public Label mailLabel;

    @FXML
    public Label attivitaLabel;
    

    public Main main;

    public AtletaController(){
    }
    
    public void setMain (Main main){
        this.main = main;

        atletaTable.setItems(main.getAtletaData());
    }

    
    
    @FXML
    public void initialize() {
        // Initialize the person table with the two columns.
        nomeColumn.setCellValueFactory(
                cellData -> cellData.getValue().nomeProperty());
        cognomeColumn.setCellValueFactory(
                cellData -> cellData.getValue().cognomeProperty());
    
        // Clear person details.
        showPersonDetails(null);
    
        // Listen for selection changes and show the person details when changed.
        atletaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }


    public void showPersonDetails(Atleta atleta) {
        if (atleta != null) {
            // Fill the labels with info from the person object.
            nomeLabel.setText(atleta.getNome());
            cognomeLabel.setText(atleta.getCognome());
            dataDiNascitaLabel.setText(DataUtil.format(atleta.getCompleanno()));

            indirizzoLabel.setText(atleta.getIndirizzo());
            telefonoLabel.setText(Integer.toString(atleta.getTelefono()));
            mailLabel.setText(atleta.getMail());
            attivitaLabel.setText(atleta.getAttivita() != null ? String.join(" ", atleta.getAttivita()) : "");

            

        } else {
            // Person is null, remove all the text.
            nomeLabel.setText("");
            cognomeLabel.setText("");
            dataDiNascitaLabel.setText("");
            indirizzoLabel.setText("");
            telefonoLabel.setText("");
            mailLabel.setText("");
            attivitaLabel.setText("");
        }
    }

    @FXML
    public void EliminaAtleta() {
        int selectedIndex = atletaTable.getSelectionModel().getSelectedIndex();
        atletaTable.getItems().remove(selectedIndex);
        
    }

    
    @FXML
    public void AggiungiAtleta(){
       Atleta atletaAggiunto = new Atleta();
       boolean salvaClick = main.showAggAtletaDialog(atletaAggiunto);
       if(salvaClick){
        main.getAtletaData().add(atletaAggiunto);
       } 
    }

    @FXML
    public void modificaAtleta(){
        Atleta atletaSelezionato= atletaTable.getSelectionModel().getSelectedItem();
        if (atletaSelezionato != null){
            boolean salvaClick = main.showAggAtletaDialog(atletaSelezionato);
            if(salvaClick){
                showPersonDetails(atletaSelezionato);
            }
        }else{
            Alert alert = new Alert (AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Nessuna selezione");
            alert.setHeaderText("Nessun atleta selezionato");
            alert.setContentText("Si prega di selezionare un atleta in tabella");

            alert.showAndWait();
        }
    }


}
