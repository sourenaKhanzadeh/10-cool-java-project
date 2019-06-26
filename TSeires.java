package Gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.regex.Pattern;

/**
 * Created by Owner on 10/30/2018.
 */
public class TSeires extends Application {
    private final int width = 0b10000111000;//1080
    private final int height = 0b1110001110;//910

    /**
     * Start the class
     * @param STAGE the primary stage
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // instantiate the main class
        final Main MAIN = new Main();

        // Grid
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20, 20, 20, 20));

        //  Scene
        Scene scene = new Scene(grid, width,height);

        //formula
        Label formula = new Label("e^x = 1 + x/1! + x^2/2! + x^3/3 + ... , + \u221E < x < -\u221E");
        formula.setFont(Font.font(40));
        grid.add(formula, 0, 0, 2, 1);

        // x and n
        Label x = new Label("x = ");
        Label n = new Label("n = ");
        x.setFont(Font.font(40));
        n.setFont(Font.font(40));
        grid.add(x, 0, 1);
        grid.add(n, 0, 2);

        // x and n inputs
        final TextField XINPUT = new TextField();
        final TextField NINPUT = new TextField();
        XINPUT.setMaxWidth(200);
        NINPUT.setMaxWidth(200);

        // do not allow user to input non numeric characters
        XINPUT.textProperty().addListener(new ChangeListener<String>() {
            /**
             * 
             * @param observable
             * @param oldValue
             * @param newValue
             */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Pattern pattern = Pattern.compile("^\\d*.\\d*$||^-\\d*.\\d*");

                if (!pattern.matcher(newValue).matches()) {
                    try {

                        XINPUT.setText(newValue.replaceAll("[^\\d*]", ""));
                    }catch (Exception e){

                    }
                }
            }
        });


        // do not allow user to input non numeric characters
        NINPUT.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    NINPUT.setText(newValue.replaceAll("[^\\d*]", ""));
                }
            }
        });
        // create a back button
        Button back = new Button("Back");
        // if back button is clicked on
        // then go back
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        // add the x and n input field
        grid.add(XINPUT, 1,1);
        grid.add(NINPUT, 1,2);

        // result label
        final Label ANS = new Label();
        ANS.setFont(Font.font(36));

        // Calculate Button
        Button calculate = new Button("Calculate");

        // if calculate button is clicked on then calculate to get the result
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double res = tCalculate(Double.parseDouble(XINPUT.getText()), Integer.parseInt(NINPUT.getText()));
                ANS.setText("if x = " + XINPUT.getText() + " n = " + NINPUT.getText() + "---->    " + res);
            }
        });

        // add all the fields
        grid.add(calculate, 0, 3, 1, 1);
        grid.add(ANS, 0, 4, 2, 1);
        grid.add(back, 0,5);

        // Modify the Stage
        STAGE.setTitle("TSeries");
        STAGE.setScene(scene);
        STAGE.show();
    }

    /**
     * replace the values and find the solution
     * to the formula
     * @param x the x value in the formula
     * @param n the range of the formula
     * @return a double where it gives the result of the formula
     */
    private double tCalculate(double x, int n){
        double res = 0;
        int fact = 1;
        // solve the equation
        for (int i = 1; i < n ; i++) {
            fact *= i;
            res += Math.pow(x, i)/fact;
        }
        return 1 +res;
    }
}
