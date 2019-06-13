package com.easier.minesweeper;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class Game {

    private SnelField snelField;

    private Difficulty difficulty;

    GraphicsContext gc;

    boolean won = false;

    public static enum Difficulty {
        EASY,
        HARD,
        EZPERT
    }

    public Game(Difficulty difficulty, GraphicsContext gc) {
        this.gc = gc;
        this.difficulty = difficulty;

        startGame();

        draw();
    }

    public void startGame() {
        PVector size;

        switch(difficulty) {
            case EASY:
                size = new PVector(20, 20);
                break;
            case HARD:
                size = new PVector(50, 50);
                break;
            case EZPERT:
                size = new PVector(100, 100);
                break;
            default:
                size = new PVector(20, 20);
        }

        snelField = new SnelField(size);
    }

    public void checkCollision(MouseButton b) {
        if (snelField.checkCollision(b)) {
            endGame();
        }
        won = snelField.checkWin();
        draw();
    }

    /**
     * End the game!
     */
    public void endGame() {
        snelField.showAll();
        Main.addButtons(Main.root, Main.gc);
    }

    public void draw() {
        snelField.draw(gc);
        if (won) {
            gc.setFill(Color.SPRINGGREEN);
            gc.fillText("Eat my ass!", 50, 50);
        }
    }

}
