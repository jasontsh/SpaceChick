package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by emilytan on 1/21/17.
 */

public class Life {
    private int xLifePosition;
    private int yLifePosition;
    private boolean state;

    public Life(int coordinateX, int coordinateY, boolean state) {
        this.state = state;
        xLifePosition = coordinateX;
        yLifePosition = coordinateY;

    }

    public void changeState() {
        state = !state;
    }

    public int getX() {
        return xLifePosition;
    }

    public int getY() {
        return yLifePosition;
    }

    public boolean getState() {
        return state;
    }

}
