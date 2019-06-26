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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.math.BigInteger;

/**
 * Created by Owner on 11/2/2018.
 */
public class RSA extends Application {
    private static final int WIDTH = 0x438;//1080
    private static final int HEIGHT = 0x384;//900
    // enter the two prime numbers
    private static final int P = 61;
    private static final int Q = 53;
    // calculate N and O(n)
    private static final int N = P * Q;
    private static final int ON = ((P-1)*(Q-1));
    // enter a co prime E
    private static final int E = 17;
    // find the decoder value
    private static final int D = modInverse(E, ON);

    /**
     * start the class
     * @param STAGE primary stage of the application
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // take the main class
        final Main MAIN = new Main();
        // make the grid
        GridPane grid = new GridPane();
        // grand result
        final String[] RES = {""};
        //counter
        final int[] COUNT = {0};
        // encryption label
        final Label ENCRIPTED= new Label();
        // text area
        final TextArea ENCRIPT = new TextArea(){
            /**
             * only accept letters and space
             * @param start start of the text area
             * @param end end of the text area
             * @param text current text in the text area
             */
            @Override public void replaceText(int start, int end, String text) {
                // If the replaced text would end up being invalid, then simply
                // ignore this call!
                if (text.matches("[a-zA-Z]|\\s")) {
                    // count incriminated
                    COUNT[0] += 1;
                    // 30 code peri line
                    if (COUNT[0] % 30 == 0) {
                        // encrypt the letter and go to the next line
                        RES[0] += encryption(text.charAt(0)) + "  \n";

                    }else{
                        // don't go to the next line
                        RES[0] += encryption(text.charAt(0)) + "  ";
                    }
                    // set encryption label to result
                    ENCRIPTED.setText(RES[0]);
                    super.replaceText(start, end, text);
                }
            }
            @Override public void replaceSelection (String text){
                // don't allow none letters
                if (text.matches("[a-zA-Z]|\\s")) {
                        super.replaceSelection(text);
                    }

                }
        };

        // create a clear button
        Button clearEn = new Button("Clear");
        // if clear is clicked then clear the labels
        clearEn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ENCRIPT.setText("");
                ENCRIPTED.setText("");
                RES[0] = "";
                COUNT[0] = 0;
            }
        });

        // add everything
        grid.add(ENCRIPT, 0, 0);
        grid.add(ENCRIPTED, 0, 1);
        grid.add(clearEn, 0, 2);

        // create a 1080 x 900 scene with grid as the root
        Scene scene = new Scene(grid, WIDTH, HEIGHT);

        // create a decrepit label
        // set font to 50
        final Label DECRIPT = new Label();
        DECRIPT.setFont(Font.font(50));

        // create a text field for decript
        final TextField DECRIPTION = new TextField();
        // if clicked give the character that is decrypted
        DECRIPTION.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    DECRIPTION.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // create a decrypt button
        Button decrptIt = new Button("Decrypt");
        // if button clicked then give back the char value
        decrptIt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DECRIPT.setText(String.valueOf(decryption(DECRIPTION)));
                DECRIPTION.setText("");
            }
        });

        //create a back button
        Button back = new Button("Back");
        // if back is clicked then go to the main class
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MAIN.start(STAGE);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }});
        // add everything to the grid
        grid.add(DECRIPTION, 0, 3);
        grid.add(decrptIt, 0, 4);
        grid.add(DECRIPT, 0, 5);
        grid.add(back, 0, 6);

        // modify stage settings
        STAGE.setTitle(getClass().toString());
        STAGE.setScene(scene);
        STAGE.show();
    }

    /**
     * use the encryption formula to give a char
     * a different value to later decrypt it
     * @param alpha character inputted by the user
     * @return a numeric value that is encrypted
     */
    private static int encryption(char alpha){
        BigInteger a = new BigInteger("" + (int)alpha);

        return a.pow(E).mod(BigInteger.valueOf(N)).intValue();
    }

    /**
     * enter a numeric value of the char
     * @param num enter the numeric value
     * @return get the decrypted char
     */
    private static char decryption(TextField num){
        BigInteger a = new BigInteger("" + num.getText());
        return (char)a.pow(D).mod(BigInteger.valueOf(N)).intValue();
    }

    /**
     * get the mod inverse of a nad m
     * where a and m is any positive integer
     * @param a num 1
     * @param m num 2
     * @return mod inverse of num 1 and 2
     */
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int i = 1; i < m; i++)
            if ((a * i) % m == 1)
                return i;
        return 1;
    }

}
