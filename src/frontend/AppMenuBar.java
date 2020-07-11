package frontend;

import javafx.scene.ImageCursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;

public class AppMenuBar extends MenuBar {

    public AppMenuBar() {
        Menu file = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(event -> CloseProcesses.checkClosing());
        file.getItems().add(exitMenuItem);
        this.setCursor(new ImageCursor(new Image("file:cursores/menuBarCursor.png")));
        Menu help = new Menu("Ayuda");
        MenuItem aboutMenuItem = new MenuItem("Acerca De");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Paint");
            alert.setContentText("Cátedra POO 2020");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);
        getMenus().addAll(file, help);
    }

}
