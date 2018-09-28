package org.firstinspires.ftc.teamcode.helper.misc;

public class Power {

    public final double topLeft;
    public final double topRight;
    public final double botLeft;
    public final double botRight;

    public static boolean reduce = false;
    private static Timer time = new Timer();

    private final double buffer = 0.2;

    public Power(double lX, double lY, double rX, boolean right){
        double vD = Math.min( Math.sqrt(Math.pow(lX,2)) + Math.pow(lY,2) ,1);
        double theta = -(Math.atan2(-lX,-lY)) + Math.PI / 4;

        if (right && time.hasReached(250)) {
            reduce = !reduce;
            time.reset();
        }
        double reduction;

        if (lX != 0 && lY < buffer && lY > -buffer){

            if (reduce) reduction = 0.5;
            else reduction = 1;

            topLeft = lX * reduction;
            topRight = -lX * reduction;
            botLeft = -lX * reduction;
            botRight = lX * reduction;

        }else {

            if (reduce) reduction = 0.35;
            else reduction = 1;

            topLeft = (vD * Math.sin(theta) + rX) * reduction;
            topRight = (vD * Math.cos(theta) - rX) * reduction;
            botLeft = (vD * Math.cos(theta) + rX) * reduction;
            botRight = (vD * Math.sin(theta) - rX) * reduction;
        }
    }
}

