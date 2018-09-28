package org.firstinspires.ftc.teamcode.autonomous.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.helper.Pictograph;
import org.firstinspires.ftc.teamcode.helper.misc.Timer;

@Autonomous(name="Pictograph Scan", group="Test")
public class PictographScan extends LinearOpMode {

    private Pictograph pictograph;
    private Timer time;

    @Override
    public void runOpMode() throws InterruptedException {

        //Set's up the Pictograph Scanner
        //This usually takes 1-2 seconds
        //So wait for the "Ready to Start" text
        //before pressing play
        pictograph = new Pictograph(hardwareMap);

        time = new Timer();

        //Notify's the Driver that the phones are ready
        log("Ready to start");

        //Make sure to include this or auto would just not run
        waitForStart();

        if(!isStopRequested()){
            time.reset();

            //Don't forget to activate or it wont work
            pictograph.activate();

            //When ever you use while loop it is very IMPORTANT
            //to call !isStopRequested() as first condition always!
            while (!isStopRequested() && pictograph.getMark() == RelicRecoveryVuMark.UNKNOWN){

                log("Scanning..."+time.formattedTime()+" seconds");
                pictograph.scan();
            }

            //ALWAYS deactivate or you'll spend too much battery
            pictograph.deactivate();

            RelicRecoveryVuMark mark = pictograph.getMark();

            //Keeps the autonomous mode active so that the pictograph
            //Mark can be seen on the phone
            while (!isStopRequested()) {
                log("Finished scanning\nMark is: "+mark.toString());

            }

        }
    }

    //Fancy way of using telemtry.addLine()! Not required but this is my Style
    private void log(String message){
        telemetry.addLine(message);
        telemetry.update();
    }
}
