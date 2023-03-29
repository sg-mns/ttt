import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane border = new BorderPane();
        Board board = new Board();
        FlowPane grid = board.getBoard();
        border.setCenter(grid);

        Scene scene = new Scene(border, 650, 800);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void restart() {

        Game game = new Game();
        try {
            game.start(new Stage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        launch(args);
    }

}
