module com.armando.prj_clinicahospitalarfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.armando.prj_clinicahospitalarfx to javafx.fxml;
    exports com.armando.prj_clinicahospitalarfx;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.json;
    requires java.xml.bind;
    requires java.activation;
    opens com.armando.prj_clinicahospitalarfx.model to java.xml.bind;
}
