package edu.westga.comp4420.grocery_list.view.codebehind;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import edu.westga.comp4420.grocery_list.model.GroceryItem;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;


public class UpdateItemWindow {   
    @FXML
    private ListView currentInfo;
    @FXML
    private TextField newAmountNeeded;
    @FXML
    private TextField newAmountInCart;
    @FXML private AnchorPane guiPane;

    private GroceryItem selectedItem;
    private List<GroceryItem> groceryItems;
	private MainWindow mainWindow;	

	private void closeWindow() {
		this.guiPane.getScene().getWindow().hide();
	}


	

	@FXML
	void updateItem(ActionEvent event) {
		System.out.println(this.selectedItem);
		try {
			// Create an updated item
			GroceryItem updatedItem = new GroceryItem(this.selectedItem.getName(), Integer.parseInt(this.newAmountInCart.getText()), Integer.parseInt(this.newAmountNeeded.getText()));

			// Remove the old item and add the updated one
			this.groceryItems.remove(this.selectedItem);
			this.groceryItems.add(updatedItem);
			
			this.closeWindow();
  
		} catch (NumberFormatException error) {
			Alert errorBox = new Alert(AlertType.ERROR);
			errorBox.setContentText("Must provide value number for amount needed.");
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

	public void setItemList(List<GroceryItem> groceryItems) {
		this.groceryItems = groceryItems;
	}
	
	public void setSelectedItem(GroceryItem item) {
    // Ensure the item is not null
		if (item != null) {
			// Set the selected item
			this.selectedItem = item;
			
			// Set the name of the item in the label
			this.currentInfo.getItems().clear();
			this.currentInfo.getItems().add(item.toString());

		}
	}
    @FXML
    void initialize() {
        assert this.currentInfo != null : "fx:id=\"currentInfo\" was not injected: check your FXML file 'UpdateItemWindow.fxml'.";
        assert this.guiPane != null : "fx:id=\"guiPane\" was not injected: check your FXML file 'UpdateItemWindow.fxml'.";
        assert this.newAmountInCart != null : "fx:id=\"newAmountInCart\" was not injected: check your FXML file 'UpdateItemWindow.fxml'.";
        assert this.newAmountNeeded != null : "fx:id=\"newAmountNeeded\" was not injected: check your FXML file 'UpdateItemWindow.fxml'.";
    }
}