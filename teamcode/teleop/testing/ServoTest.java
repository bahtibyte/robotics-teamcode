package org.firstinspires.ftc.teamcode.teleop.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.helper.misc.Timer;

@TeleOp(name = "Servo Test", group = "Teleop")
public class ServoTest extends OpMode
{

    private Servo botLeft;
    private Servo botRight;
    private Servo topLeft;
    private Servo topRight;
    private Servo jewelTop;
    private Servo jewelBottom;

    private Timer time;
    private double pos;
    private double pos2;

    @Override
    public void init()
    {
        botLeft = hardwareMap.get(Servo.class, "botLeft");
        botRight = hardwareMap.get(Servo.class, "botRight");
        topLeft = hardwareMap.get(Servo.class, "topLeft");
        topRight = hardwareMap.get(Servo.class, "topRight");
        jewelTop = hardwareMap.get(Servo.class, "jewelTop");
        jewelBottom = hardwareMap.get(Servo.class, "jewelBottom");

        time = new Timer();
        pos = 0.5;
        pos2 = 0.5;
    }

    @Override
    public void loop() {

        if (gamepad1.a && time.hasReached(50)){
            pos -= 0.01;
            time.reset();
        }else if (gamepad1.b && time.hasReached(50)){
            pos+= 0.01;
            time.reset();

        }
        if (gamepad1.x && time.hasReached(50))
        {
            pos2+= 0.01;
            time.reset();
        }
        else if (gamepad1.y && time.hasReached(50))
        {
            pos2 -= 0.01;
            time.reset();
        }

        //botLeft.setPosition(pos);
        //botRight.setPosition(Math.abs(1 -pos));
        //topLeft.setPosition(pos2);
        //topRight.setPosition(Math.abs(1 - pos2));
        jewelTop.setPosition(pos);
        jewelBottom.setPosition(pos2);

        log("Raw: "+pos);
        log("Actual Servo Left: "+botLeft.getPosition());
        log("Actual Servo Right: "+botRight.getPosition());
        log("Actual Servo Top Left: "+topLeft.getPosition());
        log("Actual Servo Top Right: "+topRight.getPosition());
        log("Actual Servo Jewel Bottom: "+ jewelBottom.getPosition());
        log("Actual Servo Jewel Top: "+ jewelTop.getPosition());

    }

    private boolean equals(double value,double target, double margin){
        return value >= target - margin && value <= target + margin;
    }
    public void log(String m){
        telemetry.addLine(m);
    }
}
