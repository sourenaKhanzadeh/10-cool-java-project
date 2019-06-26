package Gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;


/**
 * Created by Owner on 10/31/2018.
 */
public class ClothingCompany extends Application {

    private static final int WIDTH = 0x2bc;//700
    private static final int HEIGHT = 0x1f4;//500

    /**
     * Start the class
     * @param STAGE Class Stage
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // get the main class
        final Main MAIN = new Main();
        // create a grid
        final GridPane GRID = new GridPane();
        // create a top 10 button
        final Button TOP10 = new Button("Top 10");

        // add the top10 button to the grid
        GRID.add(TOP10, 0, 1);

        // create a back button
        final Button BACK = new Button("Back");
        // if back button is clicked then go back to the
        // main class
        BACK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }});
        // add the back button to the grid
        GRID.add(BACK, 0, 2);
        // if top 10 button is clicked
        TOP10.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * make a table of the top 10 items
             * of the clothing company using the
             * data method
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                // switch top10 text
                switch (TOP10.getText()){
                    case "Top 10":
                        // if Top 10 then
                        // add data method to the grid
                        // set top10 text to Refresh Top 10
                        GRID.add(data(), 0, 0);
                        TOP10.setText("Refresh Top 10");
                        break;
                    default:
                        // else
                        // remove data, top10 button and back button
                        GRID.getChildren().remove(0, 3);
                        // add data, top10 button and back button
                        GRID.add(data(), 0, 0);
                        GRID.add(TOP10, 0, 1);
                        GRID.add(BACK, 0, 2);

                }
            }
        });

        // Make a 700 x 500 scene with grid as the root
        Scene scene = new Scene(GRID, WIDTH, HEIGHT);


        // set stage title to the class
        // set staeg scene to the custom scene
        // show the stage
        STAGE.setTitle("" + getClass().toString());
        STAGE.setScene(scene);
        STAGE.show();
    }

    /**
     * Make a table for our clothing company
     * that stores the item code quantities
     * the total of each week and the Grand Total
     * @return a GridPane that give the table like rep of the clothing company
     */
    private GridPane data(){
        // instantiate a number format with canadian currency as the formatter
        NumberFormat nm = NumberFormat.getCurrencyInstance(Locale.CANADA);

        // create a new grid for our table
        // make the horizontal and vertical gap 5
        // set the padding to 20,20,20,20 -> top,right,bottom,left respectively
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(20,20,20,20));


        // create a label array that stores the primary headers
        Label[] header = {
                new Label("Item Code"),
                new Label("Qty Sold"),
                new Label("Original Price"),
                new Label("Total Price"),
                new Label("MON TUE WED THU FRI  SAT SUN")
        };
        // add the headers to the grid
        // set font to 15
        // turn color to 6c6c6c which is light grey
        for (int i = 0; i < 4 ; i++) {
            grid.add(header[i], i, 0);
            header[i].setFont(Font.font(15));
            header[i].setStyle("-fx-text-fill: #6c6c6c");
        }
        // add the days of the week to the grid as our secondary heading
        // set font to 14
        // set min width to 5cm
        // set color to 6c6c6c(light grey)
        grid.add(header[4], 1, 1);
        header[4].setFont(Font.font(14));
        header[1].setStyle("-fx-alignment: center;-fx-min-width: 5cm;-fx-text-fill: #6c6c6c");

        // add the random item codes
        for (int i = 2; i < 12 ; i++) {
            grid.add(new Label((int) (Math.random() * i) + "10F00"+ (int) (Math.random() * i)), 0, i);
        }

        // create a label array of size 10
        Label[] orPrice = new Label[10];
        // create a integer array of size 10
        int[] price = new int[10];

        // give 10 random original prices
        // set it to the label
        // make label min width to 100 and center it
        // add the 10 labels to the grid
        for (int i = 0; i <orPrice.length ; i++) {
            price[i] = (int) Math.round(Math.random() * 300);
            orPrice[i] = new Label(nm.format(price[i]));
            orPrice[i].setMinWidth(100);
            orPrice[i].setStyle("-fx-alignment: center;");
            grid.add(orPrice[i], 2, i+2);
        }

        // add a quantity label array of size 10
        Label[] quan = new Label[10];
        //add a 10 x 7 matrix of the quantities sold
        int[][] quantities = new int[10][7];

        // add a 10 x 7 matrix with discount rate stored
        // to calculate the total price
        double[][] totalPrice = {
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35},
                {0.15, 0.30, 0.25, 0.25,0.20,0.35, 0.35}

        };

        // assign random integers to the quantity array
        // calculate the the total price of each day
        for (int i = 0; i <quantities.length ; i++) {
            for (int j = 0; j <quantities[i].length ; j++) {
                quantities[i][j] = (int) (Math.random() * 30);
                totalPrice[i][j] = (price[i]- (totalPrice[i][j] *price[i])) * quantities[i][j];
            }
        }

        // create a total sum of each week prices array size of 10
        double[] totalSum = new double[10];

        // set a counter for total price
        double totRes = 0;

        // add the total's together for each day to get
        // the total for each week
        for (int i = 0; i <totalSum.length ; i++) {
            double res = 0;
            for (int j = 0; j <totalPrice[i].length ; j++) {
                res += totalPrice[i][j];
            }
            totRes += res;
            totalSum[i] = res;
        }

        // add the quantities as a label to the grid
        for (int i = 0; i < quan.length; i++) {
            quan[i] = new Label("  " +  quantities[i][0] +
                    "      " + quantities[i][1] +
                    "      " + quantities[i][2] +
                    "      " + quantities[i][3] +
                    "      " + quantities[i][4] +
                    "      " + quantities[i][5]+
                    "      " + quantities[i][6]
            );

            grid.add(quan[i], 1, i+2);
        }
        // set a counter for the row
        int r = 2;
        // format the price and add to the grid
        for (double i:
                totalSum){
            grid.add(new Label(String.format(nm.format(i))), 3, r);
            r++;
        }
        // add the grand total to the grid
        grid.add(new Label(String.format(nm.format(totRes))), 3, 12);

        // return grid
        return grid;
    }


}
