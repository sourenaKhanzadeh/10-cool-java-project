package Gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.math.BigInteger;
import java.util.ArrayList;



/**
 * Created by Owner on 10/30/2018.
 */
public class Primes extends Application{
    /**
     * Start The Application
     * @param STAGE our Primes Stage
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // make an instance of the main class
        final Main MAIN = new Main();

        // Make scrollable application
        ScrollPane scrollPane = new ScrollPane();

        // make a grid with 20 horizontal and vertical gap
        // and padding of 20,20,20,20 top, right, bottom, left
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20,20,20,20));

        // create a scene with the root as our scroller
        Scene scene = new Scene(scrollPane, 1080, 912);

        // use this stylesheet
        scene.getStylesheets().add("Gui/PrimeStyle.css");

        // make a label My Prime as the title
        Label title = new Label("My Primes");

        // make a start range textField
        final TextField RANGE0 = new TextField("Start From");
        // make a label name to in between start range and end range
        Label to = new Label("To");
        // create an end range textField
        final TextField RANGE1 = new TextField("End");
        // Create a button to get all the primes from the
        // start to end range
        Button find = new Button("Get Primes");

        //output the primes here
        final Label ANS = new Label("Primes");

        // Change title font alignment and  width
        title.setFont(Font.font(30));
        title.setAlignment(Pos.CENTER);
        title.setMinWidth(300);

        // give an id to the prime output
        ANS.setId("ans");

        RANGE0.textProperty().addListener(new ChangeListener<String>() {
            /**
             * Do not allow the user to enter a non integer
             * character
             * @param observable
             * @param oldValue
             * @param newValue
             */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                // if current value is not decimal character then
                // replace it whit ""
                if (!newValue.matches("\\d*")) {
                    RANGE0.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        RANGE1.textProperty().addListener(new ChangeListener<String>() {
            /**
             * Do not allow the user to enter a non integer
             * character
             * @param observable
             * @param oldValue
             * @param newValue
             */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                // if current value is not decimal character then
                // replace it whit ""
                if (!newValue.matches("\\d*")) {
                    RANGE1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        // change end range max width to 50
        RANGE1.maxWidth(50);

        // if you click on find button
        find.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * If this button is clicked then
             * take start and end range and give
             * all the primes in between the range
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                // if none integers are inputted on one of the ranges then do nothing
                 if (!RANGE0.getText().matches("\\d*") ||  !RANGE1.getText().matches("\\d*")) {

                 }//else
                 else{
                     //create a list to store primes
                 ArrayList<Object> myPrimes = primes(Integer.parseInt(RANGE0.getText()), Integer.parseInt(RANGE1.getText()));
                 // make a result variable to format all desired primes
                 String res = ANS.getText();

                 // if result is equal to prime then get the ans text
                 if (res.equals("Primes")) {
                     res = ANS.getText();
                 }else{
                     //else result starts with "Primes"
                     res = "Primes";
                 }
                 // set ans to nothing
                 ANS.setText("");
                 // add the ranges number to prime and the size of our primes list
                 res += " from "+ RANGE0.getText() + " To " + RANGE1.getText() + "\nThere Are "+ myPrimes.size() +" Primes\n--------------------------------";
                 // as lon as there exist a prime in the prime list print the next 40 primes
                 // then go to the next line
                 for (int i = 0; i <myPrimes.size() ; i++) {
                     if (i % 40 != 0) {
                         res += myPrimes.get(i) + ", ";
                     }
                     else{
                         res += "\n" + myPrimes.get(i) + ", ";
                     }
                 }
                 // clear prime list
                 myPrimes.clear();
                 // end with break line
                 res += "\n--------------------------------";
                 // set the answere to the formatted prime from start to end range
                 ANS.setText(res);

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

        // add everything to the grid
        grid.add(title, 0, 0, 3, 1);
        grid.add(RANGE0, 0,1);
        grid.add(to, 1,1);
        grid.add(RANGE1, 2,1);
        grid.add(find, 0,2, 3, 1);
        grid.add(ANS, 0,3, 3, 1);
        grid.add(back, 0, 4, 3, 1);
        scrollPane.setContent(grid);

        // set stage title
        // set stage scene
        // show stage
        STAGE.setTitle("Prime Numbers");
        STAGE.setScene(scene);
        STAGE.show();


    }

    /**
     * get the start and range and then return
     * the list of primes
     * @param start range start's with int start
     * @param end range end with int end
     * @return give an Array list of the primes
     */
    ArrayList<Object> primes(int start, int end){
        // create a prime Array List
        ArrayList<Object> res = new ArrayList<>();

        // from the range
        // Check if the number is prime
        // if prime then add to array list
        while(start < end+1){
            BigInteger num = new BigInteger("" + start);

            if(isItPrime(start)){
                res.add(num.intValue());
            }

            start++;
        }

//      Alternative Algorithm with integers only
//            for (int i = 1; i < start; i++) {
//                if (start % i == 0) {
//                    count++;
//                }
//                if (count> 2) {
//                    break;
//                }
//            }

//            if (count <= 2) {
//                res.add(start);
//            }

//            start++;
//        }

        // return the array list
        return res;
    }

    /**
     * give number return true if number is prime
     * @param number integer to check if integer is a prime
     * @return true if the integer is prime
     */
    boolean isItPrime(int number){
        return new BigInteger("" + number).isProbablePrime(number);
    }
}
