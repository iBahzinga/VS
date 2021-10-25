import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUIServer {

    private String font;
    private int fontSize;
    private int fontSizeHeader;
    private Stage stage;

    public GUIServer(String font, int fontSize, int fontSizeHeader, Stage stage) {
        this.font = font;
        this.fontSize = fontSize;
        this. fontSizeHeader = fontSizeHeader;
        this.stage = stage;
    }

    /**
     *
     * @throws Exception
     */
    protected void receiving() throws Exception {
        StackPane pane = new StackPane();

        /* Set label */
        final Label headline = new Label("Server");
        headline.setFont(new Font(font, fontSizeHeader));


        /* Textfield */
        TextArea receivedText = new TextArea();
        receivedText.setPrefWidth(350);
        receivedText.setPrefHeight(200);
        receivedText.setEditable(false);


        Button restartButton = new Button();
        restartButton.setText("Update ");
        restartButton.setOnAction(restart ->
        {
            System.out.println( "Restarting Server app!" );
            stage.close();
            Platform.runLater( () -> {
                try {
                    new GUIServer(font, fontSize, fontSizeHeader, new Stage()).receiving();
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