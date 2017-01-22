package com.sa.pennappss17.android.spaceshipadventure;

import android.content.res.Resources;
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
    private double r;
    private Bitmap bitmap;

    public Obstacle(int xInitial, int boundary, int maxWidth, int velMod, Resources res) {
        xPosition = xInitial;
        if (OBSTACLES[0] == null) {
            Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.planet1);
            OBSTACLES[0] = Bitmap.createScaledBitmap(bm, 150, 150, true);
            bm.recycle();
            bm = BitmapFactory.decodeResource(res, R.drawable.planet2);
            OBSTACLES[1] = Bitmap.createScaledBitmap(bm, 150, 150, true);
            bm.recycle();
            bm = BitmapFactory.decodeResource(res, R.drawable.planet3);
            OBSTACLES[2] = Bitmap.createScaledBitmap(bm, 150, 240, true);
            bm.recycle();
            bm = BitmapFactory.decodeResource(res, R.drawable.planet4);
            OBSTACLES[3] = Bitmap.createScaledBitmap(bm, 150, 240, true);
            bm.recycle();
        }
        r = Math.random();
        if (r < 0.25) {
            bitmap = OBSTACLES[0];
        } else if (r < 0.5) {
            bitmap = OBSTACLES[1];
        } else if (r < 0.75) {
            bitmap = OBSTACLES[2];
        } else {
            bitmap = OBSTACLES[3];
        }
        this.maxWidth = maxWidth;
        yPosition = Math.random() * boundary;
        velocity = Math.random() * (7*Math.log10(velMod)) + 5;
        if (velocity > 90) {
            velocity = 90;
        }
    }

    @Override
    public void movement() {
        xPosition -= 1 * velocity;
        if (xPosition < 0) {
            MainActivity.gameView.gameObjs.remove(this);
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
        int d = r >= 0.5 ? 30 : 0;
        return new Hitbox((int) (xPosition+20), (int) (yPosition+10), 100, 120 + d);
    }
}
