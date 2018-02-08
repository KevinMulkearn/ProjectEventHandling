package com.mulkearn.kevin.randomColourNames;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.graphics.Color;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    RelativeLayout mainView;
    Button randButton;
    TextView hexText, rgbText, hsvText;
    String hex = "#FFFFFF";
    Menu menu;
    MenuItem item;
    private GestureDetectorCompat gestureDetector;
    int click = 0;

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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case R.id.saveHex:
                if (hex != "#FFFFFF"){
                    SharedPreferences sharedPref = getSharedPreferences("colors", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    if(click == 0){
                        editor.putString("color0", hex);
                    } else if (click == 1){
                        editor.putString("color1", hex);
                    } else if (click == 2){
                        editor.putString("color2", hex);
                    } else if (click == 3){
                        editor.putString("color3", hex);
                    } else if (click == 4){
                        editor.putString("color4", hex);
                    }
                    editor.apply();
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Hex Value", hex);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MainActivity.this, hex + " Copied to Clipboard", Toast.LENGTH_SHORT).show();
                    click++;
                    if (click > 4){
                        click = 0;
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Nothing to Save", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.recent:
                Intent savedIntent = new Intent(this, RecentColorsActivity.class);
                startActivity(savedIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /////////////////////////////////// GESTURE START /////////////////////////////////////////
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //(key,value)
    }

    //reset the saved state values
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
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
    //////////////////////////////////// GESTURE END //////////////////////////////////////////


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
}