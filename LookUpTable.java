package Gui;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 10/31/2018.
 */
public class LookUpTable extends Application {
    private static final int WIDTH = 0x438;//1080
    private static final int HEIGHT = 0x1f4;//500

    // Make a dictionary for our table called Table Value
    Map<String, String> tVal = new HashMap<>();

    /**
     * (Stage) -> null
     * Start the application
     * @param STAGE the main stage of this class
     * @throws Exception take the exceptions
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // Make An Instance Of The Main Page
        final Main MAIN = new Main();

        // store the table values in the
        // corresponding row and column
        myTable();


        /*
        make a grid
        with 20 horizontal and vertical gap
        and a padding of 20,20,20,20  top, right, bottom, left respectively
        */
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Create a scene and make the grid the root
        Scene scene = new Scene(grid, WIDTH, HEIGHT);

        // make another grid
        // this is the table
        // set the borders to visible with 5 horizontal gap
        // and a padding of 20,20,20,20  top, right, bottom, left respectively
        GridPane grid2 = new GridPane();
        grid2.setGridLinesVisible(true);
        grid2.setHgap(5);
        grid2.setPadding(new Insets(20, 20, 20, 20));

        // Make the Column headers of our table
        Label[] head0 = {
                new Label("0000"),
                new Label("0001"),
                new Label("0010"),
                new Label("0011"),
                new Label("0100"),
                new Label("0101"),
                new Label("0110"),
                new Label("0111"),
                new Label("1000"),
                new Label("1001"),
                new Label("1010"),
                new Label("1011"),
                new Label("1100"),
                new Label("1101"),
                new Label("1110"),
                new Label("1111"),
        };

        // Make the Row Headers of the table
        Label[] head1 = {
          new Label("00"),
          new Label("01"),
          new Label("10"),
          new Label("11")
        };

        // create a 16 by 4 matrix for our table
        final Label[][] CELLS = new Label[16][4];


        for (int i = 0; i <CELLS.length ; i++) {
            for (int j = 0; j <CELLS[i].length ; j++) {
                CELLS[i][j] = new Label(getVal(head1[j].getText() + head0[i].getText()));
                CELLS[i][j].setFont(Font.font(25));
                CELLS[i][j].setMinWidth(50);
                CELLS[i][j].setStyle("-fx-border-color: #ffffff;-fx-background-color: #00cf90; -fx-text-fill: #ffffff;");
            }
        }

        // start the column count
        int Ccount = 1;

        // place the column heads
        for(Label i:
                head0){
            i.setFont(Font.font(20));
            // change color of background, text, border
            i.setStyle("-fx-background-color: #009000;-fx-text-fill: #ffffff;-fx-border-color: #000000");
            grid2.add(i, Ccount, 0);
            Ccount++;
        }

        // set column count to 1
        Ccount = 1;

        // place the row heads
        for(Label i:
                head1){
            i.setFont(Font.font(20));
            // change color of background, text, border
            i.setStyle("-fx-background-color: #009000;-fx-text-fill: #ffffff;-fx-border-color: #000000");
            grid2.add(i, 0, Ccount);
            Ccount++;
        }


        // set column count to 1
        Ccount = 1;
        // initialize row count
        int rCount = 1;

        // make the rest of the table
        for (int i = 0; i < CELLS.length; i++) {
            for (int j = 0; j <CELLS[i].length ; j++) {
                grid2.add(CELLS[i][j], Ccount, rCount);
                rCount++;
            }
            Ccount++;
            rCount = 1;
        }




        // make a text field to ask user's to input the 6 bit code
        final TextField USER_INPUT = new TextField();
        //add userInput to the root
        grid.add(USER_INPUT, 0, 0);


        // add a button that takes the user's 6 bits code
        Button get = new Button("get");

        // add a label that take's the stored value of the 6 bits code
        final Label LABEL = new Label();
        // set font to 30
        LABEL.setFont(Font.font(36));

        get.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * take the user inputs value and get back the
             * stored value of the 6 bits code and set the
             * label to that value
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                // 6bit code
                String user = USER_INPUT.getText();
                // format the code rightly
                user = user.charAt(0) + "" + user.charAt(user.length() - 1) + user.substring(1, user.length() - 1);
                // set the takes to the value of the 6 bits code
                LABEL.setText(getVal(user));

                // get the row integer
                int row = binToDec(user.substring(0, 2));
                // get the column integer
                int col = binToDec(user.substring(2, user.length()));

                // if cell is green and is the result change to red
                // if cell is yellow and is the result change to red
                // if cell is red and is the result change to yellow
                switch (CELLS[col][row].getStyle()) {
                    case "-fx-text-fill: #ffffff;-fx-background-color: red;":
                        CELLS[col][row].setStyle("-fx-background-color: #ffff00; -fx-text-fill: #000000;");
                        break;
                    default:
                        CELLS[col][row].setStyle("-fx-text-fill: #ffffff;-fx-background-color: red;");
                        break;
                }


            }
        });

        // make a clear button to change back the
        // color of the cells back to green
        Button clear = new Button("Clear");

        clear.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * If this button is clicked
             * then change all colors of the cells back
             * to its default color
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                for (Label[] i :
                        CELLS) {
                    for (Label j :
                            i) {
                        j.setStyle("-fx-border-color: #ffffff;-fx-background-color: #00cf90; -fx-text-fill: #ffffff;");
                    }
                }
            }
        });

        // make a back button to go back the
        // main file
        Button back = new Button("Back");

        back.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * If button is clicked then
             * go back to the main file
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        // add all the buttons, labels, grid and textfields
        grid.add(get, 0, 1);
        grid.add(LABEL, 0,2);
        grid.add(grid2, 0,3);
        grid.add(back, 0, 4);
        grid.add(clear, 1, 1);

        // Set the stages title
        STAGE.setTitle("Look Up Table");
        // Set the stages scene
        STAGE.setScene(scene);
        // show the stage
        STAGE.show();

    }

    /**
     * Make a data for our table
     * put it in a dictionary
     * Map a String to Another String
     */
    private void myTable(){
       // Row 1
       tVal.put("000000", "8");
       tVal.put("000001", "15");
       tVal.put("000010", "5");
       tVal.put("000011", "13");
       tVal.put("000100", "9");
       tVal.put("000101", "10");
       tVal.put("000110", "14");
       tVal.put("000111", "3");
       tVal.put("001000", "4");
       tVal.put("001001", "1");
       tVal.put("001010", "6");
       tVal.put("001011", "12");
       tVal.put("001000", "4");
       tVal.put("001100", "7");
       tVal.put("001101", "0");
       tVal.put("001110", "11");
       tVal.put("001111", "2");
       // Row 2
       tVal.put("010000", "11");
       tVal.put("010001", "6");
       tVal.put("010010", "15");
       tVal.put("010011", "10");
       tVal.put("010100", "8");
       tVal.put("010101", "6");
       tVal.put("010110", "4");
       tVal.put("010111", "14");
       tVal.put("011000", "3");
       tVal.put("011001", "9");
       tVal.put("011010", "7");
       tVal.put("011011", "2");
       tVal.put("011100", "5");
       tVal.put("011101", "1");
       tVal.put("011110", "12");
       tVal.put("011111", "14");
       // Row 3
       tVal.put("100000", "7");
       tVal.put("100001", "9");
       tVal.put("100010", "5");
       tVal.put("100011", "11");
       tVal.put("100100", "3");
       tVal.put("100101", "8");
       tVal.put("100110", "10");
       tVal.put("100111", "12");
       tVal.put("101000", "1");
       tVal.put("101001", "15");
       tVal.put("101010", "13");
       tVal.put("101011", "14");
       tVal.put("101100", "0");
       tVal.put("101101", "2");
       tVal.put("101110", "4");
       tVal.put("101111", "6");
       // Row 4
       tVal.put("110000", "15");
       tVal.put("110001", "10");
       tVal.put("110010", "1");
       tVal.put("110011", "14");
       tVal.put("110100", "7");
       tVal.put("110101", "4");
       tVal.put("110110", "9");
       tVal.put("110111", "13");
       tVal.put("111000", "2");
       tVal.put("111001", "12");
       tVal.put("111010", "6");
       tVal.put("111011", "8");
       tVal.put("111100", "11");
       tVal.put("111101", "5");
       tVal.put("111110", "3");
       tVal.put("111111", "11");



   }

    /**
     * get a table value
     * @param bin input a string format of a binary number
     * @return the value of the table
     */
    private String getVal(String bin){
       return tVal.get(bin);
   }

    /**
     * (String) -> int
     * Take in a string format of a binary number
     * and turn it into Radix 10
     * Radix 2 ---> Radix 10
     * @param bin number in base 2
     * @return decimal value of bin in base 10
     */
    private int binToDec(String bin){
        int res = 0;
        int j = 0;
        // for as many bits there exist take the bit
        // and multiply it by 2^the bits length
        // ex. 1111 =  1 * 2^0 + 1 * 2^1 + 1 * 2^2  + 1 * 2^3  = 15
        for (int i = bin.length()-1; i >= 0 ; i--) {
            res += Integer.parseInt(String.valueOf(bin.charAt(i))) * Math.pow(2, j);
            j++;
        }
        // return the Radix 10 value
        return res;
    }
}
