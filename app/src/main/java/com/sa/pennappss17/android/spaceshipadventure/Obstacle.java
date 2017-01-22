package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by gustavo on 1/20/17.
 */

public class Obstacle implements GameObj {
    private double xPosition;
    private final double yPosition;
    private double velocity;
    private double maxWidth;
    static final Bitmap[] OBSTACLES = new Bitmap[4];
    private Bitmap bitmap;

    public Obstacle(int xInitial, int boundary, int maxWidth, int velMod, Resources res, Bitmap bitmap) {
        xPosition = xInitial;
        if (OBSTACLES[0] == null) {
            Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.obstacle4);
            OBSTACLES[0] = Bitmap.createScaledBitmap(bm, 200, 200, true);
            bm.recycle();
            bm = BitmapFactory.decodeResource(res, R.drawable.obstacle3);
            OBSTACLES[1] = Bitmap.createScaledBitmap(bm, 200, 200, true);
            bm.recycle();
            bm = BitmapFactory.decodeResource(res, R.drawable.obstacle2);
            OBSTACLES[2] = Bitmap.createScaledBitmap(bm, 200, 200, true);
            bm.recycle();
            bm = BitmapFactory.decodeResource(res, R.drawable.obstacle1);
            OBSTACLES[3] = Bitmap.createScaledBitmap(bm, 200, 200, true);
            bm.recycle();
        }
        this.bitmap = Bitmap.createScaledBitmap(bitmap, 180, 120, true);
        this.maxWidth = maxWidth;
        yPosition = Math.random() * boundary;
        velocity = Math.random() * (5*Math.log10(velMod)) + 5;
        if (velocity > 55) {
            velocity = 55;
        }
    }

    @Override
    public void movement() {
        xPosition -= 1 * velocity;
        if (xPosition < 0) {
            MainActivity.gameView.gameObjs.remove(this);
            bitmap.recycle();
        }
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int getX() {
        return (int) xPosition;
    }

    @Override
    public int getY() {
        return (int) yPosition;
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox((int) (xPosition+10), (int) (yPosition+10), 120, 160);
    }
}
