package it.unife.lp.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StatisticheAttivita {
    public final SimpleStringProperty attivita;
    public final SimpleIntegerProperty count;

    public StatisticheAttivita(String attivita, int count) {
        this.attivita = new SimpleStringProperty(attivita);
        this.count = new SimpleIntegerProperty(count);
    }

    public String getAttivita() {
        return attivita.get();
    }

    public void setAttivita(String attivita) {
        this.attivita.set(attivita);
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.set(count);
    }
}

