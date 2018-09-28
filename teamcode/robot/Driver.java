package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.helper.misc.Power;

public class Driver {

    //A Useless class...

    public Gamepad gamepad;

    public Driver(Gamepad gamepad){
        this.gamepad = gamepad;
    }

    public Power getPower(){
        return new Power(gamepad.left_stick_x,gamepad.left_stick_y,gamepad.right_stick_x,gamepad.right_bumper);
    }
}