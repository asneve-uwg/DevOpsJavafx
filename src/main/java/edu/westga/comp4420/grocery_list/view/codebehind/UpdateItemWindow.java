package edu.westga.comp4420.grocery_list.view.codebehind;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import edu.westga.comp4420.grocery_list.model.GroceryItem;
import javafx.scene.control.Label;
import java.util.List;

public class UpdateItemWindow {   
    @FXML
    private Label currentInfo;
    @FXML
    private TextField newAmountNeeded;
    @FXML
    private TextField newAmountInCart;
    @FXML private AnchorPane guiPane;

    private GroceryItem selectedItem;
    private List<GroceryItem> groceryList;
	private MainWindow mainWindow;	

	private void closeWindow() {
		this.guiPane.getScene().getWindow().hide();
	}
	
    public void setSelectedItem(GroceryItem item) {
        if (item != null) {
            this.selectedItem = item;
            this.currentInfo.setText(item.getName());
            this.newAmountNeeded.setText(String.valueOf(item.getAmountNeeded()));
            this.newAmountInCart.setText(String.valueOf(item.getAmountInCart()));
        }
    }

    @FXML
    void updateItem(ActionEvent event) {
        try {
            // Create an updated item
            GroceryItem updatedItem = new GroceryItem(
                this.selectedItem.getName(),
                Integer.parseInt(this.newAmountNeeded.getText()),
                Integer.parseInt(this.newAmountInCart.getText())
            );

            // Remove the old item and add the updated one
            this.groceryList.remove(this.selectedItem);
            this.groceryList.add(updatedItem);
			
			this.closeWindow();
        } catch (NumberFormatException error) {
            Alert errorBox = new Alert(AlertType.ERROR);
            errorBox.setContentText("Must provide valid numeric values for amounts.");
            errorBox.showAndWait();
        } catch (IllegalArgumentException error) {
            Alert errorBox = new Alert(AlertType.ERROR);
            errorBox.setContentText(error.getMessage());
            errorBox.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        this.guiPane.getScene().getWindow().hide();
    }

    public void setGroceryList(List<GroceryItem> groceryList) {
        this.groceryList = groceryList;
    }

    @FXML
    void initialize() {
        assert this.currentInfo != null : "fx:id=\"currentInfo\" was not injected: check your FXML file 'UpdateItemWindow.fxml'.";
        assert this.guiPane != null : "fx:id=\"guiPane\" was not injected: check your FXML file 'UpdateItemWindow.fxml'.";
        assert this.newAmountInCart != null : "fx:id=\"newAmountInCart\" was not injected: check your FXML file 'UpdateItemWindow.fxml'.";
        assert this.newAmountNeeded != null : "fx:id=\"newAmountNeeded\" was not injected: check your FXML file 'UpdateItemWindow.fxml'.";
    }
}