package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by Jason on 1/20/2017.
 */

public interface GameObj {
    Bitmap getBitmap();
    int getX();
    int getY();
    Hitbox getHitbox();
}
