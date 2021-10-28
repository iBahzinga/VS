import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.rmi.RemoteException;

/**
 * * @author Daniel Dichte
 *  * @author Pascal Kießler
 */
public class GUIWriter {

    private final String font;
    private final int fontSize;
    private final int fontSizeHeader;
    private final Stage stage;
    private final Client client;

    /**
     * Constructor
     * @param font
     * @param fontSize
     * @param fontSizeHeader
     * @param stage
     */
    public GUIWriter(String font, int fontSize, int fontSizeHeader, Stage stage, Client client){
        this.font = font;
        this.fontSize = fontSize;
        this.fontSizeHeader = fontSizeHeader;
        this.stage = stage;
        this.client = client;
    }

    /**
     * Configuration of the form "Client"
     * @throws Exception
     */
    public void sending() throws Exception {
        StackPane pane = new StackPane();
        stage.setTitle("Distributed systems");

        /* Set label */
        final Label headline = new Label("Client-Redaktion");
        headline.setFont(new Font(font, fontSizeHeader));

        /* Textfield */
        TextField writeField = new TextField();
        writeField.setPrefWidth(350);
        writeField.setPromptText("Geben Sie hier ihren Text ein");

        /* Button senden */
        Button button_send = new Button();
        button_send.setText("Senden");
        button_send.setOnAction(click -> {
            try {
                client.newMessage(client.getClientID(), writeField.getText());
            } catch (RemoteException e) {
                e.printStackTrace();
                System.err.println("Client exception: " + e.toString());
            }
        });

        /* Button clear */
        Button button_clear = new Button();
        button_clear.setText("Clear");
        button_clear.setOnAction(clear -> {
            writeField.clear();
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
        textField.getChildren().addAll(writeField);

        /* Horizontal box (buttons) */
        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.getChildren().addAll(button_send, button_clear, closeButton);

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