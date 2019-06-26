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
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Owner on 11/1/2018.
 */
public class LetterFrequencies extends Application {
    private static final int WIDTH = 0x352;//850
    private static final int HEIGHT = 0x2bc;//700
    // map a string to an integer where string is the character
    // and the integer is the number of occurrences in the text
    private Map<String, Integer> data = new HashMap<>();
    // Text area to enter the text
    private TextArea myEntry;

    /**
     * start the class
     * @param STAGE the primary stage of the class
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // declare the root grid
        GridPane root = new GridPane();
        // take the main class
        final Main MAIN = new Main();



        // result label started as A-Z Frequencies
        // set maxWidth to WIDTH
        // set maxHeight to HEIGHT
        final Label RESULT = new Label("A-Z Frequencies: ");
        RESULT.setMaxWidth(WIDTH);
        RESULT.setMaxHeight(HEIGHT);

        // set the second result label
        final Label RESULT2 = new Label();

        // put text lenght starting as 0 in our data Hash map
        data.put("lenght", 0);
        // make a counter
        final int[] RES = {1};
         TextArea entry = new TextArea() {
             /**
              * (int, int, String) -> null
              * DO not allow user to enter anything but lower case
              * or upper case a-z letters and spaces then store
              * the current letter entered and the number of times
              * occurred
              * @param start start index of the text area
              * @param end end index of the text area
              * @param text the current text in the text area
              */
            @Override public void replaceText(int start, int end, String text) {
                // do not allow users to input none letters or none space
                if (text.matches("[a-zA-Z]|\\s")) {
                    // length of the text area increases
                    data.put("lenght", RES[0]++);
                    // if text is enter or space just ignore
                    if (text.equals("\n") || text.equals(" ")){

                    }
                    // if key does not exist
                    else if (!data.containsKey(text) ) {
                        // add letter and set occurrences to 1
                        data.put(text, 1);
                        // set result 1 to the current text
                        RESULT.setText(text + ": " + 1);
                    }else{
                        // set the integer of the letter and add by 1
                        int res = data.get(text)+1;
                        // update the letter occurrences
                        // update the current result 1
                        data.put(text, res);
                        RESULT.setText(text + ": " + data.get(text));

                    }
                    // else can not enter character
                    super.replaceText(start, end, text);
                }
            }
             /**
              * (int, int, String) -> null
              * DO not allow user to enter anything but lower case
              * or upper case a-z letters and spaces
              * @param text the current text in the text area
              */
             @Override public void replaceSelection(String text) {
                // do not allow users to input none letters or none space
                 if (text.matches("[a-zA-Z]|\\s")) {
                    super.replaceSelection(text);
                }
            }
        };

        entry.textProperty().addListener(new ChangeListener<String>() {
            /**
             * change the result label to the number of occurrences
             * for each letters inputted
             * @param observable the current value entered
             * @param oldValue the old value in the text area
             * @param newValue the new value in the text area
             */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // add a counter
                int res = 1;
                // str is our final string
                String str = "";
                // take all the letters with their probability and their number of
                // occurrences and put them all for the label
                for(String i : data.keySet()){
                    if (res % 4 == 0) {
                        int num = data.get(i);
                        str += i + ": " + String.format("%d/%d = %% %.4f", num,data.get("lenght"), (double)num/data.get("lenght")*100) + "\n";
                    }else{
                        if (data.get("lenght") != 0) {
                            int num = data.get(i);
                            str += i + ": " + String.format("%d/%d = %% %.4f", num,data.get("lenght"), (double)num/data.get("lenght")*100) + "\t\t\t";
                        }else{
                            int num = data.get(i);
                            str += i + ": " + String.format("%d/%d", num,data.get("lenght")) + "\t\t\t";
                        }

                    }
                    // set result to text to final str
                    // incriminate res
                    RESULT2.setText(str);
                    res++;
                }
            }
        });

        // set the text area width to WIDTH
        // set the text area height to HEIGHT
        // make position relative
        entry.setMinWidth(WIDTH);
        entry.setMaxHeight(HEIGHT - 600);
        entry.setStyle("-fx-position:relative;");

        // make a back button
        Button back = new Button("Back");
        // if back button is clicked go to the main class
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }});


        // add entry, result 1 and 2 and the back button to the grid
        root.add(entry, 0, 0, 10, 1);
        root.add(RESULT, 0, 1);
        root.add(RESULT2, 0, 2);
        root.add(back, 0, 3);



        // create a 850 x 700 scene with grid as the root
        Scene scene = new Scene(root, WIDTH, HEIGHT);


        // Stage
        STAGE.setTitle(getClass().toString());
        STAGE.setScene(scene);
        STAGE.show();

        // we will use the text area and
        // data map in other classes that's why we set it
        setTextArea(entry);
        setData(data);

    }

    /**
     * setter method for entry
     * @param t the TextArea of this class
     */
    private void setTextArea(TextArea t){
         this.myEntry = t;
    }

    /**
     *  getter method for the entry
     * @return get the entry TextArea
     */
    public TextArea getMyEntry(){
        return this.myEntry;
    }

    /**
     * setter method for data hash map
     * @param data hash map
     */
    public void setData(Map<String, Integer> data) {
        this.data = data;
    }

    /**
     * getter method of data hash map
     * @return get the data hash map
     */
    public Map<String, Integer> getData() {
        return data;
    }
}
