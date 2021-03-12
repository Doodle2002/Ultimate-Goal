package me.laurens.spacecore.util;

import java.util.ArrayList;

public class Cycle<T> {

    private ArrayList<T> contents = new ArrayList<T>();
    private int index = 0;
    public T Get() {
        return contents.get(index);
    }

    public T Get(int index) {
        return contents.get(Solve(index));
    }

    public void Add(T value) {
        contents.add(value);
    }

    private int Solve(int index) {
        if(index > contents.size()) {
            return contents.size()-1;
        } else if(index < 0) {
            return 0;
        }

        return index;
    }

    public T Next() {
        index = Solve(index+1);
        return Get(index);
    }

    public T Back() {
        index = Solve(index-1);
        return Get(index);
    }
}
