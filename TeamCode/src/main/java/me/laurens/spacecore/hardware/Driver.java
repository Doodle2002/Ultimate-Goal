package me.laurens.spacecore.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;

import me.laurens.spacecore.input.GamepadWrapper;
import me.laurens.spacecore.math.Vector2;

public class Driver {

    private HashMap<MotorID,DcMotor> motors = new HashMap<MotorID,DcMotor>();
    private GamepadWrapper driverController;
    public enum MotorID {FrontRight,BackRight,BackLeft,FrontLeft};

    public void SetMotor(MotorID id, DcMotor motor) {
        motors.put(id,motor);
    }

    public DcMotor GetMotor(MotorID id) {
        return motors.get(id);
    }

    public void SetDriverController(GamepadWrapper controller) {
        driverController = controller;
    }

    public GamepadWrapper GetDriverController() {
        return driverController;
    }

    public void Drive(Telemetry tel) {
        Vector2 axis = driverController.GetAxis(GamepadWrapper.Axis.STICK_L);

        axis.y = -axis.y;

        double joyR = driverController.GetAxis(GamepadWrapper.Axis.STICK_R).x;

        double angle = 0;
        double R = Range.clip(Math.sqrt(Math.pow(axis.x,2.0) + Math.pow(axis.y,2.0)),0,1);

        double Z = 0;
        double C = 0;
        double S = 0;

        if(!Double.isNaN(angle)) {
            if(R > 0) {
                S = Math.asin(Range.clip((axis.y / R),-1,1));
                C = Math.acos(Range.clip((axis.x / R),-1,1));
            }

            if(S > 0) {
                Z = 2 * Math.PI - C;
            } else {
                Z = C;
            }
        }

        double power = 0.7d + driverController.GetTrigger(GamepadWrapper.Trigger.TRIGGER_R)*0.3d;

        motors.get(MotorID.FrontLeft).setPower(-(Range.clip((Math.cos(Z+Math.PI / 4 - angle) * R + joyR) * power,-1,1)));
        motors.get(MotorID.FrontRight).setPower(-(Range.clip((Math.cos(Z+ 3 * Math.PI / 4 - angle) * R - joyR) * power,-1,1)));
        motors.get(MotorID.BackLeft).setPower(-(Range.clip((Math.cos(Z+ 3 * Math.PI / 4 - angle) * R + joyR) * power,-1,1)));
        motors.get(MotorID.BackRight).setPower(-(Range.clip((Math.cos(Z+Math.PI / 4 - angle) * R - joyR) * power,-1,1)));


    }

}
