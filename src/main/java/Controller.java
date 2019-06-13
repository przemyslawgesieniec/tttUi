package main.java;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class Controller {

    private String sign;
    private String oponentSign;
    private String clientName = "client1";
    private boolean isMyTurn = true;
    private Map<Button, Boolean> buttons = new HashMap<>();

    public void setScene(Scene scene) {
        this.scene = scene;
        initializeButtonArray();
    }

    private Scene scene;

    public Controller() {

        assignSigns();
        readMessagesFromServer(clientName);
    }

    public void initializeButtonArray() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons.put((Button) scene.lookup("#b" + i + j), false);
            }
        }
    }


    public void sendMessage(ActionEvent actionEvent) {

        final Button button = ((Button) actionEvent.getSource());
        final String id = button.getId();
        final List<Integer> listOfIndexes = id.substring(1)
                .chars()
                .boxed()
                .map(Character::getNumericValue)
                .collect(Collectors.toList());

        button.setText(sign);
        buttons.replace(button, true);
        disableAllButtons();
        isMyTurn = false;
        //TODO sendMessage
    }


    private void assignSigns() {

        final String mySign = "x";
        final String oSign = "o";
        //todo ask server for sign assignment

        sign = "[" + mySign + "]";
        oponentSign = "[" + oSign + "]";

    }


    private void readMessagesFromServer(final String clientName) {

        new Thread(() -> {

            while (true) {

                try {
                    Thread.sleep(8000);
                    isMyTurn = true;
                    //todo ask server for data;

                    if (isMyTurn == true) {
                        setOpponentChoice("12");
                        enableUnclickedButtons();
//                        printMessageFormServer();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void disableAllButtons() {
        buttons.forEach((k, v) -> k.setDisable(true));
    }

    private void setOpponentChoice(String buttonIndex) throws IOException {

        final String buttonId = "b" + buttonIndex;
        buttons.entrySet()
                .stream()
                .filter(e -> e.getKey().getId().equals(buttonId))
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(updateOpponentButton);
    }

    private void enableUnclickedButtons() {
        buttons.entrySet()
                .stream()
                .filter(e -> !e.getValue())
                .forEach(e -> e.getKey().setDisable(false));
    }

    private Consumer<Button> updateOpponentButton = (Button button) ->
            Platform.runLater(() -> {
                button.setText(oponentSign);
                button.setDisable(true);
                buttons.replace(button, true);
            });
}
