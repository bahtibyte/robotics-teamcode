package org.firstinspires.ftc.teamcode.teleop.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.helper.misc.Power;
import org.firstinspires.ftc.teamcode.robot.Driver;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "Strafe Test",group = "TeleOp")
public class EncoderTest extends OpMode{

    private Robot robot;
    private Driver driver;

    @Override
    public void init() {
        robot = new Robot(hardwareMap,telemetry);
        robot.initWheels();
        driver = new Driver(gamepad1);
    }

    @Override
    public void loop() {
        Power power = driver.getPower();

        telemetry.addLine("Motor Power Information");
        telemetry.addLine("Top Left  Power ~ "+format(power.topLeft));
        telemetry.addLine("Top Right Power ~ "+format(power.topRight));
        telemetry.addLine("Bot Left  Power ~ "+format(power.botLeft));
        telemetry.addLine("Bot Right Power ~ "+format(power.botRight));


        if (gamepad1.x){
            robot.topLeft.setPower(0.5);
            robot.topRight.setPower(-0.5);
            robot.botLeft.setPower(-0.5);
            robot.botRight.setPower(0.5);
        }
        else if (gamepad1.b){
            robot.topLeft.setPower(-0.5);
            robot.topRight.setPower(0.5);
            robot.botLeft.setPower(0.5);
            robot.botRight.setPower(-0.5);
        }
        else{
            robot.move(new Power(0,0,0,false));
        }



        telemetry.addLine("\nMotor Rotation Information");
        telemetry.addLine("Top Left  Value: "+robot.topLeft.getCurrentPosition());
        telemetry.addLine("Top Right Value: "+robot.topRight.getCurrentPosition());
        telemetry.addLine("Bot Left  Value: "+robot.botLeft.getCurrentPosition());
        telemetry.addLine("Bot Right Value: "+robot.botRight.getCurrentPosition());
    }

    private double format(double value){
        return (value * 100) / 100.0;
    }
}