package com.example.eventbusdemo.dataset;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public enum Color {

    BLUE(android.graphics.Color.BLACK, android.graphics.Color.BLUE),
    WHITE(android.graphics.Color.BLACK, android.graphics.Color.WHITE),
    BLACK(android.graphics.Color.WHITE, android.graphics.Color.BLACK),
    YELLOW(android.graphics.Color.BLACK, android.graphics.Color.YELLOW);

    private int textColor;

    private int backgroundColor;

    Color(int textColor, int backgroundColor) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
