package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.helper.Pictograph;
import org.firstinspires.ftc.teamcode.helper.misc.Timer;

@Autonomous(name="Modified Pictograph Scan", group="Test")
public class MoifiedPictographScan extends LinearOpMode {

    private ModifiedPictograph pictograph;
    private Timer time;

    @Override
    public void runOpMode() throws InterruptedException {

        pictograph = new ModifiedPictograph(hardwareMap);
        time = new Timer();
        log("Ready to start");

        waitForStart();

        time.reset();

        if(!isStopRequested()){

            pictograph.activate();

            while (!isStopRequested()){

                pictograph.scan();

                RelicRecoveryVuMark mark = pictograph.getMark();

                log("Running for "+time.formattedTime()+" seconds\nScanning... Current: "+mark.toString());
            }
            pictograph.deactivate();
        }
    }

    private void log(String message){
        telemetry.addLine(message);
        telemetry.update();
    }
}
