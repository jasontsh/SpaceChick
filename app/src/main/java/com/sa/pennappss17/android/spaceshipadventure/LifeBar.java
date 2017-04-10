package com.sa.pennappss17.android.spaceshipadventure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Jason on 1/21/2017.
 */

public class LifeBar {
    private Life[] lives;

    private int currentLife;
    private static final int sizex = 125, sizey = 125;
    private Bitmap chick, nugget;

    public LifeBar(int xEnd, int yEnd, Resources res) {
        lives = new Life[5];
        currentLife = 3;
        for (int i = 0; i < 3; i++) {
            lives[i] = new Life(xEnd-sizex-100, yEnd - sizey * (4-i), true);
        }
        for (int i = 3; i < 5; i++) {
            lives[i] = new Life(xEnd-sizex-100, yEnd - sizey * (4-i), false);
        }
        Bitmap buffer = BitmapFactory.decodeResource(res, R.drawable.chick);
        chick = Bitmap.createScaledBitmap(buffer, sizex, sizey, true);
        buffer.recycle();

        buffer = BitmapFactory.decodeResource(res, R.drawable.nugget);
        nugget = Bitmap.createScaledBitmap(buffer, sizex, sizey, true);
        buffer.recycle();

        currentLife = 3;
    }

    public Bitmap getBitmap(int index) {
        return lives[index].getState() ? chick : nugget;
    }

    public void addLife() {
        lives[currentLife].changeState();
        if (currentLife < 5) currentLife++;

    }

    public void removeLife() {
        currentLife--;
        lives[currentLife].changeState();

    }

    public boolean alive() {
        return currentLife > 0;
    }

    public Life[] getLives() {
        return lives;
    }
}
