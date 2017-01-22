package com.sa.pennappss17.android.spaceshipadventure;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by gustavo on 1/21/17.
 */

public class Star implements GameObj{
    private int xStarPosition;
    private int yStarPosition;
    private int velocity;
    private int which;
    private int count;
    private boolean os;
    static final Bitmap[][] STARS = new Bitmap[5][2];

    public Star(int width, int length, Resources res) {
        xStarPosition = (int) (Math.random() * width);
        yStarPosition = (int) (Math.random() * length);
        velocity = -2;
        if (STARS[0][0] == null) {
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.star1);
            STARS[0][0] = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(res, R.drawable.star2);
            STARS[1][0] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(res, R.drawable.star3);
            STARS[2][0] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(res, R.drawable.star4);
            STARS[3][0] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(res, R.drawable.star5);
            STARS[4][0] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
            bitmap.recycle();

            bitmap = BitmapFactory.decodeResource(res, R.drawable.star1_move);
            STARS[0][1] = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(res, R.drawable.star2_move);
            STARS[1][1] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(res, R.drawable.star3_move);
            STARS[2][1] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(res, R.drawable.star4_move);
            STARS[3][1] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
            bitmap.recycle();
            bitmap = BitmapFactory.decodeResource(res, R.drawable.star5_move);
            STARS[4][1] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
            bitmap.recycle();
        }
        which = (int)(Math.random()*5);
        os = true;
    }

    @Override
    public Bitmap getBitmap() {
        return STARS[which][os ? 0 : 1];
    }

    @Override
    public int getX() {
        return xStarPosition;
    }

    @Override
    public int getY() {
        return yStarPosition;
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public void movement() {
        xStarPosition += velocity;
        if (xStarPosition < 0) {
            MainActivity.gameView.gameObjs.remove(this);
            MainActivity.starCount--;
        }
        count++;
        if (count > 10) {
            os = !os;
            count = 0;
        }
    }
}
