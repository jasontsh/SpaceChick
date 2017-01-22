package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by emilytan on 1/21/17.
 */

public class Splat implements GameObj {
    @Override

    private int xSplat;
    private int ySplat;

    public Splat (int xPosition, int yPosition, Bitmap bitmap){
        xSplat = xPosition;
        ySplat = yPosition;
    }

    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public int getX() {
        return xSplat;
    }

    @Override
    public int getY() {
        return ySplat;
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public void movement() {

    }
}
