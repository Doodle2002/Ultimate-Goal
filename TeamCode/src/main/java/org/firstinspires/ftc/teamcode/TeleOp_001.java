package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import me.laurens.spacecore.hardware.Driver;
import me.laurens.spacecore.input.GamepadWrapper;
import me.laurens.spacecore.math.Vector2;

@TeleOp(name="TeleOp-001", group="Driver Controlled")
public class TeleOp_001 extends OpMode
{
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    DcMotor backLeftMotor;
    DcMotor frontLeftMotor;

    GamepadWrapper gp1;
    Driver driver;
    @Override
    public void init() {

        frontRightMotor = hardwareMap.get(DcMotor.class,"motor-front-right");
        backRightMotor = hardwareMap.get(DcMotor.class,"motor-back-right");
        backLeftMotor = hardwareMap.get(DcMotor.class,"motor-back-left");
        frontLeftMotor = hardwareMap.get(DcMotor.class,"motor-front-left");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        gp1 = new GamepadWrapper(gamepad1);

        driver = new Driver();

        driver.SetDriverController(gp1);

        driver.SetMotor(Driver.MotorID.BackLeft,backLeftMotor);
        driver.SetMotor(Driver.MotorID.BackRight,backRightMotor);
        driver.SetMotor(Driver.MotorID.FrontLeft,frontLeftMotor);
        driver.SetMotor(Driver.MotorID.FrontRight,frontRightMotor);
    }

    @Override
    public void start() {
        /*frontRightMotor.setPower(1);
        backRightMotor.setPower(1);
        backLeftMotor.setPower(1);
        frontLeftMotor.setPower(1);*/
    }

    @Override
    public void stop() {

    }
    @Override
    public void loop() {
        gp1.Update();

        driver.Drive(telemetry);
    }
}