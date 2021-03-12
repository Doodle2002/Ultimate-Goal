package me.laurens.spacecore.math;

public class Vector3 {

    public double x = 0;
    public double y = 0;
    public double z = 0;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    //Empty constructor for default values
    public Vector3() {

    }

    public Vector3 Add(Vector3 v) {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    public Vector3 Subtract(Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }
    public Vector3 Scale(double s) {
        return new Vector3(x*s,y*s,z*s);
    }

    public double GetMagnitude() {
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2) + Math.pow(z,2));
    }

    public Vector3 Normalize() {
        if(x == 0 && y == 0 && z == 0) {
            return new Vector3(0,0,0);
        }

        return Scale(1d/GetMagnitude());
    }
}
