// Greg Heggie
// MDF3 - 1708
// DrawingSurface

package com.example.gheggie.DigDug;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class DrawingSurface extends SurfaceView implements SurfaceHolder.Callback {

    private Rect mDimensions;
    private Paint mBlankPaint;
    private Bitmap mDiggingHole;
    private Bitmap mBackground;
    private Paint mTextPaint;
    private ArrayList<Point> mHoleDug;
    private ArrayList<Treasure> mTreasure;
    static ArrayList<Treasure> foundTreasure;
    static ArrayList<Treasure> foundGold;
    private static final int HOLE_RADIUS = 100;
    private InputStream iS;

    public DrawingSurface(Context context) {
        super(context);
    }

    public DrawingSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        storeDimensions(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        storeDimensions(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setWillNotDraw(false);
        getHolder().addCallback(this);

        Resources res = getResources();

        mBackground = BitmapFactory.decodeResource(res, R.drawable.field);

        // set blank paint
        mBlankPaint = new Paint();

        // set black hole hole
        mDiggingHole = BitmapFactory.decodeResource(res, R.drawable.hole);

        //set text paint
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setTextSize(60.0f);

        // initialize array Lists
        mHoleDug = new ArrayList<>();
        foundTreasure = new ArrayList<>();
        foundGold = new ArrayList<>();
        mTreasure = new ArrayList<>();


        iS = getResources().openRawResource(R.raw.items);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Clear the canvas by drawing a single color
        canvas.drawColor(Color.BLACK);

        // Draw our background image to fill the entire dimensions of the canvas
        canvas.drawBitmap(mBackground, null, mDimensions, mBlankPaint);

        // Draw a circle at each touch point
        for(Point p : mHoleDug) {
            canvas.drawBitmap(mDiggingHole, p.x, p.y, null);
        }

        // Draw Text in the middle of the screen
        canvas.drawText(
                "Items Found : " + foundTreasure.size(),
                mDimensions.width() / 4.25f,
                mDimensions.height() / 10.5f,
                mTextPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Check if this touch event is a down event
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            Point newPoint = new Point((int)event.getX(), (int)event.getY());

            if(!mHoleDug.contains(newPoint)) {
                // add the point to our list if it does not exist
                mHoleDug.add(newPoint);
            }
                // if point is found. show toast and add to found list
                for(Treasure chest : mTreasure) {
                    if((chest.getLocX() >= newPoint.x - HOLE_RADIUS
                            && chest.getLocX() <= newPoint.x + HOLE_RADIUS)
                            && (chest.getLocY() >= newPoint.y - HOLE_RADIUS
                            && chest.getLocY() <= newPoint.y + HOLE_RADIUS)) {
                        if(!foundTreasure.contains(chest) && !chest.getName().equals("Gold")) {
                            // tell the user the item was found
                            foundTreasure.add(chest);
                            Toast.makeText(getContext(),
                                    "Found " + chest.getNameAndAmt(),
                                    Toast.LENGTH_SHORT).show();
                        } else if (!foundTreasure.contains(chest) && chest.getName().equals("Gold")) {
                            foundGold.add(chest);
                            Toast.makeText(getContext(),
                                    "Found " + chest.getNameAndAmt(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            // Tell the view to redraw on the next frame
            postInvalidate();
            gameWinner();
        }
        return super.onTouchEvent(event);
    }

    private void storeDimensions(SurfaceHolder holder) {
        // Lock the canvas to get an instance of it back.
        Canvas canvas = holder.lockCanvas();

        // Retrieve the dimensions and hold onto them for later.
        mDimensions = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Release the canvas and post a draw.
        holder.unlockCanvasAndPost(canvas);

        if(mDimensions != null) {
            // generate treasure list
            getItems();
        }
    }

    // generate random points on the view
    // create an array list to hold items from CSV file and the points as location
    private void getItems(){
        Random getX = new Random();
        Random getY = new Random();

        BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
        try {
            String csvLine;

                while ((csvLine = reader.readLine()) != null) {
                    int locX = getX.nextInt(mDimensions.width());
                    int locY = getY.nextInt(mDimensions.height());

                    String[] row = csvLine.split(",");
                    mTreasure.add(new Treasure(row[0], row[1], locX, locY));

                }
            iS.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gameWinner(){
        if(foundTreasure.size() + foundGold.size() == mTreasure.size()){
            new AlertDialog.Builder(getContext())
                    .setTitle("Congrats")
                    .setMessage("You dug up all the items!")
                    .setPositiveButton("OK", null).show();
        }
    }

    static ArrayList<Treasure> sortArray(ArrayList<Treasure> treasureArrayList) {

        int potion = 0;
        int hiPotion = 0;
        int xPotion = 0;
        int ether = 0;
        int hiEther = 0;
        int rustySword = 0;
        ArrayList<Treasure> sortedTreasures = new ArrayList<>();

        for(Treasure t : treasureArrayList) {
            switch(t.getName()) {
                case "Potion":
                    potion += 1;
                    break;
                case "Hi-Potion":
                    hiPotion += 1;
                    break;
                case "X-Potion":
                    xPotion += 1;
                    break;
                case "Ether":
                    ether += 1;
                    break;
                case "Hi-Ether":
                    hiEther += 1;
                    break;
                case "Rusty Sword":
                    rustySword += 1;
                    break;
                default:
                    Treasure treasure = new Treasure(t.getName(), "x 1");
                    sortedTreasures.add(treasure);
                    break;
            }
        }

        if(potion > 0) {
            Treasure treasure = new Treasure("Potion", "x " + potion);
            sortedTreasures.add(treasure);
        }

        if(hiPotion > 0) {
            Treasure treasure = new Treasure("Hi-Potion", "x " + hiPotion);
            sortedTreasures.add(treasure);
        }

        if(xPotion > 0) {
            Treasure treasure = new Treasure("X-Potion", "x " + xPotion);
            sortedTreasures.add(treasure);
        }

        if(ether > 0) {
            Treasure treasure = new Treasure("Ether", "x " + ether);
            sortedTreasures.add(treasure);
        }

        if(hiEther > 0) {
            Treasure treasure = new Treasure("Hi-Ether", "x " + hiEther);
            sortedTreasures.add(treasure);
        }

        if(rustySword > 0) {
            Treasure treasure = new Treasure("Rusty Sword", "x " + rustySword);
            sortedTreasures.add(treasure);
        }

        return sortedTreasures;
    }
}
