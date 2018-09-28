package org.firstinspires.ftc.teamcode.teleop.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Driver;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "Mode Test",group = "TeleOp")
public class ModeTest extends OpMode{

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

        if (gamepad1.a)
            robot.modeA();

        if (gamepad1.b)
            robot.modeB();

        if (gamepad1.x)
            robot.modeX();

        if (gamepad1.y)
            robot.modeY();

    }

    private void log(String m){
        telemetry.addLine(m);
    }
}
