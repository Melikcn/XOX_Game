package com.bilgeadam.xox.animations;

import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.NonNull;

import java.util.Optional;

public class Animations {

    private final static long DURATION = 500;
    private final static  float ALPHA = 0.27F;

    /**
     * Rotates given text 360 degree for each 100 ms.
     * @param myText Text to ve animated.
     * @param duration Duration of the animation.
     */
    public void rotateText(@NonNull View myText, Optional<Long> duration){

        long durationValue = duration.orElse(DURATION);

        myText.animate().rotationXBy(360F * durationValue / 100).setDuration(durationValue).alpha(ALPHA);
    }

    /**
     * Moves the button out of the screen
     * @param myButton Button to be moved
     * @param duration Duration of the animation
     * @param currentWindow Current window manager
     */
    public void moveButton(@NonNull View myButton, Optional<Long> duration, WindowManager currentWindow) {
        long durationValue = duration.orElse(DURATION);

        // Get current windows boundaries
        Rect bounds = currentWindow.getCurrentWindowMetrics().getBounds();

        //Get current button location
        int[] buttonLocation = new int[2];

        switch(Direction.generateRandomDirection()){
            case Right:
                myButton.animate().translationXBy(bounds.right - buttonLocation[0]).setDuration(durationValue).alpha(ALPHA);
                break;
            case Left:
                myButton.animate().translationXBy(bounds.left - buttonLocation[0] + myButton.getWidth()).setDuration(durationValue).alpha(ALPHA);
                break;
            case Up:
                myButton.animate().translationYBy(bounds.top - buttonLocation[1] - myButton.getHeight()).setDuration(durationValue).alpha(ALPHA);
                break;
            case Bottom:
                myButton.animate().translationYBy(bounds.bottom - buttonLocation[1]).setDuration(durationValue).alpha(ALPHA);
                break;
        }
    }
}
