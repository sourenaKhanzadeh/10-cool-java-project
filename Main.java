package Gui;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.Animation.*;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Created by Owner on 10/31/2018.
 */
public class Main extends Application {
    private static final  int WIDTH = 0x44c;//1100
    private static final  int HEIGHT = 0x1f4;//500

    /**
     * run the class
     * @param STAGE The primary stage of the class
     * @throws Exception
     */
    @Override
    public void start(final Stage STAGE) throws Exception {
        // make an instance of all the classes in the package
        final Primes P = new Primes();
        final LookUpTable LOOK = new LookUpTable();
        final TSeires T = new TSeires();
        final Accounts ACCOUNTS = new Accounts();
        final Orders ORDERS = new Orders();
        final LetterFrequencies LETTER_FREQUENCIES = new LetterFrequencies();
        final RSA MY_RSA = new RSA();
        final ClothingCompany CLOTHING_COMPANY = new ClothingCompany();
        final Compression COMPRESSION = new Compression();
        final Invoice INVOICE = new Invoice();


        // make a grid for the root of our scene
        GridPane grid = new GridPane();
        // set the horizontal and vertical gap of 20
        grid.setHgap(20);
        grid.setVgap(20);
        // set the grid padding to 20,20,20,20 --> top,right,bottom,left respectively
        grid.setPadding(new Insets(20,20,20,20));
        // center the root grid
        grid.setAlignment(Pos.CENTER);

        grid.add(animation(950), 0,1);
        grid.add(animation(-950), 5,2);



        // create a 1100 x 500 scene where grid is the root
        Scene scene = new Scene(grid, WIDTH, HEIGHT);

        // make a size 10 array of buttons convenient to our
        // class name
        Button[] projects = {
                new Button("LookUpTable"),
                new Button("Primes"),
                new Button("TSeires"),
                new Button("CheckingAccount"),
                new Button("Order"),
                new Button("Letter Frequency"),
                new Button("RSA"),
                new Button("Clothing Company"),
                new Button("Compression"),
                new Button("Invoice")

        };

        // start column count and row count as 0
        int cCount = 0;
        int rCount = 0;
        // for each buttons
        // put 5 buttons in every grid line
        for(Button i :
                projects){
            if (cCount % 5 == 0) {
                cCount = 0;
                grid.add(i, cCount, rCount+1);
                rCount++;
            }else{
            grid.add(i, cCount, rCount);
            }
            cCount++;
        }

        // if project 1 is clicked start project 1 class
       projects[0].setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               try {
                   LOOK.start(STAGE);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });

        // if project 2 is clicked start project 2 class
        projects[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    P.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // if project 3 is clicked start project 3 class
       projects[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    T.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // if project 4 is clicked start project 4 class
        projects[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ACCOUNTS.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // if project 5 is clicked start project 5 class
        projects[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ORDERS.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // if project 6 is clicked start project 7 class
        projects[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LETTER_FREQUENCIES.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // if project 7 is clicked start project 7 class
        projects[6].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MY_RSA.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // if project 8 is clicked start project 8 class
        projects[7].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CLOTHING_COMPANY.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // if project 9 is clicked start project 9 class
        projects[8].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    COMPRESSION.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // if project 10 is clicked start project 10 class
        projects[9].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    INVOICE.start(STAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // create a label that determines my choice project with a smiley face
        // change its font to 30
        // make text color grey
        Label label = new Label("LookUpTable is my Main Choice. \u263A");
        label.setFont(Font.font(30));
        label.setStyle("-fx-text-fill: grey");
        // add label to the grid
        grid.add(label,0,0);

        // set the stage title to My Projects
        // set the stage scene to scene
        // show the current stage
        STAGE.setTitle("My Projects");
        STAGE.setScene(scene);
        STAGE.show();

    }

    /**
     * (int) -> Circle
     * Simple animation of circles
     * to make application more fun to
     * look at
     * @param x set the x value that circle must travel to
     * @return give the floating squares
     */
    private Circle  animation(int x){
        // Create a new circle
        // color it aquamarine
        // set its radius to30
        // se layout x and y to 50
        final Circle circle = new Circle();
        circle.setFill(Color.AQUAMARINE);
        circle.setRadius(30);
        circle.setLayoutX(50);
        circle.setLayoutY(50);
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            // if circle is clicked on then change the circles
            // color
            @Override
            public void handle(MouseEvent event) {
                // take circle color
                Color fill = (Color) circle.getFill();

                //if color is Aquamarine then change it to red
                // if color is red then change it to yellow
                // if color is yellow then change to Aquamarine
                if (fill == Color.AQUAMARINE) {
                    circle.setFill(Color.RED);

                }else if(fill == Color.RED){
                    circle.setFill(Color.YELLOW);
                }else{
                    circle.setFill(Color.AQUAMARINE);
                }

            }
        });

        // Use a Translate transition to move the circle
        // Ease in and set x and y position and auto_reverse it set
        // the cycle amount to infinite
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5), circle);
        transition.setInterpolator(Interpolator.EASE_IN);
        transition.setByX(x);
        transition.setAutoReverse(true);
        transition.setCycleCount(Animation.INDEFINITE);
        // play the animation
        transition.play();
        // return the circle
        return circle;

    }
}
