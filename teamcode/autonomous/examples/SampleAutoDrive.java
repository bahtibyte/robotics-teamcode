package org.firstinspires.ftc.teamcode.autonomous.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.AutoRobot;

@Autonomous(name="Auto Drive", group="test")
public class SampleAutoDrive extends LinearOpMode {

    private AutoRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        //Creates the new Robot mainly for Autonomous
        //The Constructor is: HardwareMap, Telemtry, LinearOpMode, boolean
        //Since we are extending LinearOpMode, we can just put "this" for 3rd param
        //Keyword "this" is the reference to the class
        robot = new AutoRobot(hardwareMap,telemetry,this,false);

        //Sets up the claw's
        robot.initClaw();

        //Sets the wheels
        robot.initWheels();

        //Notify's the Driver that the phones are ready
        telemetry.addLine("Ready to start");
        telemetry.update();

        //Make sure to include this or auto would just not run
        waitForStart();

        if(!isStopRequested()){


            //DISTANCE OF 1000 = 1 FT
            //Sets the claw's position
           // robot.modeA();

            //Waits for 250 milliseconds so that the robot finish's grabbing
            //robot.waitFor(250);

            robot.driveForward(0.5,1000);

            /*
            robot.driveBackward(1, 3000);

            robot.driveForward(1,3000);

            robot.driveBackward(1, 3000);

            robot.driveForward(1,3000);

            robot.driveBackward(1, 3000);

            robot.driveForward(1,3000);

            robot.driveBackward(1, 3000);

            robot.driveForward(1,3000);

            robot.driveBackward(1, 3000);
            /*
            //2500 distance is 90 degree turn
            robot.rotateLeft(1, 2500);

            robot.driveForward(.25, 1000);

            robot.rotateRight(.5, 2500);

            robot.driveBackward(1, 2000);

            robot.rotateLeft(.75, 2500);

            robot.driveForward(1, 1000);

            robot.rotateRight(.35, 2500);

            */

            //After this finish's it should come back to its original spot
            //If not it means the robot's weight is uneven
        }
    }
}
