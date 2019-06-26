package Gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * Created by soure on 2018-11-12.
 */
public class Accounts extends Application {
    private static final int WIDTH = 0x3e8;//1000
    private static final int HEIGHT = 0x384;//900

    /**
     * (Stage) -> Null
     * start the app
     * @param STAGE the primary stage
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // instantiate a main file
        final Main MAIN = new Main();

        // create a GridPane
        final GridPane GRID = new GridPane();
        GRID.setHgap(20);
        GRID.setVgap(20);
        GRID.setPadding(new Insets(20,20,20,20));

        // Accounts title
        Label title = new Label("Accounts");
        title.setFont(Font.font(30));
        title.setAlignment(Pos.CENTER);
        title.minWidth(WIDTH);
        // create a back button to go back
        Button back = new Button("Back");

        // if back button is clicked then go back to main
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        // add title
        GRID.add(title, 0,0);
        //add back button
        GRID.add(back, 2, 1);

        //Scroll Pane to make app scrollable
        ScrollPane scroll = new ScrollPane();
        // add grid to the scroller
        scroll.setContent(GRID);

        //add the table headers
        Label heads = new Label("Name\t\t\tChecking\t\t\t\t\t\t\tSavings");
        GRID.add(heads, 0, 1);

        //Add Customer Button
        final Button ADDCUS = new Button("Add Customer");


        final int[] CCOUNT = {2};
        // add the add customer button to the grid
        GRID.add(ADDCUS, 0, CCOUNT[0]);

        final int[] ROW = {2};
        // if add customer is clicked on
        // replace the button with customer method
        // add add customer button the next row
        ADDCUS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ROW[0]++;
                GRID.getChildren().remove(ADDCUS);
                GRID.add(ADDCUS, 0, ROW[0]);
                GRID.add(customer(), 0, ROW[0]-1);
            }
        });
        // add the back button

        // create a 1000 x 900 scene with scroll as its root
        Scene scene = new Scene(scroll, WIDTH, HEIGHT);


        // Set The Stage Setting
        STAGE.setTitle(getClass().toString());
        STAGE.setScene(scene);
        STAGE.show();


    }

    /**
     * () -> GridPane
     *  add a table like database where the user
     *  can enter the customer and contains saving and checking account
     * @return a grid
     */
    private GridPane customer(){
        // make a table
        final GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20,20,20,20));

        // make and add name button
        // make name label
        // make name textfield
        final Button name = new Button("Add Name");
        final Label nameL = new Label();
        final TextField nameT = new TextField("Name Here");
        // if add name button is clicked
        name.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               switch (name.getText()){
                   // if Add Name is add buttons text
                   case "Add Name":
                       // Then set the text to Add
                       // remove name button
                       // replace it with its textfield
                       // add the name button
                       name.setText("Add");
                       grid.getChildren().remove(name);
                       grid.add(nameT, 0, 0);
                       grid.add(name, 0, 1);
                       break;
                   // if Add is add buttons text
                   case "Add":
                       // Then set the text to Edit Name
                       // remove name textfield
                       // replace it with its label
                       nameL.setText(nameT.getText());
                       name.setText("Edit Name");
                       grid.getChildren().remove(nameT);
                       grid.add(nameL, 0, 0);
                       break;
                   // else
                   default:
                       // remove the label
                       // change text to Add
                       // add the name textfield
                       grid.getChildren().remove(nameL);
                       name.setText("Add");
                       grid.add(nameT, 0, 0);
                       break;
               }
            }
        });

        // add the name button
        grid.add(name, 0, 0);

        // create a textfield with saving, deposit and transfer button
        final TextField checking = new TextField();
        final TextField saving = new TextField();
        Label totalChecking = new Label("Total Checking: $0.00");
        Label totalSaving = new Label("Total Saving: $0.00");

        // add them all
        grid.add(checking, 1, 0);
        grid.add(depWit(checking, saving,0, totalChecking, totalSaving), 1, 1);
        grid.add(saving, 2, 0);
        grid.add(depWit(checking, saving,1, totalChecking, totalSaving), 2, 1);
        // return the grid
        return grid;
    }

    /**
     * (TextField , TextField, int, Label, Label) -> GridPane
     * add the buttons and label and make them function properly
     * @param CHEKING Textfield where user withdraw, deposit or transfer to checking
     * @param SAVING Textfield where user withdraw, deposit or transfer to saving
     * @param ID Integer id where determines if textfield is checking or saving
     * @param TOTALCHECKING label that shows the amount of money in checking account
     * @param TOTALSAVING label that shows the amount of money in saving account
     * @return a grid of those buttons and labels
     */
    private GridPane depWit(final TextField CHEKING, final TextField SAVING, final int ID, final Label TOTALCHECKING, final Label TOTALSAVING){
        // make a table
        final GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        // make three buttons deposit, withdraw and transfer
        Button depositC = new Button("Deposit");
        Button withdrawC = new Button("Withdraw");
        Button transferC = new Button("Transfer");
        // this is where checking and saving is stored
        final double[] CHE = {0};
        final double[] SAV = {0};
        // Canada format currency
        final NumberFormat NM = NumberFormat.getCurrencyInstance(Locale.CANADA);

        //if withdraw is clicked on
        // if id is 0 take money from
        // total checking
        // else take money from total
        // saving
        withdrawC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (CHE[0]- Double.parseDouble(CHEKING.getText())>= 0 && ID ==0) {
                    double value = Double.parseDouble(CHEKING.getText());
                    CHE[0] -= value;
                    TOTALCHECKING.setText("Total Checking: " + NM.format(CHE[0]));
                }else if(SAV[0]- Double.parseDouble(SAVING.getText())>= 0 && ID ==1){
                    double value2 = Double.parseDouble(SAVING.getText());
                    SAV[0] -= value2;
                    TOTALSAVING.setText("Total Saving:" + NM.format(SAV[0]));
                }
            }
        });

        // if deposit is clicked on
        // if id is 0 add money from
        // total checking
        // else add money from total
        // saving
        depositC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ID == 0){
                        double value = Double.parseDouble(CHEKING.getText());
                        CHE[0] += value;
                        TOTALCHECKING.setText("Total Checking: " +NM.format(CHE[0]));
                    }else{
                        double value2 = Double.parseDouble(SAVING.getText());
                        SAV[0] += value2;
                        TOTALSAVING.setText("Total Saving:" + NM.format(SAV[0]));

                    }

            }
        });
        // if transfer is clicked on
        // if id is 0 take money from
        // total checking add to saving
        // else take money from total
        // saving add to checking
        transferC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ID == 0) {
                    double value = Double.parseDouble(CHEKING.getText());
                    if (CHE[0] -value >= 0) {
                        CHE[0] -= value;
                        SAV[0] += value;
                        TOTALCHECKING.setText("Total Checking: " +NM.format(CHE[0]));
                        TOTALSAVING.setText("Total Saving:" + NM.format(SAV[0]));
                    }
                }else{
                    double value = Double.parseDouble(CHEKING.getText());
                    System.out.println(SAV[0] - value);
                    if (SAV[0] -value >= 0) {
                        CHE[0] += value;
                        SAV[0] -= value;
                        TOTALCHECKING.setText("Total Checking: " +NM.format(CHE[0]));
                        TOTALSAVING.setText("Total Saving:" + NM.format(SAV[0]));
                    }
                }
            }
        });
        // add everything to the grid
        grid.add(depositC, 1, 0);
        grid.add(withdrawC, 2, 0);
        grid.add(transferC, 3, 0);
        grid.add(TOTALCHECKING, 4, 0);
        grid.add(TOTALSAVING, 8, 0);

        // return the grid
        return grid;
    }

}
