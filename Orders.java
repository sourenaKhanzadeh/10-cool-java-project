package Gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * Created by soure on 2018-10-31.
 */
public class Orders extends Application{
    private static final int WIDTH = 0x258;//600
    private static final int HEIGHT = 0x1f4;//500

    /**
     * start app
     * @param STAGE the primary stage
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // instantiate the main class
        final Main MAIN = new Main();

        // make the grid
        final GridPane GRID = new GridPane();
        GRID.setVgap(20);
        GRID.setHgap(10);
        GRID.setPadding(new Insets(20,20,20,20));

        // scroller as root
        // make grid scrollable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(GRID);

        // create a 600 x 500 scene with scrollPane as the root
        Scene scene = new Scene(scrollPane, WIDTH, HEIGHT);

        // add the headers
        Label[] headers = {
                new Label("Item Code "),
                new Label("Item Description "),
                new Label(" Qty Ordered")

        };

        // add the headers with their css style
        final int[] CCOUNT = {0};
        for (Label i:
             headers) {
            i.setStyle("-fx-text-fill: #cccccc;-fx-background-color: #555555");
            GRID.add(i, CCOUNT[0], 0);
            CCOUNT[0]++;
        }

        // create an add order button
        final Button ADD = new Button("Add Order");
        CCOUNT[0] = 2;

        // replace the order button ask user
        // then add the order button
        final int[] RCOUNT = {1};
        ADD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GRID.getChildren().remove(ADD);
                takeOrder(GRID, CCOUNT[0], ADD ,RCOUNT[0]);
                CCOUNT[0]++;
                RCOUNT[0]++;
            }
        });
        // create the back button
        Button back = new Button("Back");

        // if back is clicked on then go back
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        // add the back button
        // add the add button
        GRID.add(back, 3, 0);
        GRID.add(ADD, 0, 1);

        // change the stage settings
        STAGE.setTitle("Orders");
        STAGE.setScene(scene);
        STAGE.show();
    }

    /**
     * create a dynamic add order button
     * that allows the user to add and write the
     * input fields and add the products
     * @param GRID the table
     * @param row the row of the table
     * @param ADD Add order button
     * @param RCOUNT The row of the button
     */
    private void takeOrder(final GridPane GRID, int row, final Button ADD, final int RCOUNT){
        // the purchase label
        final Label PURCHASE = new Label("Purchase Order #: ");
        // the TextField to enter the purchase number
        final TextField PURCHASEO = new TextField();
        // the label date
        final Label DATE = new Label("Date: ");
        // the textfield to enter the date
        final TextField DATEO = new TextField();
        // the company description label
        final Label COMPANY = new Label("Company TO/Des: ");
        // the company description text area
        final TextArea COMPANYO = new TextArea();

        // change the size of the text area
        COMPANYO.setMaxHeight(200);
        COMPANYO.setMaxWidth(200);

        // create and add and add button
        final Button ADDED = new Button("Add");
        GRID.add(ADDED, 2, row+2);

        // the Row count
        final int[] RRCOUNT = {RCOUNT};
        // if add is clicked
        // then replace add button with
        // Company description, purchase order
        // and the date
        // add their label
        ADDED.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label itemCode = new Label("" + Math.floor(Math.random()*5000));
                Label itemDescription = new Label(DATEO.getText() + "\n" + COMPANYO.getText());
                Label qtyOrder = new Label(PURCHASEO.getText());



                GRID.getChildren().remove(COMPANY);
                GRID.getChildren().remove(COMPANYO);
                GRID.getChildren().remove(DATE);
                GRID.getChildren().remove(DATEO);
                GRID.getChildren().remove(PURCHASE);
                GRID.getChildren().remove(PURCHASEO);
                GRID.getChildren().remove(ADDED);

                GRID.add(itemCode, 0, RRCOUNT[0]);
                GRID.add(itemDescription, 1, RRCOUNT[0]);
                GRID.add(qtyOrder, 2, RRCOUNT[0]);
                GRID.add(ADD, 0, RRCOUNT[0]+1);
            }
        });

        // add everything
        GRID.add(COMPANY,0,row+1);
        GRID.add(COMPANYO,1,row+1, 1, 2);
        GRID.add(DATE,0,row);
        GRID.add(DATEO,1,row);
        GRID.add(PURCHASE, 0, row-1);
        GRID.add(PURCHASEO, 1, row-1);
    }
}
