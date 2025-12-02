package com.example.showersense;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sensor {
    private final StringProperty status;
    private final StringProperty locatie;

    public Sensor(String status, String locatie) {
        this.status = new SimpleStringProperty(status);
        this.locatie = new SimpleStringProperty(locatie);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getLocatie() {
        return locatie.get();
    }

    public StringProperty locatieProperty() {
        return locatie;
    }
}
