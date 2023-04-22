package lk.ijse.hostelManagementSystem.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public static AnchorPane anchorPane;
    public static Stage stage;

    public enum Routes{
        Student,Rooms,Reservation
    }

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.anchorPane = pane;
        Navigation.anchorPane.getChildren().clear();
        stage = (Stage) Navigation.anchorPane.getScene().getWindow();
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");

        switch (route){
            case Student:
                initUI("lk/ijse/hostelManagementSystem/view/AddStudentForm.fxml");
                break;
            case Rooms:
                initUI("/lk/ijse/hostelManagementSystem/view/AddRoomForm.fxml");
                break;
            case Reservation:
                initUI("/lk/ijse/hostelManagementSystem/view/ReserveForm.fxml");
                break;
        }
    }

    public static void initUI(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class.getResource(location)));
    }
}
