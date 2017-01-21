package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by emilytan on 1/21/17.
 */

public class Life implements GameObj {

    public static Life[] lives = new Life[5];
    private static int currentLife;

    public static void addLife() {
        currentLife += 1;
    }

    public static void removeLife() {
        currentLife -= 1;
    }

    public static boolean alive() {
        if (Life.length > 0)
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public void movement() {

    }

    public int
}
