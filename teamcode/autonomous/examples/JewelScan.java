package org.firstinspires.ftc.teamcode.autonomous.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.helper.Jewel;

@Autonomous(name="Jewel Scan", group="Test")
public class JewelScan extends LinearOpMode {

    private Jewel jewel;

    @Override
    public void runOpMode() throws InterruptedException {

        //Creates a Jewel Scanner
        jewel = new Jewel(hardwareMap);

        //Make sure to call this method to set up the scanner
        jewel.init();

        //Wait for this message to come up before pressing play
        telemetry.addLine("Ready to start");
        telemetry.update();

        //Don't forget to include this, or auto wont run
        waitForStart();

        if(!isStopRequested()){

            //Enables the scanner. Must be called after pressing play
            jewel.enable();

            //When ever you use while loop it is very IMPORTANT
            //to call !isStopRequested() as first condition always!
            while (!isStopRequested()){

                //jewel.getJewel() is a JewelOrder enum. you can compare using ==
                telemetry.addLine(jewel.getJewel().toString());
                telemetry.update();
            }

            //ALWAYS deactivate or you'll spend too much battery
            jewel.disable();
        }
    }
}
