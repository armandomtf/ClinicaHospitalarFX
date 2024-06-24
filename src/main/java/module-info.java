module com.armando.prj_clinicahospitalarfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.armando.prj_clinicahospitalarfx to javafx.fxml;
    exports com.armando.prj_clinicahospitalarfx;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.json;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
      
}
