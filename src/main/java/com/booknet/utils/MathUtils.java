package com.booknet.utils;

public class MathUtils {
    public double randomBetween(double min, double max) {
        return min + (max - min) * Math.random();
    }

    public int randomInt(int min, int max) {
        return (int) Math.round(this.randomBetween(min - 0.5, max + 0.5));
    }
}