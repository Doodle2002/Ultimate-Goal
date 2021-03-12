package me.laurens.spacecore.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import me.laurens.spacecore.util.Cycle;

public class ServoAnimator {

    public Cycle<Double> positions = new Cycle<Double>();

    private Servo servo;

    public void SetServo(Servo servo) {
        this.servo = servo;
    }

    public Servo GetServo() {
        return servo;
    }

    public void Next() {
        servo.setPosition(positions.Next());
    }

    public void Back() {
        servo.setPosition(positions.Back());
    }
}
