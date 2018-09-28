package org.firstinspires.ftc.teamcode.autonomous;

import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.helper.Jewel;
import org.firstinspires.ftc.teamcode.helper.Pictograph;
import org.firstinspires.ftc.teamcode.helper.misc.Timer;
import org.firstinspires.ftc.teamcode.robot.AutoRobot;

@Autonomous(name = "Blue Large", group = "Auto")
public class BlueLarge extends LinearOpMode
{
    private AutoRobot robot;
    private Jewel jewel;
    private Pictograph pictograph;

    private Timer time;

    private JewelDetector.JewelOrder order = JewelDetector.JewelOrder.UNKNOWN;
    private RelicRecoveryVuMark mark = RelicRecoveryVuMark.UNKNOWN;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new AutoRobot(hardwareMap,telemetry,this,false);
        robot.initWheels();
        robot.initClaw();
        robot.initJewelArm();

        pictograph = new Pictograph(hardwareMap);

        jewel = new Jewel(hardwareMap);
        jewel.init();

        time = new Timer();

        telemetry.addLine("Ready to start");
        telemetry.update();

        waitForStart();

        if (!isStopRequested()){

            scanPictograph(1500);

            scanJewel(2500);

            jewelAndGlyph();

            placeGlyph();

            while (!isStopRequested()){

                telemetry.addLine("Finished Auto for Blue Large");
                telemetry.addLine("Jewel: " + order.toString());
                telemetry.addLine("Pictograph: " + mark.toString());
                telemetry.update();
            }
        }

    }

    private void grabGlyph(){
        robot.modeA();

        robot.waitFor(300);

        robot.liftMotor.setPower(0.25);

        robot.waitFor(500);

        robot.liftMotor.setPower(0);

        robot.waitFor(500);
    }

    private void placeGlyph(){

        //Re calibration
        //robot.driveBackward(.1, 200);

        int distance;

        /*
        if (mark == RelicRecoveryVuMark.LEFT)
            distance = 700;
        else if (mark == RelicRecoveryVuMark.CENTER)
            distance = 700;
        else if (mark == RelicRecoveryVuMark.RIGHT)
            distance = 700;
        else
        */
        //distance = 400;

        //robot.driveForward(.75,distance);

        //robot.waitFor(250);

        //robot.rotateRight(.35,2350);

        robot.rotateRight(0.50, 2140);

        robot.waitFor(250);

        if (mark == RelicRecoveryVuMark.LEFT)
            robot.driveForward(0.65, 850);
        else if (mark == RelicRecoveryVuMark.RIGHT)
            robot.driveForward(0.65, 2450);
        else
            robot.driveForward(.65,1450);

        robot.waitFor(300);

        robot.rotateLeft(0.50, 2240);

        robot.driveForward(.45,650);

        robot.waitFor(300);

        robot.modeB();

        robot.waitFor(300);

        robot.driveBackward(0.45, 300);

        robot.modeF();

        robot.driveForward(0.45, 400);

        robot.waitFor(300);

        robot.modeX();

        robot.driveBackward(0.45, 550);
    }


    private void knockJewel(){

        if (order != JewelDetector.JewelOrder.UNKNOWN) robot.lower();

        robot.waitFor(500);

        if (order == JewelDetector.JewelOrder.BLUE_RED){

            robot.driveForward(0.35, 1000);

            robot.raise();

            robot.waitFor(1000);

            robot.driveForward(.5, 950);


        }else if (order == JewelDetector.JewelOrder.RED_BLUE){


            robot.rotateLeft(0.35, 350);

            robot.waitFor(250);

            robot.raise();

            robot.waitFor(1000);

            robot.rotateRight(0.35, 350);

            robot.driveForward(.75, 1850);


        }else{
            robot.driveForward(.5,2000);
        }

    }

    private void jewelAndGlyph()
    {
        robot.modeA();

        robot.align();

        robot.waitFor(300);

        if (order != JewelDetector.JewelOrder.UNKNOWN)
        {
            robot.lower();

            robot.waitFor(300);

            robot.liftMotor.setPower(0.35);

            robot.waitFor(200);

            if (order == JewelDetector.JewelOrder.RED_BLUE) robot.hitLeft();

            else robot.hitRight();

            robot.waitFor(300);

            robot.liftMotor.setPower(0);

            robot.raise();

            robot.waitFor(250);

            robot.driveForward(.75,2100);

        }
        else
        {
            robot.waitFor(300);

            robot.liftMotor.setPower(0.30);

            robot.waitFor(500);

            robot.liftMotor.setPower(0);

            robot.driveForward(.75,2100);
        }

        robot.waitFor(300);
    }

    private void scanJewel(long millis){

        time.reset();

        jewel.enable();

        while(!isStopRequested() && !time.hasReached(millis) && jewel.getJewel() == JewelDetector.JewelOrder.UNKNOWN){
            telemetry.addLine("Scanning for Jewels ("+time.formattedTime()+" seconds)");
            telemetry.update();
        }

        order = jewel.getJewel();

        jewel.disable();
    }

    private void scanPictograph(long millis){
        time.reset();

        pictograph.activate();

        while (!isStopRequested() && !time.hasReached(millis) && mark == RelicRecoveryVuMark.UNKNOWN){

            log("Scanning Vuforia..."+time.formattedTime());

            pictograph.scan();
        }

        mark = pictograph.getMark();

        pictograph.deactivate();
    }


    private void log(String m){
        telemetry.addLine(m);
        telemetry.update();
    }
}


