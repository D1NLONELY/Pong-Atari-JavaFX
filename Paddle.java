import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 100;
    private static final int SPEED = 5;

    private double x;
    private double y;
    private boolean movingUp;
    private boolean movingDown;

    public Paddle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (movingUp) {
            y = Math.max(y - SPEED, 0);
        } else if (movingDown) {
            y = Math.min(y + SPEED, Main.getHeight() - HEIGHT);
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void setUp(boolean up) {
        this.movingUp = up;
    }

    public void setDown(boolean down) {
        this.movingDown = down;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return HEIGHT;
    }
}
