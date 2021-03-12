package me.laurens.spacecore.math;

public class Vector2 {

    public double x = 0;
    public double y = 0;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Empty constructor for default values
    public Vector2() {

    }

    public Vector2 Add(Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    public Vector2 Subtract(Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }
    public Vector2 Scale(double s) {
        return new Vector2(x*s,y*s);
    }

    public double GetMagnitude() {
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    public Vector2 Normalize() {
        if(x == 0 && y == 0) {
            return new Vector2(0,0);
        }

        return Scale(1d/GetMagnitude());
    }


    public double GetAngle() {
        return Math.atan2(y,x);
    }

}
