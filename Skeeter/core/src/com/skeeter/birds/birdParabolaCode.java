
public class birdParabolaCode {
    public static void main(String[] args) {

        double x = 800;
        double y = 100;
        double yVelocity = 5;
        double yAcceleration = 0.2;

        do {
            yVelocity = yVelocity - yAcceleration;
            y = y + yVelocity;
            x = x - 5;
            if (y < 100) {
                yVelocity = 5;
            }
            System.out.println("y = " + y + ", x = " + x);
        } while (x > 0);

    }
}
