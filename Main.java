import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        leftPaddle = new Paddle(50, HEIGHT / 2 - 50);
        rightPaddle = new Paddle(WIDTH - 70, HEIGHT / 2 - 50);
        ball = new Ball(WIDTH / 2, HEIGHT / 2);
        
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            update();
            render(gc);
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        
        Scene scene = new Scene(new javafx.scene.layout.StackPane(canvas));
        
        scene.setOnKeyPressed(this::handleKeyPress);
        scene.setOnKeyReleased(this::handleKeyRelease);
        
        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        gameLoop.play();
    }

    private void update() {
        ball.move();
        ball.checkCollision(leftPaddle, rightPaddle);
        leftPaddle.move();
        rightPaddle.move();
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        
        leftPaddle.render(gc);
        rightPaddle.render(gc);
        
        ball.render(gc);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.W) {
            leftPaddle.setUp(true);
        } else if (event.getCode() == KeyCode.S) {
            leftPaddle.setDown(true);
        }

        if (event.getCode() == KeyCode.UP) {
            rightPaddle.setUp(true);
        } else if (event.getCode() == KeyCode.DOWN) {
            rightPaddle.setDown(true);
        }
    }

    private void handleKeyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.W) {
            leftPaddle.setUp(false);
        } else if (event.getCode() == KeyCode.S) {
            leftPaddle.setDown(false);
        }

        if (event.getCode() == KeyCode.UP) {
            rightPaddle.setUp(false);
        } else if (event.getCode() == KeyCode.DOWN) {
            rightPaddle.setDown(false);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
