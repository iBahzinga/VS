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

/**
 * @author Daniel Dichte
 * @author Pascal Kießler
 *
 * GUI to start the application
 *
 */
public class GUIServerClient extends Application {

    private String font;
    private int fontSize;
    private int fontSizeHeader;
    private GUIClient writer;
    private GUIServer listener;

    /**
     * Constructor
     */
    public GUIServerClient() {
        this.font = "Arial";
        this.fontSize = 12;
        this.fontSizeHeader = 20;
    }

    /**
     *
     * @throws Exception
     */
    public void stop() throws Exception {
        System.out.println("Programm wurde beendet.");
    }

    /**
     *
     * @throws Exception
     */
    public void init() throws Exception {
        System.out.println("Programm wurde gestartet.");
    }

    @Override
    /**
     * @param
     */
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Distributed systems");
        StackPane pane = new StackPane();

        /* Set label */
        final Label headline = new Label("Server-Client");
        headline.setFont(new Font(font, fontSizeHeader));

        /* Buttons */
        Button writeButton = new Button();
        writeButton.setText("Redakteur");
        writeButton.setOnAction(actionWrite -> {
            Stage secondaryStage = new Stage();
            writer = new GUIClient(font, fontSize, fontSizeHeader, secondaryStage);
            try {
                writer.sending();
             } catch (Exception e) {
                System.err.println("Exception: " + e.toString());
                e.printStackTrace();
            }
        });

        Button listenButton = new Button();
        listenButton.setText("Lese");
        listenButton.setOnAction(sctionListen -> {
            Stage thirdStage = new Stage();
            listener = new GUIServer(font, fontSize, fontSizeHeader, thirdStage);
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
        vertical.getChildren().addAll(headline, buttons);
        vertical.setPadding(new Insets(15, 15, 15, 15));
        vertical.setSpacing(15);

        pane.getChildren().addAll(vertical);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
