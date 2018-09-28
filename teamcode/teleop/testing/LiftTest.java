package org.firstinspires.ftc.teamcode.teleop.testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "Lift Test",group = "TeleOp")
public class LiftTest extends OpMode{

    private Robot robot;

    private ElapsedTime time;

    public void init() {
        robot = new Robot(hardwareMap, telemetry);
        robot.initClaw();

        time = new ElapsedTime();
    }

    public void start(){
        time.reset();
    }

    public void loop() {
        log("Running for: "+(time.milliseconds()/1000));

        lift();
    }

    private void lift(){
        if (gamepad1.right_trigger != 0){
            robot.lift.setPower(gamepad1.right_trigger * .5);
            telemetry.addLine("Lifting Lift ("+(gamepad1.right_trigger * .5)+")");
        }else if (gamepad1.left_trigger != 0){
            robot.lift.setPower(-gamepad1.left_trigger * .5);
            telemetry.addLine("Lowering Lift ("+(-gamepad1.left_trigger * .5)+")");
        }else{
            robot.lift.setPower(0);
            telemetry.addLine("Lift is Still");
        }

        telemetry.addLine("RAW POWER " + robot.lift.getPower());
    }

    private void log(String m){
        telemetry.addLine(m);
    }
}
