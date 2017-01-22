package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by emilytan on 1/21/17.
 */

public class Splat implements GameObj {

    private int xSplat;
    private int ySplat;
    private int counter;
    private Bitmap bitmap;

    public Splat (int xPosition, int yPosition, Bitmap bitmap){
        xSplat = xPosition;
        ySplat = yPosition;
        this.bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
        counter = 0;
    }

    public Bitmap getBitmap() {
        if(counter > 25) {
            MainActivity.gameView.gameObjs.remove(this);
            return null;
        }
        return bitmap;
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
        counter++;
    }
}
