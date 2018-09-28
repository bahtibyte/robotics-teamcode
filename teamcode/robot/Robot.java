package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.helper.Constant;
import org.firstinspires.ftc.teamcode.helper.misc.Power;

public class Robot {

    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;

    public DcMotor topRight = null;
    public DcMotor topLeft = null;
    public DcMotor botRight = null;
    public DcMotor botLeft = null;

    public DcMotor lift = null;

    public Servo topLeftClaw;
    public Servo topRightClaw;
    public Servo botLeftClaw;
    public Servo botRightClaw;

    public String mode = "B";

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }

    public void initWheels(){
        log("\n***Initializing The Wheels***");
        try{
            topLeft = get(Constant.topLeftMotor);
            topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            topLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            topLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            log("Top Left ~ Ready");
        }catch (Exception e){
            log("Top Left ~ Error ("+topLeft+")");
        }

        try{
            topRight = get(Constant.topRightMotor);
            topRight.setDirection(DcMotorSimple.Direction.FORWARD);
            topRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            topRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            log("Top Right ~ Ready");
        }catch (Exception e){
            log("Top Right ~ Error ("+topRight+")");
        }
        try{
            botLeft = get(Constant.botLeftMotor);
            botLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            botLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            botLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            log("Bot Left ~ Ready");
        }catch (Exception e){
            log("Bot Left ~ Error ("+botLeft+")");
        }
        try{
            botRight = get(Constant.botRightMotor);
            botRight.setDirection(DcMotorSimple.Direction.FORWARD);
            botRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            botRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            log("Bot Right ~ Ready");
        }catch (Exception e){
            log("Bot Right ~ Error ("+botRight+")");
        }
    }

    public void initClaw(){
        log("\nInitializing The Claw");

        try{
            topLeftClaw = hardwareMap.get(Servo.class,Constant.topLeftClaw);
            topLeftClaw.setPosition(Constant.xtl);
            log("Left Claw ~ Ready");
        }catch (Exception e){
            log("Left Claw ` Error ("+topLeftClaw+")");
        }

        try{
            topRightClaw = hardwareMap.get(Servo.class,Constant.topRightClaw);
            topRightClaw.setPosition(Constant.xtr);
            log("Top Right Claw ~ Ready");
        }catch (Exception e){
            log("Top Right Claw ` Error ("+topRightClaw+")");
        }

        try{
            botLeftClaw = hardwareMap.get(Servo.class,Constant.botLeftClaw);
            botLeftClaw.setPosition(Constant.xbl);
            log("Bot Left Claw ~ Ready");
        }catch (Exception e){
            log("Bot Left Claw ` Error ("+botLeftClaw+")");
        }

        try{
            botRightClaw = hardwareMap.get(Servo.class,Constant.botRightClaw);
            botRightClaw.setPosition(Constant.xbr);
            log("Bot Right Claw ~ Ready");
        }catch (Exception e){
            log("Bot Right Claw ` Error ("+botRightClaw+")");
        }

        try{
            lift = get(Constant.lift);
            lift.setDirection(DcMotorSimple.Direction.REVERSE);
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            log("Lift Motor ~ Ready");
        }catch (Exception e){
            log("Lift Motor ~ Error ("+lift+")");
        }
    }

    public void move(Power power){
        topLeft.setPower(power.topLeft);
        topRight.setPower(power.topRight);
        botLeft.setPower(power.botLeft);
        botRight.setPower(power.botRight);
    }

    public void setMotorMode(DcMotor.RunMode mode)
    {
        topLeft.setMode(mode);
        topRight.setMode(mode);
        botLeft.setMode(mode);
        botRight.setMode(mode);
    }

    public void printPower(){
        log("Top Left  Power ~ "+topLeft.getPower());
        log("Top Right Power ~ "+topRight.getPower());
        log("Bot Left  Power ~ "+botLeft.getPower());
        log("Bot Right Power ~ "+botRight.getPower());
    }

    public void modeA(){
        try{
            mode = "A";
            topLeftClaw.setPosition (Constant.atl);
            topRightClaw.setPosition(Constant.atr);
            botLeftClaw.setPosition (Constant.abl);
            botRightClaw.setPosition(Constant.abr);
        }catch (Exception e){
            mode = "Crashed A\n"+e.toString();
        }
    }

    public void modeB(){
        try{
            mode = "B";
            topLeftClaw.setPosition (Constant.btl);
            topRightClaw.setPosition(Constant.btr);
            botLeftClaw.setPosition (Constant.bbl);
            botRightClaw.setPosition(Constant.bbr);
        }catch (Exception e){
            mode = "Crashed B\n"+e.toString();
        }
    }

    public void modeX(){
        try{
            mode = "X";
            topLeftClaw.setPosition (Constant.xtl);
            topRightClaw.setPosition(Constant.xtr);
            botLeftClaw.setPosition (Constant.xbl);
            botRightClaw.setPosition(Constant.xbr);
        }catch (Exception e){
            mode = "Crashed X\n"+e.toString();
        }
    }

    public void modeY(){
        try{
            mode = "Y";
            topLeftClaw.setPosition (Constant.ytl);
            topRightClaw.setPosition(Constant.ytr);
            botLeftClaw.setPosition (Constant.ybl);
            botRightClaw.setPosition(Constant.ybr);
        }catch (Exception e){
            mode = "Crashed Y\n"+e.toString();
        }
    }

    public void modeS(){
        try{
            mode = "S";
            topLeftClaw.setPosition (Constant.stl);
            topRightClaw.setPosition(Constant.str);
            botLeftClaw.setPosition (Constant.sbl);
            botRightClaw.setPosition(Constant.sbr);
        }catch (Exception e){
            mode = "Crashed S\n"+e.toString();
        }
    }


    private DcMotor get(String name){ return hardwareMap.get(DcMotor.class,name); }
    public void log(String m){
        telemetry.addLine(m);
    }
}