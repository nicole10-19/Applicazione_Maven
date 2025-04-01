package it.unife.lp.view;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import it.unife.lp.Main;


public class RootViewController {

    @FXML
    public BorderPane rootLayout;  // Riferimento al layout principale (BorderPane)

    public Main main;  // Riferimento alla classe Main

    // Metodo per gestire il clic sul menu "Attività"
    @FXML
    public void handleAttivitaMenuItem() {
        main.showAttivitaView();  // Mostra la vista Attività
    }


    @FXML
    public void handleAtletaMenuItem(){
        main.showAtletaView();
    }

    @FXML
    public void handleVisualizzaStatistiche(){
        main.showStatisticheView();
    }

    // Metodo per impostare il riferimento alla classe Main
    public void setMain(Main main) {
        this.main = main;
    }
}

