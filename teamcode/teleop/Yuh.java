package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.helper.Constant;
import org.firstinspires.ftc.teamcode.helper.misc.Power;
import org.firstinspires.ftc.teamcode.helper.misc.Timer;
import org.firstinspires.ftc.teamcode.robot.Driver;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "Yuh", group = "Yuh")
public class Yuh extends OpMode {

    private Robot robot;
    private Driver driver;

    private Servo botLeft;
    private Servo botRight;

    private Servo jewelArmTop;
    private Servo jewelArmBottom;

    private Timer time;
    private double pos;

    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        robot.initWheels();
        robot.initClaw();

        jewelArmTop = hardwareMap.get(Servo.class,Constant.jewelArmTop);
        jewelArmTop.setPosition(0);

        jewelArmBottom= hardwareMap.get(Servo.class,Constant.jewelArmBottom);
        jewelArmBottom.setPosition(0.58);

        driver = new Driver(gamepad1);

        botLeft = hardwareMap.get(Servo.class, Constant.botLeftClaw);
        botRight = hardwareMap.get(Servo.class, Constant.botRightClaw);
        time = new Timer();
        //pos = 0.5;
    }

    public void loop() {

        telemetry.addLine("Reduction: " + Power.reduce + " " + (Power.reduce ? 0.25 : 1));

        robot.move(driver.getPower());

        modes();

        //servos();

        lift();

        telemetry.addLine("Claw Mode: " + robot.mode);
    }

    private void lift() {
        if (gamepad1.right_trigger != 0) {
            robot.lift.setPower(gamepad1.right_trigger * 1);
            telemetry.addLine("Lifting Lift (" + (gamepad1.right_trigger * 1) + ")");
        } else if (gamepad1.left_trigger != 0) {
            robot.lift.setPower(-gamepad1.left_trigger * 1);
            telemetry.addLine("Lowering Lift (" + (-gamepad1.left_trigger * 1) + ")");
        } else {
            robot.lift.setPower(0);
            telemetry.addLine("Lift is Still");
        }
    }

    private void servos()
    {
        if(gamepad1.a &&time.hasReached(50))

        {
            pos -= 0.01;
            time.reset();
        }else if(gamepad1.b &&time.hasReached(50))

        {
            pos += 0.01;
            time.reset();

        }

        //botLeft.setPosition(pos);
        botRight.setPosition(pos);

        log("Raw: "+pos);

        log("Actual Servo Left: "+botLeft.getPosition());

        log("Actual Servo Right: "+botRight.getPosition());
    }

    private void modes(){

        //GRAB
        if (gamepad1.a)
            robot.modeA();

        //DROP
        if (gamepad1.b)
            robot.modeB();

        //OPEN FULL
        if (gamepad1.x)
            robot.modeX();

        //CLOSE FULL
        if (gamepad1.y)
            robot.modeY();

        //INTERMEDIATE
        //if(gamepad1.dpad_up)
            //robot.modeX();
    }

    public void log(String m){
        telemetry.addLine(m);
    }
}
