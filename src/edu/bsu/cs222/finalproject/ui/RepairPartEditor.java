package edu.bsu.cs222.finalproject.ui;

import edu.bsu.cs222.finalproject.Main;
import edu.bsu.cs222.finalproject.backend.Print;
import edu.bsu.cs222.finalproject.database.Employee;
import edu.bsu.cs222.finalproject.database.Repair;
import edu.bsu.cs222.finalproject.database.RepairPart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.function.Consumer;

public class RepairPartEditor {
    private Stage stage;
    private RepairPart repairPart;
    private Consumer<RepairPart> callback = null;

    static RepairPartEditor createInstance(Stage rootStage, RepairPart repairPart) throws Exception{
        Main main = Main.getInstance();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.getClass().getResource("/fxml/RepairPartEditor.fxml"));
        Parent loadedPane = loader.load();
        RepairPartEditor editor = loader.getController();
        editor.repairPart = new RepairPart(repairPart);

        {
            editor.quantityField.setText("" + repairPart.quantity);
            editor.nameField.setText(repairPart.name);
            editor.priceField.setText(NumberFormat.getCurrencyInstance().format(repairPart.price / 100.0));

            if(repairPart.needToBuy == false){
                editor.markAsBoughtButton.setDisable(true);
            }
        }
        {
            editor.stage = new Stage();
            editor.stage.initOwner(rootStage);
            editor.stage.initModality(Modality.APPLICATION_MODAL);
            editor.stage.setScene(new Scene(loadedPane));
        }

        return editor;
    }

    @FXML TextField quantityField = null;
    @FXML TextField nameField = null;
    @FXML TextField priceField = null;

    @FXML Button markAsBoughtButton = null;

    void setCallback(Consumer<RepairPart> callback){
        this.callback = callback;
    }

    void show(){
        stage.show();
    }

    @FXML
    void exit(){
        stage.close();
    }

    @FXML
    void updateRepairPart(){
        Main main = Main.getInstance();

        repairPart.quantity = Integer.parseInt(quantityField.getText());
        repairPart.name = nameField.getText();
        String priceText = priceField.getText();
        //TODO: split all of this dealing with price parsing stuff into a separate file, just like how PhoneNumbers work
        if(priceText.length() > 1 && priceText.charAt(0) == '$'){
            priceText = priceText.substring(1);
        }
        repairPart.price = (int)(Double.parseDouble(priceText) * 100);//explicitly cast this down to an int

        main.workingLayer.updateRepairPart(repairPart);

        if(callback != null) {
            callback.accept(repairPart);
        }

        stage.close();
    }

    @FXML
    void markAsBought(){
        repairPart.needToBuy = false;//this will be pushed to the database once the user hits "update"
        markAsBoughtButton.setDisable(true);
    }
}
