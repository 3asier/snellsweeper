package com.easier.minesweeper;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public ScheduledExecutorService executorService;

    public static Image snelter;
    public static Image flag;

    public static Game game;

    public static Group root;
    public static GraphicsContext gc;

    public void start(Stage stage) {
        stage.setTitle("Snellsweeper");

        root = new Group();
        Scene theScene = new Scene(root);
        stage.setScene(theScene);

        Canvas canvas = new Canvas(800, 800);
        root.getChildren().add(canvas);
        stage.setResizable(false);

        // ----- INPUT -----
        Input input = new Input(theScene);


        // ----- Graphics ----
        gc = canvas.getGraphicsContext2D();

        try {
            snelter = new Image(new FileInputStream("res/snelter.png"));
            flag = new Image(new FileInputStream("res/flag.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addButtons(root, gc);

        stage.show();
    }

    public static void addButtons(Group root, GraphicsContext gc) {
        Button easy = new Button("Easy");
        Button hard = new Button("Hard");
        Button ezpert = new Button("Ezpert");

        VBox vBox = new VBox(easy, hard, ezpert);

        root.getChildren().add(vBox);

        buttonSetup(easy, gc, root, vBox);
        buttonSetup(hard, gc, root, vBox);
        buttonSetup(ezpert, gc, root, vBox);
    }

    private static void buttonSetup(Button button, GraphicsContext gc, Group root, VBox vBox) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switch (button.getText()) {
                    case "Easy":
                        Tile.size = 800 / 20;
                        game = new Game(Game.Difficulty.EASY, gc);
                        break;
                    case "Hard":
                        Tile.size = 800 / 50;
                        game = new Game(Game.Difficulty.HARD, gc);
                        break;
                    case "Ezpert":
                        Tile.size = 800 / 100;
                        game = new Game(Game.Difficulty.EZPERT, gc);
                        break;
                }

                root.getChildren().remove(vBox);
            }
        });
    }

    public static void drawGame() {
        game.draw();
    }

    public static void checkClick(MouseButton b) {
        game.checkCollision(b);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
