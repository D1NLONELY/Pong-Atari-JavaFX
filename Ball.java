import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
    private static final int RADIUS = 10;
    private double x, y;
    private double xSpeed = 3;
    private double ySpeed = 3;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
        
        if (y <= 0 || y >= Main.getHeight() - RADIUS) {
            ySpeed *= -1;
        }
    }

    public void checkCollision(Paddle leftPaddle, Paddle rightPaddle) {
        if (x <= leftPaddle.getX() + 20 && y >= leftPaddle.getY() && y <= leftPaddle.getY() + 100) {
            xSpeed *= -1;
        }

        if (x >= rightPaddle.getX() - 20 && y >= rightPaddle.getY() && y <= rightPaddle.getY() + 100) {
            xSpeed *= -1;
        }

        if (x <= 0 || x >= Main.getWidth()) {
            x = Main.getWidth() / 2;
            y = Main.getHeight() / 2;
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillOval(x, y, RADIUS, RADIUS);
    }
}
