
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
//import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ChatInterface extends Application {

    private VBox chatArea = new VBox(12); // spacing between messages
    private ChatController controller = new ChatController();

    @Override
    public void start(Stage stage) {

        // ========================
        // StackPane for chat & background
        // ========================
        StackPane chatLayer = new StackPane();

        // Background image
        ImageView bgView = new ImageView(
                new Image(getClass().getResource("/Resources/bus.png").toExternalForm())
        );
        bgView.setPreserveRatio(false);
        bgView.setSmooth(true); 
        //bgView.setEffect(new GaussianBlur(15));
        bgView.fitWidthProperty().bind(chatLayer.widthProperty());
        bgView.fitHeightProperty().bind(chatLayer.heightProperty());

        // Dim overlay
        Region dimOverlay = new Region();
        dimOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.35);");
        dimOverlay.prefWidthProperty().bind(chatLayer.widthProperty());
        dimOverlay.prefHeightProperty().bind(chatLayer.heightProperty());

        // ScrollPane for messages
        ScrollPane scrollPane = new ScrollPane(chatArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        chatArea.setPadding(new Insets(10));

        // ========================
        // Floating TextField input
        // ========================
        TextField input = new TextField();
        input.setPromptText("Type your message...");
        input.setFont(Font.font("Segoe UI", 25));
        input.setStyle("""
            -fx-background-color: rgba(144,238,144,0.9);
            -fx-background-radius: 30;
            -fx-padding: 15 12;
            -fx-border-radius: 20;
            -fx-border-color: transparent;
            -fx-text-fill: #0000ff;
        """);
        input.setMaxWidth(400); // Optional: restrict width

        input.setOnAction(e -> {
            String text = input.getText().trim();
            if (!text.isEmpty()) {
                addUserMessage(text);
                String reply = controller.handleUserInput(text);
                addBotMessage(reply);
                input.clear();
                scrollPane.setVvalue(1.0); // auto-scroll to bottom
            }
        });

        // Position input at bottom of StackPane
        StackPane.setAlignment(input, Pos.BOTTOM_CENTER);
        StackPane.setMargin(input, new Insets(0, 0, 20, 0));

        // ========================
        // Add layers to StackPane
        // ========================
        chatLayer.getChildren().addAll(bgView, dimOverlay, scrollPane, input);

        // ========================
        // Root layout
        // ========================
        VBox root = new VBox(chatLayer);
        VBox.setVgrow(chatLayer, Priority.ALWAYS);

        Scene scene = new Scene(root, 600, 600);
        stage.setTitle(" Route Finder 💬");
        stage.setScene(scene);
        stage.show();

        // Start conversation
        addBotMessage("👋 Hello! What’s your name?");
    }

    // ========================
    // Methods for chat bubbles
    // ========================
    private void addUserMessage(String text) {
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setPadding(new Insets(5, 0, 5, 0));

        Text msg = new Text(text);
        msg.setFill(Color.WHITE);
        msg.setFont(Font.font("Segoe UI", 25));

        StackPane bubble = new StackPane(msg);
        bubble.setStyle("""
            -fx-background-color: linear-gradient(to right, #0084ff, #00b7ff);
            -fx-background-radius: 30;
            -fx-padding: 15 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 6, 0, 0, 2);
        """);

        box.getChildren().add(bubble);
        chatArea.getChildren().add(box);
    }

    private void addBotMessage(String text) {
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(5, 0, 5, 0));

        Text msg = new Text(text);
        msg.setFill(Color.WHITE);
        msg.setFont(Font.font("Segoe UI", 14));

        StackPane bubble = new StackPane(msg);
        bubble.setStyle("""
            -fx-background-color: linear-gradient(to bottom right, #444, #222);
            -fx-background-radius: 18;
            -fx-padding: 15 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 6, 0, 0, 2);
        """);

        box.getChildren().add(bubble);
        chatArea.getChildren().add(box);
    }

    public static void main(String[] args) {
        launch();
    }
}
