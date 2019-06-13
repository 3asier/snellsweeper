package com.easier.minesweeper;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;

import java.util.Random;

public class SnelField {

    Random random;

    private Tile[][] tiles;
    private PVector size;

    public SnelField(PVector size) {
        random = new Random();

        this.size = size;
        tiles = new Tile[(int) size.getX()][(int) size.getY()];
        generateField();
        calcNumbers();
    }

    private void generateField() {
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                if (random.nextInt(8) == 0) tiles[x][y] = new Tile(new PVector(x * Tile.size, y * Tile.size), true);
                else tiles[x][y] = new Tile(new PVector(x * Tile.size, y * Tile.size), false);
            }
        }
    }

    private void calcNumbers() {
        Tile[][] temp = new Tile[(int) size.getX() + 2][(int) size.getY() + 2];

        for (int x = 0; x < size.getX() + 2; x++) {
            for (int y = 0; y < size.getY() + 2; y++) {
                if (x == 0 || y == 0 || x == size.getX() + 1 || y == size.getY() + 1) temp[x][y] = new Tile(new PVector(), false);
                else temp[x][y] = tiles[x - 1][y - 1];
            }
        }



        for (int x = 1; x < size.getX() + 1; x++) {
            for (int y = 1; y < size.getY() + 1; y++) {
                int count = 0;

                if (temp[x - 1][y - 1].hasSnel()) count++;
                if (temp[x][y - 1].hasSnel()) count++;
                if (temp[x + 1][y - 1].hasSnel()) count++;

                if (temp[x - 1][y].hasSnel()) count++;
                if (temp[x + 1][y].hasSnel()) count++;

                if (temp[x - 1][y + 1].hasSnel()) count++;
                if (temp[x][y + 1].hasSnel()) count++;
                if (temp[x + 1][y + 1].hasSnel()) count++;

                tiles[x - 1][y - 1].number = count;
            }
        }
    }

    public boolean checkCollision(MouseButton b) {
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                if (tiles[x][y].checkCollision()) {
                    if (tiles[x][y].collision(b)) return true;
                    if (tiles[x][y].number == 0 && b == MouseButton.PRIMARY) showAllEmpty(x, y);
                }
            }
        }
        return false;
    }

    public void showAllEmpty(int x, int y) {
        for (int x1 = -1; x1 <= 1; x1++) {
            for (int y1 = -1; y1 <= 1; y1++) {
                // Check that its out of bounds.
                if (x + x1 < 0 || x + x1 >= size.getX()) continue;
                if (y + y1 < 0 || y + y1 >= size.getY()) continue;

                if (tiles[x + x1][y + y1].number == 0 && tiles[x + x1][y + y1].state == Tile.TileState.UNPRESSED) {
                    tiles[x + x1][y + y1].state = Tile.TileState.PRESSED;
                    showAllEmpty(x + x1, y + y1);
                }

                tiles[x + x1][y + y1].state = Tile.TileState.PRESSED;
            }
        }
    }

    public boolean checkWin() {
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                if (tiles[x][y].state == Tile.TileState.FLAGGED) {
                    if (!tiles[x][y].hasSnel()) {
                        return false;
                    }
                }

                if (tiles[x][y].state != Tile.TileState.FLAGGED) {
                    if (tiles[x][y].hasSnel()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void showAll() {
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                tiles[x][y].state = Tile.TileState.PRESSED;
            }
        }
    }

    public void draw(GraphicsContext gc) {
        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                tiles[x][y].draw(gc);
            }
        }
    }

}
