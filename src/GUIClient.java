import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUIClient {

    private final String font;
    private final int fontSize;
    private final int fontSizeHeader;
    private GUIWriter writer;
    private GUIListener listener;
    private final Client client;

    /**
     * Constructor
     */
    public GUIClient (String font, int fontSize, int fontSizeHeader, Client client) {
        this.font = font;
        this.fontSize = fontSize;
        this.fontSizeHeader = fontSizeHeader;
        this.client = client;
    }

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    public void startClient(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Distributed systems");
        StackPane pane = new StackPane();

        /* Set label */
        final Label headline = new Label("Server-Client");
        headline.setFont(new Font(font, fontSizeHeader));

        /* Set 2nd label */
        final Label subheadline = new Label("Hallo " + client.getClientID() + " willkommen auf " + client.getServerP());
        headline.setFont(new Font(font, fontSize));

        /* Buttons */
        Button writeButton = new Button();
        writeButton.setText("Redakteur");
        writeButton.setOnAction(actionWrite -> {
            Stage secondaryStage = new Stage();
            writer = new GUIWriter(font, fontSize, fontSizeHeader, secondaryStage, client);
            try {
                writer.sending();
            } catch (Exception e) {
                System.err.println("Exception: " + e.toString());
                e.printStackTrace();
            }
        });

        Button listenButton = new Button();
        listenButton.setText("Rezeption");
        listenButton.setOnAction(sctionListen -> {
            Stage thirdStage = new Stage();
            listener = new GUIListener(font, fontSize, fontSizeHeader, thirdStage, client);
            try {
                listener.receiving();
            } catch (Exception e){
                System.err.println("Exception: " + e.toString());
                e.printStackTrace();
            }
        });

        /* Button exit */
        Button closeButton = new Button();
        closeButton.setText("Schließen");
        closeButton.setOnAction(closeProgramm -> {
            Platform.exit();
        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(writeButton, listenButton, closeButton);
        buttons.setSpacing(20);

        VBox vertical = new VBox();
        vertical.getChildren().addAll(headline, subheadline, buttons);
        vertical.setPadding(new Insets(15, 15, 15, 15));
        vertical.setSpacing(15);

        pane.getChildren().addAll(vertical);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
