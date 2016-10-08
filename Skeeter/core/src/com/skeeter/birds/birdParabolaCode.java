
public class birdParabolaCode {
    public void doMath() {


    double x = 800;
    double y = 100;
    double yVelocity = 5;
    double yAcceleration = 0.2;

        do

    {
        yVelocity = yVelocity - yAcceleration;
        y = y + yVelocity;
        x = x - 5;
        if (y < 100) {
            yVelocity = 5;
        }
    } while(x >0);
    }
}
