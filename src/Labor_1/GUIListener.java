package Labor_1;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *  * @author Daniel Dichte
 *  * @author Pascal Kießler
 *
 */
public class GUIListener {

    private String font;
    private int fontSize;
    private int fontSizeHeader;
    private Stage stage;
    private Client client;

    public GUIListener(String font, int fontSize, int fontSizeHeader, Stage stage, Client client) {
        this.font = font;
        this.fontSize = fontSize;
        this. fontSizeHeader = fontSizeHeader;
        this.stage = stage;
        this.client = client;
    }

    /**
     *
     * @throws Exception
     */
    protected void receiving() throws Exception {
        StackPane pane = new StackPane();
        stage.setTitle("Distributed systems");

        /* Set label */
        final Label headline = new Label("Client-Rezeption");
        headline.setFont(new Font(font, fontSizeHeader));

        /* Textfield */
        TextArea receivedText = new TextArea();
        receivedText.setPrefWidth(350);
        receivedText.setPrefHeight(100);
        receivedText.setEditable(false);
        receivedText.setText (client.nextMessage(client.getClientID()));

        Button restartButton = new Button();
        restartButton.setText("Update ");
        restartButton.setOnAction(restart ->
        {
            System.out.println( "Restarting Rezeption Client!" );
            stage.close();
            Platform.runLater( () -> {
                try {
                    new GUIListener(font, fontSize, fontSizeHeader, new Stage(), client).receiving();
                } catch (Exception e) {
                    System.err.println("Exception: " + e.toString());
                    e.printStackTrace();
                }
            });
        });

        /* Button exit */
        Button closeButton = new Button();
        closeButton.setText("Schließen");
        closeButton.setOnAction(closeProgramm -> {
            stage.close();
        });

        /* Horizontal box (Label) */
        HBox label = new HBox();
        label.getChildren().addAll(headline);

        /* Horizontal box (Text field) */
        HBox textField = new HBox();
        textField.getChildren().addAll(receivedText);

        /* Horizontal box (buttons) */
        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.getChildren().addAll(restartButton, closeButton);

        /* vertical box */
        VBox box = new VBox();
        box.setSpacing(20);
        box.setPadding(new Insets(15, 15, 15, 15));
        box.getChildren().addAll(label, textField, buttons);

        /* Display the Client form */
        pane.getChildren().addAll(box);
        stage.setScene(new Scene(pane));
        stage.show();

    }
}