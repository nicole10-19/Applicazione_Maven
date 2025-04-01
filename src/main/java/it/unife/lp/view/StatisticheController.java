package it.unife.lp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;

import it.unife.lp.Main;
import it.unife.lp.model.Atleta;
import it.unife.lp.model.StatisticheAttivita;

public class StatisticheController {

    @FXML
    public TableView<StatisticheAttivita> statisticheTable;
    @FXML
    public TableColumn<StatisticheAttivita, String> attivitaColumn;
    @FXML
    public TableColumn<StatisticheAttivita, Integer> countColumn;
    @FXML
    public PieChart pieChart;

    public Main main;

    public void setMain(Main main) {
        this.main = main;
        aggiornaStatistiche(); // Aggiorna le statistiche quando il controller viene configurato
    }

    public void aggiornaStatistiche() {
        ObservableList<StatisticheAttivita> statistiche = calcolaStatistiche(main.getAtletaData());

        // Configura la tabella
        attivitaColumn.setCellValueFactory(new PropertyValueFactory<>("attivita"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        statisticheTable.setItems(statistiche);

        // Configura il grafico a torta
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (StatisticheAttivita statistica : statistiche) {
            pieChartData.add(new PieChart.Data(statistica.getAttivita(), statistica.getCount()));
        }
        pieChart.setData(pieChartData);
    }

    public ObservableList<StatisticheAttivita> calcolaStatistiche(ObservableList<Atleta> atleti) {
        Map<String, Integer> conteggioAttivita = new HashMap<>();

        for (Atleta atleta : atleti) {
            for (String attivita : atleta.getAttivita()) {
                if(attivita!= null && !attivita.trim().isEmpty()){ 
                conteggioAttivita.put(attivita, conteggioAttivita.getOrDefault(attivita, 0) + 1);
                }
            }
        }

        ObservableList<StatisticheAttivita> statistiche = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : conteggioAttivita.entrySet()) {
            statistiche.add(new StatisticheAttivita(entry.getKey(), entry.getValue()));
        }

        return statistiche;
    }
}
