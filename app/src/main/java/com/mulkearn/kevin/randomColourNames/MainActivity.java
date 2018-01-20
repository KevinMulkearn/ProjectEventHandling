package com.mulkearn.kevin.randomColourNames;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.graphics.Color;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    RelativeLayout myLayout;
    Button colourButton;
    TextView hexView;
    TextView nameView;

    String colour;

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.gestureDetector = new GestureDetectorCompat(this,this);

        //Create Layout
        myLayout = new RelativeLayout(this);
        myLayout.setBackgroundColor(Color.WHITE);

        //Create Colour Button
        colourButton = new Button(this);
        colourButton.setText("Generate Random Colour");
        colourButton.setBackgroundColor(Color.WHITE);
        colourButton.setTextColor(Color.BLACK);

        //Colour Button rules
        RelativeLayout.LayoutParams buttonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, //Button size of text
                RelativeLayout.LayoutParams.WRAP_CONTENT //Button size of text
        );
        buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL); //Button position
        buttonDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonDetails.setMargins(0,0,0,20);

        //Create Hex View
        hexView = new TextView(this);
        hexView.setText("Hex Value");
        hexView.setTextColor(Color.WHITE);
        hexView.setShadowLayer(2,2,2,Color.BLACK);
        hexView.setTextSize(30);

        //Hex View rules
        RelativeLayout.LayoutParams textDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, //text view size of text
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        textDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);

        //Create Name View
        nameView = new TextView(this);
        nameView.setText("Colour Name");
        nameView.setTextColor(Color.BLACK);
        nameView.setBackgroundColor(Color.WHITE);
        nameView.setGravity(1);
        nameView.setTextSize(20);

        //Name View rules
        RelativeLayout.LayoutParams textNameDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, //text view size of text
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textNameDetails.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        textNameDetails.setMargins(0,20,0,0);


        //Add widgets to view
        myLayout.addView(colourButton, buttonDetails); //Add with position
        myLayout.addView(hexView, textDetails); //Add with position
        myLayout.addView(nameView, textNameDetails); //Add with position

        //Set main activity interface
        setContentView(myLayout);

        //Colour Button event listener
        colourButton.setOnClickListener(
                //Create Interface
                new Button.OnClickListener(){
                    public void onClick(View v){
                        String col = colourGenerator();

                        nameView.setBackgroundColor(Color.WHITE);
                        hexView.setText(col);
                        nameView.setText(colourName(col));
                        nameView.setTextColor(Color.parseColor(col));
                        colourButton.setTextColor(Color.parseColor(col));
                        myLayout.setBackgroundColor(Color.parseColor(col));
                        colourButton.setBackgroundColor(Color.parseColor(oppositeColour(col)));
                    }
                }
        );

    }

    //Use the below to add extra hidden features
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        hexView.setText("");
        nameView.setBackgroundColor(colourButton.getCurrentTextColor());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //Random colour generator
    public String colourGenerator(){
        Random rn = new Random();
        String nums = "";
        int num;
        String hexnum;

        for(int i = 0; i<6; i++){
            num = rn.nextInt(16); //Random int between 0 and 15
            hexnum = Integer.toHexString(num); //Convert int to hex string
            nums = nums + hexnum; //Append (to create 6 bit hex number)
        }
        colour = nums.toUpperCase();
        return "#" + colour;
    }

    public String oppositeColour(String hex){
        String opp_num = "";

        for(int i = 1; i < 7; i++){
            String str_num = "" + hex.charAt(i);
            int int_num = Integer.parseInt(str_num, 16);

            int new_num = 15 - int_num;
            String hex_num = Integer.toHexString(new_num);
            opp_num = opp_num + hex_num;
        }
        return "#" + opp_num;
    }

    public String colourName(String colour){
        String[] name1 = {" Smooth "," Rough "," Playful "," Wary "," Timid "," Reliable "," Ferocious "," Generous ",
        " Dramatic "," Cheerful "," Amusing "," Ambitious "," Dependable "," Vivid "," Keen "," Questionable "};
        String[] name2 = {"Dry ","Wet ","Hot ","Cold ","Warm ","Cool ","Good ","Bad ",
                "Day ","Night ","Sweet ","Sour ","Smart ","Strong ","Sharp ","Dull "};
        String[] name3 = {"Eruption ","Gamble ","Cream ","Summer ","Surprise ","Dream ","Emotion ","Whisper ",
                "Mist ","Attack ","Pleasure ","Secret ","Time ","Zest ","Passion ","mystery "};

        String name = "";
        int int_value1,int_value2,int_value3;
        String p1 = "",p2 = "",p3 = "";

        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                String str_value = "" + colour.charAt(1); //hex value at 1
                int_value1 = Integer.parseInt(str_value, 16); //hex value to int
                p1 = name1[int_value1];
            }
            if (i == 1) {
                String str_value = "" + colour.charAt(3); //hex value at 3
                int_value2 = Integer.parseInt(str_value, 16); //hex value to int
                p2 = name2[int_value2];
            }
            if (i == 2) {
                String str_value = "" + colour.charAt(5); //hex value at 5
                int_value3 = Integer.parseInt(str_value, 16); //hex value to int
                p3 = name3[int_value3];
            }
            name = p1 + p2 + p3;
        }
        return name;
    }
}