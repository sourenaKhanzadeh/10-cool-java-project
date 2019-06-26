package Gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/**
 * Created by soure on 2018-10-31.
 */
public class Invoice extends Application {
    private static final int WIDTH = 0x4b0;//1200
    private static final int HEIGHT = 0x2ee;//750

    /**
     * run the class
     * @param STAGE current stage
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // instantiate the main class to go back to our main
        final Main MAIN = new Main();

        // This Class is scrollable where scroller is the root
        ScrollPane scroll = new ScrollPane();

        // make a grid
        final GridPane GRID = new GridPane();
        // set padding to 20,20,20,20 --> top,right,bottom,left respectively
        GRID.setPadding(new Insets(20,20,20,20));
        // set veritical and horizontal gap by 5
        GRID.setVgap(5);
        GRID.setHgap(5);
        // add grid to scroller to make the stage scrollable
        scroll.setContent(GRID);

        // add a 1200 x 750 scene with scroll as the root
        Scene scene = new Scene(scroll, WIDTH, HEIGHT);

        // head label as Invoice
        // position top right 3 x 18 cm
        // black borders grey background and white text
        // font set to 30
        Label invoice = new Label("INVOICE\t");
        invoice.setStyle("-fx-background-color: grey;-fx-text-fill: white;-fx-border-color: black;" +
                "-fx-min-height: 3cm; -fx-min-width: 18cm;-fx-alignment: center-right;");
        invoice.setFont(Font.font(30));

        // add the invoice header
        GRID.add(invoice, 0, 0, 5, 1);
        // change grid background color to a mix of blue and green
        GRID.setStyle("-fx-background-color: #006f6f");

        // make an add company button
        final Button ADDCOMPANY = new Button("Add Company");
        // if button is clicked on
        ADDCOMPANY.setOnAction(new EventHandler<ActionEvent>() {
            // company name textfield
            TextField askCompany = new TextField();
            /**
             * if addCompany button is clicked
             * then create a button and a textfield
             * and if the button is pressed then take
             * textfield text and set the company title
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                // create an add button
                final Button add = new Button("Add");
                // remove addCompany from the grid
                GRID.getChildren().remove(ADDCOMPANY);
                // place the askCompany textfield
                GRID.add(askCompany, 0, 1, 5, 1);
                // place an add button
                GRID.add(add, 0, 2, 5, 1);
                // if add button is clicked
                add.setOnAction(new EventHandler<ActionEvent>() {
                    /**
                     * if add button is clicked then
                     * take the askCompany textfield
                     * text and replace it as a label
                     * @param event
                     */
                    @Override
                    public void handle(ActionEvent event) {
                        // Create a label with askCompany text as its name
                        // set font to 50
                        // place bottom left
                        // width = 18cm with white text color
                        Label companyL = new Label(askCompany.getText() + "\n--------------------");
                        companyL.setFont(Font.font(50));
                        companyL.setStyle("-fx-alignment: bottom-left; -fx-text-fill: #ffffff;" +
                                "-fx-background-color: #006f6f;-fx-min-width: 18cm");
                        // remove the textfield from the grid
                        // remove the add button
                        GRID.getChildren().remove(askCompany);
                        GRID.getChildren().remove(add);
                        // add the label which is the companies name
                        GRID.add(companyL, 0,1,7,1);

                    }
                });

            }
        });
        // add the addCompany to the grid
        GRID.add(ADDCOMPANY, 0, 1);

        // create date label
        // set text color to white
        Label date = new Label("Date: ");
        date.setStyle("-fx-text-fill: white;");

        // add date label
        GRID.add(date, 0, 2);

        // add date textField
        final TextField DATEF = new TextField();

        // change date textField's text property
        DATEF.textProperty().addListener(new ChangeListener<String>() {
            /**
             * once user typed the data as this format
             * DD/MM/YYYY then replace the text with label
             * @param observable the current entered string
             * @param oldValue all the values excluding the new value entered
             * @param newValue all the values including the current value
             */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // make a pattern with this format DD/MM/YYYY
                Pattern pattern = Pattern.compile("^\\d\\d/\\d\\d/\\d\\d\\d$");
                // make new date label
                Label Date = new Label();
                // if old value matches the pattern
                if (pattern.matcher(oldValue).matches()) {
                    // then set the date label
                    // change text color to white
                    Date.setText(newValue);
                    Date.setStyle("-fx-text-fill: white;");
                    //remove date field
                    GRID.getChildren().remove(DATEF);
                    // add date label
                    GRID.add(Date, 1, 2);
                }
            }
        });// add the date text field to the grid
        GRID.add(DATEF, 1, 2);
        // create a back button
        Button back = new Button("Back");
        // if back button is clicked
        back.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * if back button is clicked
             * then go to the main class
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // add label and a text field invoice then customer #
        lableAndT(GRID, "Invoice#: ", 0,3,1);
        lableAndT(GRID, "Customer#: ", 0,4,1);

        // add two sub heading of our invoice with store info
        // name Bill to and Ship to
        GRID.add(setHead("Bill To: "), 0, 5);
        GRID.add(setHead("Ship To: "), 2, 5);

        // add a subHead to our invoice
        GRID.add(getSubHead(), 0, 7, 4, 1);
        // add an intractive table that adds item's and price it
        GRID.add(getTable(), 0, 8, 4, 1);
        // create a thank you good bye label to show the user this is the end
        // set font to 20
        // set text color to white
        Label thanks = new Label("-----------------------------------------\n" +
                "Thank You For Making Business With Us");
        thanks.setFont(Font.font(20));
        thanks.setStyle("-fx-text-fill: #ffffff;");
        // add the thanks label
        GRID.add(thanks, 0 ,9, 1,10);
        // add the back button
        GRID.add(back, 1 ,9);

        // set stage title to Invoice
        // set stage scene to our scene
        // show stage
        STAGE.setTitle("Invoice");
        STAGE.setScene(scene);
        STAGE.show();
    }

    /**
     * set headers that contain different label and
     * textfield
     * @param header the name of the box header
     * @return a GridPane containing a table like header
     */
    private GridPane setHead(String header){
        // create a grid
        final GridPane GRID = new GridPane();
        // create a label name the header
        // set font to 30
        // set text to white
        Label head = new Label(header);
        head.setFont(Font.font(30));
        head.setStyle("-fx-text-fill: white;");

        // add the head label
        GRID.add(head, 0, 0);

        // create the label and text field with convenient names
        lableAndT(GRID, "First Name: ", 0,1, 0);
        lableAndT(GRID, "Last Name: ", 0,2, 0);
        lableAndT(GRID, "Street Name: ",0, 3, 0);
        lableAndT(GRID, "Zip Code: ",0, 4, 3);
        lableAndT(GRID, "Street#: ", 0,5, 1);
        lableAndT(GRID, "Phone#: ", 0,6, 2);

        // return the grid
        return GRID;
    }

    /**
     * make a sub  head in the format of the following
     * Sales Person     Shipping Method     Shipping Terms      Payment Id      Due Date    Delivery Date
     * TextField        TextField           TextField           TextField       TextField   TextField
     *                                                                                      Save Button
     * @return a GridPane In the above format
     */
    private GridPane getSubHead(){
        // create a grid with a horizontal gap of 10
        final GridPane GRID = new GridPane();
        GRID.setHgap(10);

        // create the desired labels array of size 6
        Label[] heads = {
                new Label("Sales Person"),
                new Label("Shipping Method"),
                new Label("Shipping Terms"),
                new Label("Payment Id"),
                new Label("Due Date"),
                new Label("Delivery Date")
        };

        // create another label array of size 6
        final Label[] ANS = new Label[heads.length];
        // create TextField array of size 6
        final TextField[] HEADF = new TextField[heads.length];

        // make the label heads
        // make text color white
        // add to the grid
        // make the corresponding textfield
        // add textfield to the grid
        for (int i = 0; i< heads.length;i++) {
            ANS[i] = new Label();
            heads[i].setStyle("-fx-text-fill: white");
            GRID.add(heads[i], i, 0);
            HEADF[i] = new TextField();
            GRID.add(HEADF[i], i, 1);
        }

        // make a save button
        final Button SAVE = new Button("Save");

        // if save is clicked
        SAVE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // for all the heads take the
                // textfield text and set is to its label
                // make the text color white
                // make text background light green
                // remove the textfield headers
                // add the labels
                for (int i = 0; i <HEADF.length ; i++) {
                    ANS[i].setText(HEADF[i].getText());
                    ANS[i].setStyle("-fx-text-fill: white;" +
                            "-fx-background-color: #00afa0");
                    GRID.getChildren().remove(HEADF[i]);
                    GRID.add(ANS[i], i, 2);
                }
                // remove the save button
                GRID.getChildren().remove(SAVE);

            }
        });
        // add the save button
        GRID.add(SAVE, heads.length-1, 2);

        // return the grid
        return GRID;
    }

    /**
     * Make an interactive table questionnaire
     * that allows you to input as many item as possible
     * make the items and give a discount price total
     * price is going to be calculated
     * @return a GridPane that makes a database of items
     * with prices and total price
     */
    private GridPane getTable(){
        // make a grid with horizontal gap of 20
        final GridPane GRID = new GridPane();
        GRID.setHgap(40);

        // create the desired heads of the table
        Label[] head = {
            new Label("Item#"),
            new Label("Detail"),
            new Label("Quantity"),
            new Label("Unit Price($)"),
            new Label("Total Price($)"),
        };

        // add the heads to the grid
        // set text color to white
        for (int i = 0; i <head.length ; i++) {
            head[i].setStyle("-fx-text-fill: white;");
            GRID.add(head[i], i, 0);
        }

        // make an add button
        // min width = 100
        final Button ADD = new Button("Add Item");
        ADD.setMinWidth(100);

        // make  TextArea, TextField, TextField -- > details, quantity and unit price respectively
        final TextArea DETAILS = new TextArea();
        final TextField  QUANTITY = new TextField();
        final TextField  UNITEPRICE = new TextField();

        // make an array of calculated totals
        final ArrayList<Double> TOTAL = new ArrayList<>();

        //count the rows
        final int[] ROW = {2};
        // make a save button
        final Button SAVE = new Button("Save");
        // if add is clicked
        ADD.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * add the next row of text fields and text areas
             * and then add to the table
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                switch (ADD.getText()){
                    // if add text is set to Add Item
                    case "Add Item":
                        // then save button is disabled
                        SAVE.setDisable(true);
                        // remove add button and save button
                        GRID.getChildren().remove(ADD);
                        GRID.getChildren().remove(SAVE);
                        // add the item number id to the grid
                        GRID.add(new Label("00000" + (ROW[0]-1)), 0, ROW[0]-1);
                        // set details text area to 200 x 100
                        DETAILS.setMaxHeight(200);
                        DETAILS.setMaxWidth(100);

                        // set add text to Add
                        ADD.setText("Add");

                        // add details text area to the grid
                        // add quantity text field to the grid
                        // add unit price text field to the grid
                        // add add button to the grid
                        // add save button to the grid
                        GRID.add(DETAILS, 1, ROW[0]-1);
                        GRID.add(QUANTITY, 2, ROW[0]-1);
                        GRID.add(UNITEPRICE, 3, ROW[0]-1);
                        GRID.add(ADD, 0, ROW[0]+1);
                        GRID.add(SAVE, 1, ROW[0]+1);
                        // go to next row
                        ROW[0]++;
                        break;
                    default:
                        //else save is enabled
                        SAVE.setDisable(false);
                        // remove details text area from grid
                        // remove quantity text field from grid
                        // remove unit price text field form grid
                        GRID.getChildren().remove(DETAILS);
                        GRID.getChildren().remove(QUANTITY);
                        GRID.getChildren().remove(UNITEPRICE);

                        // add details label
                        // add quantity label
                        // add unit price label
                        // add the total price of the row
                        GRID.add(new Label(DETAILS.getText()), 1, ROW[0]-2);
                        GRID.add(new Label(QUANTITY.getText()), 2, ROW[0]-2);
                        GRID.add(new Label(UNITEPRICE.getText()), 3, ROW[0]-2);
                        GRID.add(new Label(String.format("$%.2f", Double.parseDouble(UNITEPRICE.getText()) *
                                Integer.parseInt(QUANTITY.getText()))), 4, ROW[0]-2);
                        // add the total of each row to the list
                        TOTAL.add(Double.parseDouble(UNITEPRICE.getText()) * Integer.parseInt(QUANTITY.getText()));

                        // change the add button's text to Add Item
                        ADD.setText("Add Item");
                        // clear all text Fields
                        DETAILS.setText("");
                        QUANTITY.setText("");
                        UNITEPRICE.setText("");
                        break;
                }
            }
        });

        // this is our full total
        final double[] RES2 = {0};
        // make a text field for our discount
        final TextField DISCOUNT = new TextField();
        // change discount text property
        DISCOUNT.textProperty().addListener(new ChangeListener<String>() {
            // create a pattern like this d.dd where d is a number
            Pattern pattern = Pattern.compile("^\\d.\\d\\d$");

            /**
             * if the text field matches the pattern then
             * set the discount to that rate
             * @param observable current entered value
             * @param oldValue old values in the text field
             * @param newValue new values entered in the text field
             */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // if text field math the pattern
                if (pattern.matcher(newValue).matches()) {
                    // then remove discount text field
                    GRID.getChildren().remove(DISCOUNT);
                    // add discount rate
                    // add Calculated Grand Total
                    GRID.add(new Label("\t\t\t" + DISCOUNT.getText()), 4 ,ROW[0]+1);
                    GRID.add(new Label(String.format("Grand Total: $%.2f" ,((RES2[0] - (Double.parseDouble(DISCOUNT.getText())*RES2[0]))*1.13))), 4 ,ROW[0]+3);

                }
            }
        });
        // if save button is clicked
        SAVE.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * change the text field to the labels
             * set their prices and calculate the
             * total price and lead to the grand total
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                // remove add button
                // remove save button
                GRID.getChildren().remove(ADD);
                GRID.getChildren().remove(SAVE);

                // take the sum of each row's total
                for (int i = 0; i <TOTAL.size() ; i++) {
                    RES2[0] += TOTAL.get(i);
                }

                // add the subtotal
                // add the discount rate
                // add the discount
                // add the tax rate
                GRID.add(new Label(String.format("-----------------\nSubTotal: $%.2f" , RES2[0])), 4, ROW[0]);
                GRID.add(new Label("Discount Rate: "), 4, ROW[0]+1);
                GRID.add(DISCOUNT, 5, ROW[0]+1);
                GRID.add(new Label("Tax Rate: 0.13"), 4, ROW[0]+2);
            }
        });
        // add the add button
        // add the save button
        GRID.add(ADD,0, 1);
        GRID.add(SAVE,1, 1);

        // return the grid
        return GRID;
    }

    /**
     * Make a label and a text field
     * @param GRID the root GridPane
     * @param button the desired button
     * @param COL column number
     * @param ROW row number
     * @param type what type of text field is it
     */
    void lableAndT(final GridPane GRID, String button,final int COL,final int ROW, int type){
        // create the label which is the button name
        // set text color to white
        final Label lname = new Label(button);
        lname.setStyle("-fx-text-fill: white;");
        // add the label
        GRID.add(lname, COL, ROW);

        // add the textfield
        // set max width to 100
        final TextField L_NAME_F = new TextField();
        L_NAME_F.setMaxWidth(100);

        //text field type 0
        if (type == 0) {
            // change the text property
            L_NAME_F.textProperty().addListener(new ChangeListener<String>() {
                /**
                 * This is the text only text field
                 * if current value is not text then
                 * change into the label
                 * @param observable current entered value
                 * @param oldValue old value entered in the text field
                 * @param newValue new value entered in the text field
                 */
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    // create a label
                    Label label = new Label();
                    // if new value is not text
                    if (!newValue.matches("^[A-Z][a-z]*||^[a-z]*")) {
                        // then remove text field
                        // set label to old value
                        // change text color to white
                        // add label
                        GRID.getChildren().remove(L_NAME_F);
                        label.setText(oldValue);
                        label.setStyle("-fx-text-fill: #ffffff");
                        GRID.add(label, 1,ROW);
                    }
                }
            });
        }
        //text field type 1
        else if (type == 1) {
            L_NAME_F.textProperty().addListener(new ChangeListener<String>() {
                /**
                 * This is the numbers only text field
                 * if current value is not a number then
                 * change into the label
                 * @param observable current entered value
                 * @param oldValue old value entered in the text field
                 * @param newValue new value entered in the text field
                 */
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    Label label = new Label();
                    // if old value matches the pattern
                    if (!newValue.matches("^\\d*")) {
                        // then remove text field
                        // set label to old value
                        // change text color to white
                        // add label
                        GRID.getChildren().remove(L_NAME_F);
                        label.setText(oldValue);
                        label.setStyle("-fx-text-fill: #ffffff");
                        GRID.add(label, 1,ROW);
                    }
                }
            });
        }
        //text field type 2
        else if (type == 2) {
            L_NAME_F.textProperty().addListener(new ChangeListener<String>() {
                /**
                 * This is a phone number formatted text field
                 * if old value matches the format then
                 * change into the label
                 * @param observable current entered value
                 * @param oldValue old value entered in the text field
                 * @param newValue new value entered in the text field
                 */
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    Label label = new Label();
                    Pattern pattern = Pattern.compile("^\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d$");
                    // if old value matches the pattern
                    if (pattern.matcher(oldValue).matches()) {
                        // then remove text field
                        // set label to old value
                        // change text color to white
                        // add label
                        GRID.getChildren().remove(L_NAME_F);
                        label.setText(newValue);
                        label.setStyle("-fx-text-fill: #ffffff");
                        GRID.add(label, 1,ROW);
                    }
                }
            });
        }
        //text field type 3
        else if (type == 3) {
            L_NAME_F.textProperty().addListener(new ChangeListener<String>() {
                /**
                 * This is a postal code format text field
                 * if old value is formatted then
                 * change into the label
                 * @param observable current entered value
                 * @param oldValue old value entered in the text field
                 * @param newValue new value entered in the text field
                 */
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    Label label = new Label();
                    Pattern pattern = Pattern.compile("^[A-Z]\\d[A-Z]\\d[A-Z\\d]$");
                    // if old value matches the pattern
                    if (pattern.matcher(oldValue).matches()) {
                        // then remove text field
                        // set label to old value
                        // change text color to white
                        // add label
                        GRID.getChildren().remove(L_NAME_F);
                        label.setText(newValue);
                        label.setStyle("-fx-text-fill: #ffffff");
                        GRID.add(label, 1,ROW);
                    }
                }
            });
        }
        // add the text field to the grid
        GRID.add(L_NAME_F, 1, ROW);

    }
}
