package org.firstinspires.ftc.teamcode.teleop.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Driver;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "MoveTest",group = "TeleOp")
public class MoveTest extends OpMode{

    private Robot robot;
    private Driver driver;

    private ElapsedTime time;

    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        robot.initWheels();
        robot.initClaw();

        driver = new Driver(gamepad1);

        time = new ElapsedTime();
    }

    public void start(){
        time.reset();
    }

    public void loop() {
        log("Running for: "+(time.milliseconds()/1000));

        move();
    }

    private void move(){
        try {
            log("Moving The Robot");
            robot.move(driver.getPower());
            robot.printPower();
        }catch (Exception e){
            log("Error When Moving Robot");
            log(e);
        }
    }

    private void log(String m){
        telemetry.addLine(m);
    }

    //Prints the error on the phone, easier to debug
    private void log(Exception e){
        log(e.toString());
        for (StackTraceElement a : e.getStackTrace())
            log(a.toString());
        log("");
    }
}
