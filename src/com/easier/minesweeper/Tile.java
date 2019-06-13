package com.easier.minesweeper;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.awt.*;

public class Tile {

    public static int size = 40;
    public PVector location;

    private boolean snel;
    public int number = 0;

    public TileState state;

    public enum TileState {
        PRESSED,
        UNPRESSED,
        FLAGGED
    }

    public Tile(PVector location, boolean snel) {
        this.snel = snel;
        state = TileState.UNPRESSED;
        this.location = location;
    }

    public boolean hasSnel() {
        return this.snel;
    }

    public void setSnel(boolean snel) {
        this.snel = snel;
    }

    public boolean checkCollision() {
        if(Input.mousePos.getX() > location.getX() && Input.mousePos.getX() < location.getX() + size) {
            if(Input.mousePos.getY() > location.getY() && Input.mousePos.getY() < location.getY() + size) {
                return true;
            }
        }
        return false;
    }

    public boolean collision(MouseButton b) {
        if (b == MouseButton.PRIMARY) {
            if (state == TileState.FLAGGED) {
                state = TileState.UNPRESSED;
                return false;
            }
            else if (state == TileState.UNPRESSED) {
                state = TileState.PRESSED;

                if (snel) {
                    return true;
                }
            }
            return false;
        } else if (b == MouseButton.SECONDARY) {
            if (state == TileState.UNPRESSED) state = TileState.FLAGGED;
            else if (state == TileState.FLAGGED) state = TileState.UNPRESSED;
        }
        return false;
    }

    public void draw(GraphicsContext gc) {
        if (state == TileState.PRESSED) {
            // Draw the tile
            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(location.getX(), location.getY(), size, size);
            gc.setFill(Color.LIGHTSLATEGRAY);
            gc.fillRect(location.getX(), location.getY(), size-2, size-2);

            if (snel) {
                gc.drawImage(Main.snelter, location.getX(), location.getY(), size, size);
            }

            // Draw the numbers
            if (number == 0) return;
            gc.setFill(Color.PINK);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText(String.valueOf(number), location.getX() + size / 2, location.getY() + size / 2);
        } else if (state == TileState.UNPRESSED) {
            gc.setFill(Color.GRAY);
            gc.fillRect(location.getX(), location.getY(), size, size);
            gc.setFill(Color.DARKGRAY);
            gc.fillRect(location.getX(), location.getY(), size-2, size-2);
        } else if (state == TileState.FLAGGED) {
            // Draw the tile
            gc.setFill(Color.GRAY);
            gc.fillRect(location.getX(), location.getY(), size, size);
            gc.setFill(Color.DARKGRAY);
            gc.fillRect(location.getX(), location.getY(), size-2, size-2);

            gc.drawImage(Main.flag, location.getX(), location.getY(), size, size);
        }
    }

}
