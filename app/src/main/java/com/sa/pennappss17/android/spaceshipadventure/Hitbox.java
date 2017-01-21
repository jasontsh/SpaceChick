package com.sa.pennappss17.android.spaceshipadventure;

/**
 * Created by emilytan on 1/21/17.
 */

public class Hitbox {
    public final int xStart, xEnd, yStart, yEnd;

    public Hitbox(int xPosition, int yPosition, int xLength, int yLength) {
        xStart = xPosition;
        yStart = yPosition;
        xEnd = xStart + xLength;
        yEnd = yStart + yLength;
    }

    /**
     *
     * @param other
     * @return true if this hitbox and the other hitbox overlaps
     */
    public boolean collision(Hitbox other) {
        return (other.xStart >= xStart && other.xStart <= xEnd && other.yStart >= yStart && other.yStart <= yEnd) ||
                (other.xEnd >= xStart && other.xEnd <= xEnd && other.yStart >= yStart && other.yStart <= yEnd) ||
                (other.xStart >= xStart && other.xStart <= xEnd && other.yEnd >= yStart && other.yEnd <= yEnd) ||
                (other.xEnd >= xStart && other.xEnd <= xEnd && other.yEnd >= yStart && other.yEnd <= yEnd) ||
                (this.xStart >= other.xStart && this.xStart <= other.xEnd && this.yStart >= other.yStart && this.yStart <= other.yEnd) ||
                (this.xEnd >= other.xStart && this.xEnd <= other.xEnd && this.yEnd >= other.yStart && this.yEnd <= other.yEnd) ||
                (this.xStart >= other.xStart && this.xStart <= other.xEnd && this.yEnd >= other.yStart && this.yEnd <= other.yEnd) ||
                (this.xEnd >= other.xStart && this.xEnd <= other.xEnd && this.yEnd >= other.yStart && this.yEnd <= other.yEnd);
    }

}
