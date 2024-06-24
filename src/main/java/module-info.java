module com.armando.prj_clinicahospitalarfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.armando.prj_clinicahospitalarfx to javafx.fxml;
    exports com.armando.prj_clinicahospitalarfx;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.json;
    requires java.xml;
    requires java.xml.bind;
    requires java.xml.crypto;
    requires io.github.xstream.mxparser;
    requires xstream;
    requires xmlpull;
    opens com.armando.prj_clinicahospitalarfx.model to xstream;

    
}
