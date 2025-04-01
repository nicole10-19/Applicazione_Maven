module it.unife.lp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base; 
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports it.unife.lp.model;
    exports it.unife.lp.view;

    
    opens it.unife.lp to javafx.fxml, com.fasterxml.jackson.databind;


    exports it.unife.lp;
}
