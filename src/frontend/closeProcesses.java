package frontend;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class closeProcesses {

    public static void checkClosing(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("Salir de la aplicación");
        alert.setContentText("¿Está seguro que desea salir de la aplicación?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                System.exit(0);
            }
        }
    }
}
