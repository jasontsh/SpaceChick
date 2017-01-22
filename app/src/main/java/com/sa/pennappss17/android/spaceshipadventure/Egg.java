package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by emilytan on 1/21/17.
 */

public class Egg implements GameObj {

    private int xEgg;
    private int yEgg;
    private int velocity;
    private Bitmap bitmap;
    private int maxWidth;

    public Egg(int xShip, int yShip, int maxWidth, Bitmap bitmap) {
        xEgg = xShip;
        yEgg = yShip;
        velocity = 40;
        this.maxWidth = maxWidth;
        this.bitmap = Bitmap.createScaledBitmap(bitmap, 90, 60, true);
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int getX() {
        return xEgg;
    }

    @Override
    public int getY() {
        return yEgg;
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox(xEgg, yEgg, 90, 60);
    }

    @Override
    public void movement() {
        xEgg += velocity;
        if (xEgg > maxWidth + 200) {
            MainActivity.gameView.gameObjs.remove(this);
            bitmap.recycle();
        }
    }
}
