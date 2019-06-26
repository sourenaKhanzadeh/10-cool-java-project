package Gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Created by Owner on 11/2/2018.
 */
public class Compression extends Application {
    private static final int WIDTH = 0x3e8;//1000
    private static final int HEIGHT = 0x2bc;//700
    private TextArea entry;
    private Map<String, Integer> data;

    /**
     * run the class
     * @param STAGE the primary stage of the class
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception{
        // instantiate the main class
        final Main MAIN = new Main();
        // create a grid
        GridPane grid = new GridPane();
        // take letter freq data and entry
        letterFreq(STAGE);

        // add the entry
        grid.add(entry, 0, 0);

        // our answere fields
        final Label ANS = new Label("");
        final Label ANS2 = new Label("");
        // make a counter
        final int COUNT[] = {1};

        // override the entry text property
        entry.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // our results
                String res = "";
                String res2 = "";
                // column counter
                double ccount = 0;
                // as long as there exist data
                for(String i : data.keySet()){
                    // if i is length then skip
                    if (i.equals("lenght")) {

                    }
                    // if i is not length
                    else if (data.get("lenght") != 0) {
                        // print the next probabilities
                        if (COUNT[0] %10 == 0) {
                            int num = data.get(i);
                            res += String.format("P(%s) = %.4f", i, (double) num/data.get("lenght")) + "\n";

                        }
                        // store the first ten probabilities
                        else{

                            int num = data.get(i);
                            res += String.format("P(%s) = %.4f", i, (double) num /data.get("lenght")) + "\t";

                           }

                       }
                    // if data is not length
                    if (!i.equals("lenght")) {
                        // take the number of occurrences
                        int num = data.get(i);
                        // check the bites
                        int bits = data.keySet().size()-1;
                        // count the probability
                        ccount += Math.ceil(-Math.log10(((double) num/data.get("lenght")))/Math.log10(2));
                        // get the average
                        double average = ccount/data.get("lenght");
                        // calculate the sum
                        // calculate the average sum
                        // calculate the average bits
                        // calculate the average sum(P(x))
                        res2 = String.format("Sum(P(x)) = %.4f",  ccount) + "\n\n Average(Sum(P(x))) = " + average + "\n Average Bits = log2(" + bits + ") = " +
                                Math.log10(bits)/Math.log10(2) + "\n\nAverage(Sum(P(x))) <= log2(" + bits + ")\n" + average + "<=" + Math.log10(bits)/Math.log10(2);
                        if (average < Math.log10(bits)/Math.log10(2)) {
                            res2 += "\n\n Text Is Compressible";
                        }else{
                            res2 += "\n\n Text Is Not Compressible";
                        }
                    }
                    COUNT[0]++;
                    // set the labels to the result strings
                    ANS.setText(res);
                    ANS2.setText(res2);
                    ANS2.setFont(Font.font(40));
                }
            }
        });
        // make a back button
        Button back = new Button("Back");

        // if back button is clicked go back to the main class
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }});

        // add first answer to the field
        grid.add(ANS, 0, 1);
        grid.add(ANS2, 0, 2);
        grid.add(back, 0, 3);

        // crate 1000 x 700 scene with grid as the root
        Scene scene = new Scene(grid, WIDTH, HEIGHT);

        // modify the stage setting
        STAGE.setTitle(getClass().toString());
        STAGE.setScene(scene);
        STAGE.show();
    }

    /**
     * run letter Freq class and take
     * entry and data values
     * @param stage letterFreq primary stage
     */
    private void letterFreq(Stage stage){
        // instantiate letterFreq class
        LetterFrequencies lF = new LetterFrequencies();
        // start letter freq
        try {
            lF.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // take desired data
        entry = lF.getMyEntry();
        data = lF.getData();
    }
}
