package com.sa.pennappss17.android.spaceshipadventure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by emilytan on 1/21/17.
 */

public class Fox implements GameObj {

    private final int xPosition;
    private int yPosition;
    private final double velocity;
    private Bitmap bitmap;
    private int maxHeight;
    static final Bitmap[] FOXES = new Bitmap[3];
    private int worth;


    public Fox(int xInitial, int yInitial, int xrange, int maxHeight, Resources res, int which) {
        xPosition = xInitial + (int) (Math.random() *  xrange);
        double r = Math.random();
        yPosition = r >= 0.5 ? yInitial : maxHeight;
        if (FOXES[0] == null) {
            Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.fox3);
            FOXES[0] = Bitmap.createScaledBitmap(bm, 350, 350, true);
            bm.recycle();
            bm = BitmapFactory.decodeResource(res, R.drawable.fox1);
            FOXES[1] = Bitmap.createScaledBitmap(bm, 300, 300, true);
            bm.recycle();
            bm = BitmapFactory.decodeResource(res, R.drawable.fox2);
            FOXES[2] = Bitmap.createScaledBitmap(bm, 300, 300, true);
            bm.recycle();
        }
        this.bitmap = FOXES[which];
        this.maxHeight = maxHeight;
        double v = 12 + Math.random()*4;
        if (r < 0.5) {
            v *= -1;
        }
        velocity = v;
        worth = which == 0 ? 10 : which == 1 ? 1 : 3;
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
        return new Hitbox(xPosition+20, yPosition+20, 240, 240);
    }

    @Override
    public void movement() {
        yPosition += velocity;
        if (yPosition > maxHeight) {
            MainActivity.gameView.gameObjs.remove(this);
            MainActivity.foxSet.remove(this);
        }
    }

    public int getWorth() {
        return worth;
    }
}
