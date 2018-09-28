package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.helper.Constant;
import org.firstinspires.ftc.teamcode.helper.misc.Timer;

public class AutoRobot {

    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    private final LinearOpMode opMode;

    private DcMotor topLeftMotor;
    private DcMotor topRightMotor;
    private DcMotor botLeftMotor;
    private DcMotor botRightMotor;
    public DcMotor liftMotor;
    private Servo topLeftClaw;
    private Servo topRightClaw;
    private Servo botLeftClaw;
    private Servo botRightClaw;
    private Servo jewelArmTop;
    private Servo jewelArmBottom;

    private String mode = "B";

    public Timer time;

    private boolean debug;

    public AutoRobot(HardwareMap hardwareMap, Telemetry telemetry, LinearOpMode opMode, boolean debug){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.opMode = opMode;
        this.debug = debug;

        time = new Timer();
    }

    public void waitFor(long millis){
        time.reset();
        while(!opMode.isStopRequested() && !time.hasReached(millis)){}
    }

    public void initWheels(){
        log("> Initializing Wheels");
        try{
            topLeftMotor = get(Constant.topLeftMotor);
            topLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            topLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            topLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            log("Top Left ~ Ready");
        }catch (Exception e){
            log("Top Left ~ Error ("+ topLeftMotor +")");
        }
        try{
            topRightMotor = get(Constant.topRightMotor);
            topRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            topRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            topRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            log("Top Right ~ Ready");
        }catch (Exception e){
            log("Top Right ~ Error ("+ topRightMotor +")");
        }
        try{
            botLeftMotor = get(Constant.botLeftMotor);
            botLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            botLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            botLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            log("Bot Left ~ Ready");
        }catch (Exception e){
            log("Bot Left ~ Error ("+ botLeftMotor +")");
        }
        try{
            botRightMotor = get(Constant.botRightMotor);
            botRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            botRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            botRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            log("Bot Right ~ Ready");
        }catch (Exception e){
            log("Bot Right ~ Error ("+ botRightMotor +")");
        }

        log("> Finished initializing Wheels\n");
    }

    public void initJewelArm(){
        try{
            jewelArmTop = hardwareMap.get(Servo.class,Constant.jewelArmTop);
            jewelArmTop.setPosition(0);
            log("Bot Jewel Arm ~ Ready");
        }catch (Exception e){
            log("Bot Jewel Arm ` Error ("+jewelArmTop+")");
        }

        try{
            jewelArmBottom= hardwareMap.get(Servo.class,Constant.jewelArmBottom);
            jewelArmBottom.setPosition(0.3);
            log("Bot Jewel Arm Bottom ~ Ready");
        }catch (Exception e){
            log("Bot Jewel Arm Bottom ` Error ("+jewelArmBottom+")");
        }
    }

    public void align()
    {
        jewelArmTop.setPosition(0.01);
        jewelArmBottom.setPosition(0.57);
    }


    public void raise(){
        jewelArmTop.setPosition(0);
    }

    public void lower(){
        jewelArmTop.setPosition(0.86);
    }

    public void hitLeft() {jewelArmBottom.setPosition(0.38); }

    public void hitRight() {jewelArmBottom.setPosition(0.78); }

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
            liftMotor = get(Constant.lift);
            liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            log("Lift Motor ~ Ready");
        }catch (Exception e){
            log("Lift Motor ~ Error ("+ liftMotor +")");
        }
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

    public void modeO(){
        try{
            mode = "O";
            topLeftClaw.setPosition (Constant.xtl);
            topRightClaw.setPosition(Constant.xtr);
            botLeftClaw.setPosition (1);
            botRightClaw.setPosition(0);
        }catch (Exception e){
            mode = "Crashed O\n"+e.toString();
        }
    }


    public void setMotorMode(DcMotor.RunMode mode){
        topLeftMotor.setMode(mode);
        topRightMotor.setMode(mode);
        botLeftMotor.setMode(mode);
        botRightMotor.setMode(mode);
    }

    public void closeClaw(){
        botLeftClaw.setPosition(Constant.lbbl);
        botRightClaw.setPosition(Constant.lbbr);
        topLeftClaw.setPosition(Constant.lblr);
        topRightClaw.setPosition(Constant.lbtr);
    }

    private void drive(double power, int distance, boolean tl, boolean tr, boolean bl, boolean br){
        topLeftMotor.setTargetPosition(topLeftMotor.getCurrentPosition() + (tl ? distance : -distance));
        topRightMotor.setTargetPosition(topRightMotor.getCurrentPosition() + (tr ? distance : -distance));
        botLeftMotor.setTargetPosition(botLeftMotor.getCurrentPosition() + (bl ? distance : -distance));
        botRightMotor.setTargetPosition(botRightMotor.getCurrentPosition() + (br ? distance : -distance));

        while(!opMode.isStopRequested() && isMotorsBusy()){
            topLeftMotor.setPower(tl ? power : -power);
            topRightMotor.setPower(tr ? power : -power);
            botLeftMotor.setPower(bl ? power : -power);
            botRightMotor.setPower(br ? power : -power);
        }

        topLeftMotor.setPower(0);
        topRightMotor.setPower(0);
        botLeftMotor.setPower(0);
        botRightMotor.setPower(0);
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void driveForward(double power, int distance){
        drive(power,distance,true,true,true,true);
    }

    public void rotateRight(double power, int distance){
        drive(power,distance,true,false,true,false);
    }

    public void driveBackward(double power, int distance){
        drive(power,distance,false,false,false,false);
    }

    public void rotateLeft(double power, int distance){
        drive(power,distance,false,true,false,true);
    }

    private boolean isMotorsBusy(){
        return topLeftMotor.isBusy() && topRightMotor.isBusy() && botLeftMotor.isBusy() && botRightMotor.isBusy();
    }

    private DcMotor get(String name){ return hardwareMap.get(DcMotor.class,name); }

    private void log(String message){
        if (debug) telemetry.addLine(message);
    }

    //FULLY CLOSE TO PUSH IN GLYPH
    public void modeF(){
        try{
            mode = "F";
            botLeftClaw.setPosition (Constant.abl);
            botRightClaw.setPosition(Constant.abr);
            topLeftClaw.setPosition(Constant.atl);
            topRightClaw.setPosition(Constant.atr);
        }catch (Exception e){
            mode = "Crashed F\n"+e.toString();
        }
    }

    public void modeS(){
        try{
            mode = "S";
            botLeftClaw.setPosition (Constant.sbl);
            botRightClaw.setPosition(Constant.sbr);
            topLeftClaw.setPosition(Constant.stl);
            topRightClaw.setPosition(Constant.str);
        }catch (Exception e){
            mode = "Crashed S\n"+e.toString();
        }
    }
}