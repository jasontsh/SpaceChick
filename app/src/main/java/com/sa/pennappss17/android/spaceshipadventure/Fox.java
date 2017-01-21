package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by emilytan on 1/21/17.
 */

public class Fox implements GameObj {

    private final int xPosition;
    private int yPosition;
    private final int velocity;
    private Bitmap bitmap;
    private int maxHeight;


    public Fox(int xInitial, int yInitial, int maxHeight, Bitmap bitmap) {
        xPosition = xInitial;
        yPosition = yInitial;
        this.bitmap = Bitmap.createScaledBitmap(bitmap, 400, 300, true);
        this.maxHeight = maxHeight;
        velocity = 20;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int getX() {
        return xPosition;
    }

    @Override
    public int getY() {
        return yPosition;
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox(xPosition, yPosition, 400, 300);
    }

    @Override
    public void movement() {
        yPosition += velocity;
        if (yPosition > maxHeight) {
            MainActivity.gameView.gameObjs.remove(this);
        }
    }
}
