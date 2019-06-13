package com.easier.minesweeper;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Input {

    public static PVector mousePos;

    public Input(Scene scene) {
        mousePos = new PVector();

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePos.setX(event.getX());
                mousePos.setY(event.getY());

                if (Main.game == null) return;
                Main.drawGame();
            }
        });

        scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePos.setX(event.getX());
                mousePos.setY(event.getY());

                if (Main.game == null) return;
                Main.drawGame();

                Main.checkClick(event.getButton());
            }
        });

        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePos.setX(event.getX());
                mousePos.setY(event.getY());
                if (Main.game == null) return;
                Main.drawGame();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePos.setX(event.getX());
                mousePos.setY(event.getY());
                if (Main.game == null) return;
                Main.drawGame();
            }
        });

    }

}
