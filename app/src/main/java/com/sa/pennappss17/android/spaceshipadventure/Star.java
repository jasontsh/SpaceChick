package com.sa.pennappss17.android.spaceshipadventure;

/**
 * Created by gustavo on 1/21/17.
 */

public class Star {
    private int xStarPosition;
    private int yStarPosition;
    private int velocity;

    public Star(int width, int length) {
        xStarPosition = (int) Math.random() * width;
        yStarPosition = (int) Math.random() * length;
        velocity = 5;
    }
}
