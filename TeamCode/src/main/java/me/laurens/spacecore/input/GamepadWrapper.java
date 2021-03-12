package me.laurens.spacecore.input;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;

import me.laurens.spacecore.math.Vector2;

public class GamepadWrapper {

    public enum Button {
        A,
        B,
        X,
        Y,
        DPAD_UP,
        DPAD_DOWN,
        DPAD_LEFT,
        DPAD_RIGHT,
        BUMPER_L,
        BUMPER_R,
        STICK_L,
        STICK_R;
    }

    public enum Trigger {
        TRIGGER_L,
        TRIGGER_R
    }

    public enum Axis {
        STICK_L,
        STICK_R
    }

    private Gamepad gamepad = null;

    private HashMap<Button,Boolean> currentButtons = new HashMap<Button,Boolean>();
    private HashMap<Button,Boolean> previousButtons = new HashMap<Button,Boolean>();


    public GamepadWrapper(Gamepad gamepad) {
        this.gamepad = gamepad;

        for (Button button : Button.values()) {
            currentButtons.put(button,false);
            previousButtons.put(button,false);
        }

    }

    public GamepadWrapper() {
        this(null);
    }

    public void SetGamepad(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    private void UpdateButtons() {

        for(Button button : Button.values()) {

            //Copy the old values to the previousButtons map
            previousButtons.put(button,currentButtons.get(button));

            boolean value = false;
            switch(button) {
                case A: value = gamepad.a; break;
                case B: value = gamepad.b; break;
                case X: value = gamepad.x; break;
                case Y: value = gamepad.y; break;
                case DPAD_DOWN: value = gamepad.dpad_down; break;
                case DPAD_UP: value = gamepad.dpad_up; break;
                case DPAD_LEFT: value = gamepad.dpad_left; break;
                case DPAD_RIGHT: value = gamepad.dpad_right; break;
                case BUMPER_L: value = gamepad.left_bumper; break;
                case BUMPER_R: value = gamepad.right_bumper; break;
                case STICK_L: value = gamepad.left_stick_button; break;
                case STICK_R: value = gamepad.right_stick_button; break;
            }

            currentButtons.put(button,value);
        }
    }
    public void Update() {
        UpdateButtons();
    }

    public boolean GetButtonDown(Button button) {
        return currentButtons.get(button);
    }

    public boolean GetButtonUp(Button button) {
        return !currentButtons.get(button);
    }

    public boolean GetButtonPressed(Button button) {
        return currentButtons.get(button) && !previousButtons.get(button);
    }

    public float GetTrigger(Trigger trigger) {
        switch(trigger) {
            case TRIGGER_L:
                return gamepad.left_trigger;
            case TRIGGER_R:
                return gamepad.right_trigger;
        }

        return 0f;
    }

    public Vector2 GetAxis(Axis axis) {
        switch(axis) {
            case STICK_L:

                Vector2 rl = new Vector2(gamepad.left_stick_x,gamepad.left_stick_y);

                rl = rl.Normalize().Scale(Math.min(1d,rl.GetMagnitude()));
                return rl;
            case STICK_R:
                Vector2 rr = new Vector2(gamepad.right_stick_x,gamepad.right_stick_y);

                rr = rr.Normalize().Scale(Math.min(1d,rr.GetMagnitude()));
                return rr;
        }

        return new Vector2();
    }
}
