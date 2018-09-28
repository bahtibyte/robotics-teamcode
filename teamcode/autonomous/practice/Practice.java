package org.firstinspires.ftc.teamcode.autonomous.practice;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.AutoRobot;

@Autonomous(name="Practice", group="test")
/**
 * Created by faizanahmed on 1/16/18.
 */

public class Practice extends LinearOpMode {
    private AutoRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new AutoRobot(hardwareMap, telemetry, this, false);

        robot.initWheels();

        telemetry.addLine("Ready to start");
        telemetry.update();

        waitForStart();

        if (!isStopRequested()) {
            robot.driveForward(1, 1500);

            robot.rotateLeft(1, 1250);

            robot.driveForward(1, 1000);

            robot.rotateRight(1, 2550);

            /*

            robot.driveForward(1, 2500);

            robot.rotateLeft(1, 1250);

            robot.driveForward(1, 4000);

            robot.rotateLeft(1, 7500);

            robot.driveForward(1, 2500);

            robot.waitFor(2000);

            robot.rotateLeft(1, 2500);

            robot.driveForward(1, 1000);

            */
        }
    }
}
