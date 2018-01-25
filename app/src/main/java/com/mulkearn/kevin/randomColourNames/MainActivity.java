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

    RelativeLayout mainView;
    Button randButton;
    TextView hexText, rgbText, hsvText;
    String hex;

    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.gestureDetector = new GestureDetectorCompat(this,this);

        randButton = (Button) findViewById(R.id.randButton);
        hexText = (TextView) findViewById(R.id.hexText);
        rgbText = (TextView) findViewById(R.id.rgbText);
        hsvText = (TextView) findViewById(R.id.hsvText);
        mainView = (RelativeLayout) findViewById(R.id.mainView);

        randButton.setOnClickListener(
                //Create Interface
                new Button.OnClickListener()
                {
                    public void onClick (View v){
                        int red = randomRed();
                        int green = randomGreen();
                        int blue = randomBlue();
                        hex = rgbToHex(red, green, blue);
                        String hsv = getHSVValue(red, green, blue);

                        hexText.setTextColor(Color.WHITE);
                        rgbText.setTextColor(Color.WHITE);
                        hsvText.setTextColor(Color.WHITE);

                        hexText.setShadowLayer(2,2,2, Color.BLACK);
                        rgbText.setShadowLayer(2,2,2, Color.BLACK);
                        hsvText.setShadowLayer(2,2,2, Color.BLACK);

                        randButton.setTextColor(Color.parseColor(hex));

                        hexText.setText("Hex: " + hex);
                        rgbText.setText("rgb(" + red + ", " + green + ", " + blue + ")");
                        hsvText.setText(hsv);
                        
                        randButton.setBackgroundColor(Color.parseColor(oppositeHex(hex)));
                        mainView.setBackgroundColor( 0xff000000 + red * 0x10000 + green * 0x100 + blue);
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
        hexText.setTextColor(Color.WHITE);
        rgbText.setTextColor(Color.WHITE);
        hsvText.setTextColor(Color.WHITE);

        hexText.setShadowLayer(2,2,2, Color.BLACK);
        rgbText.setShadowLayer(2,2,2, Color.BLACK);
        hsvText.setShadowLayer(2,2,2, Color.BLACK);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        hexText.setTextColor(Color.parseColor(hex));
        rgbText.setTextColor(Color.parseColor(hex));
        hsvText.setTextColor(Color.parseColor(hex));

        hexText.setShadowLayer(2,2,2, Color.parseColor(hex));
        rgbText.setShadowLayer(2,2,2, Color.parseColor(hex));
        hsvText.setShadowLayer(2,2,2, Color.parseColor(hex));
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



    public int randomRed(){
        Random r_red = new Random();
        return r_red.nextInt(256);
    }

    public int randomGreen(){
        Random r_green = new Random();
        return r_green.nextInt(256);
    }

    public int randomBlue(){
        Random r_blue = new Random();
        return r_blue.nextInt(256);
    }

    //Random colour
    public String rgbToHex(int r, int g, int b){
        return String.format("#%02X%02X%02X",r, g, b);
    }

    //Get opposite colour
    public String oppositeHex(String hex){
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

    public String getHSVValue(int r, int g, int b){
        float[] hsv = new float[3];

        Color.RGBToHSV(r, g, b, hsv);

        float h = hsv[0];
        float s = hsv[1] * 100;
        float v = hsv[2] * 100;

        String hue = Integer.toString((int) h) + "\u00b0";
        String sat = Integer.toString((int) s) + "%";
        String val = Integer.toString((int) v) + "%";

        return "hsv(" + hue + ", " + sat + ", " + val + ")";
    }

//    public String colourName(String colour){
//        String[] name1 = {" Smooth "," Rough "," Playful "," Wary "," Timid "," Reliable "," Ferocious "," Generous ",
//        " Dramatic "," Cheerful "," Amusing "," Ambitious "," Dependable "," Vivid "," Keen "," Questionable "};
//        String[] name2 = {"Dry ","Wet ","Hot ","Cold ","Warm ","Cool ","Good ","Bad ",
//                "Day ","Night ","Sweet ","Sour ","Smart ","Strong ","Sharp ","Dull "};
//        String[] name3 = {"Eruption ","Gamble ","Cream ","Summer ","Surprise ","Dream ","Emotion ","Whisper ",
//                "Mist ","Attack ","Pleasure ","Secret ","Time ","Zest ","Passion ","mystery "};
//
//        String name = "";
//        int int_value1,int_value2,int_value3;
//        String p1 = "",p2 = "",p3 = "";
//
//        for (int i = 0; i < 3; i++) {
//            if (i == 0) {
//                String str_value = "" + colour.charAt(1); //hex value at 1
//                int_value1 = Integer.parseInt(str_value, 16); //hex value to int
//                p1 = name1[int_value1];
//            }
//            if (i == 1) {
//                String str_value = "" + colour.charAt(3); //hex value at 3
//                int_value2 = Integer.parseInt(str_value, 16); //hex value to int
//                p2 = name2[int_value2];
//            }
//            if (i == 2) {
//                String str_value = "" + colour.charAt(5); //hex value at 5
//                int_value3 = Integer.parseInt(str_value, 16); //hex value to int
//                p3 = name3[int_value3];
//            }
//            name = p1 + p2 + p3;
//        }
//        return name;
//    }
}