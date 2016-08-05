package com.example.vinsa_000.slots;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{
    private ImageView flower1;
    private ImageView flower2;
    private ImageView flower3;
    private ImageButton go;
    private ImageButton reset;
    private TextView bank;
    private int bankAmount;
    private Animation rotate;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flower1 = (ImageView) findViewById(R.id.imageView2);
        flower2 = (ImageView) findViewById(R.id.imageView3);
        flower3 = (ImageView) findViewById(R.id.imageView4);
        go= (ImageButton) findViewById(R.id.button);
        reset = (ImageButton) findViewById(R.id.imageButton);
        bank = (TextView) findViewById(R.id.textView5);
        bankAmount = Integer.parseInt(getString(R.string.amount));
        rotate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        rotate.setAnimationListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.vinsa_000.slots/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.vinsa_000.slots/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    public void doResetButton(View view){
        go.setVisibility(View.VISIBLE);
        reset.setVisibility(View.INVISIBLE);
        bankAmount = Integer.parseInt(getString(R.string.amount));
        bank.setText(Integer.toString(bankAmount));
        flower1.setImageResource(R.drawable.f1);
        flower2.setImageResource(R.drawable.f1);
        flower3.setImageResource(R.drawable.f1);
    }
    public void doGoButton(View view) {
        if(bankAmount > 0){
            bankAmount -= 1;
        }
        reset.setVisibility(View.VISIBLE);
        flower1.setImageResource(R.drawable.tmp);
        flower2.setImageResource(R.drawable.tmp);
        flower3.setImageResource(R.drawable.tmp);
        doAnimate(flower1);
        doAnimate(flower2);
        doAnimate(flower3);
    }
    public void changeFlowers(){
        int r1 = (int)(Math.random()*(2+1));
        int r2 = (int)(Math.random()*(2+1));
        int r3 = (int)(Math.random()*(2+1));
        if(r1 == 0){
            flower1.setImageResource(R.drawable.f1);
        }
        else if(r1 == 1){
            flower1.setImageResource(R.drawable.f2);
        }
        else{
            flower1.setImageResource(R.drawable.f3);
        }
        if(r2 == 0){
            flower2.setImageResource(R.drawable.f1);
        }
        else if(r2 == 1){
            flower2.setImageResource(R.drawable.f2);
        }
        else{
            flower2.setImageResource(R.drawable.f3);
        }
        if(r3 == 0){
            flower3.setImageResource(R.drawable.f1);
        }
        else if(r3 == 1){
            flower3.setImageResource(R.drawable.f2);
        }
        else{
            flower3.setImageResource(R.drawable.f3);
        }
        updateBank(r1, r2, r3);
    }
    public void updateBank(int one, int two, int three){
        if(one == two && two == three){
            bankAmount += 3;
            bank.setText(Integer.toString(bankAmount));
        }
        else if(one == two && one != three){
            bankAmount += 2;
            bank.setText(Integer.toString(bankAmount));
        }
        else if(one == three && one != two){
            bankAmount += 2;
            bank.setText(Integer.toString(bankAmount));
        }
        else if(two == three && two != one) {
            bankAmount += 2;
            bank.setText(Integer.toString(bankAmount));
        }
        else {
            bank.setText(Integer.toString(bankAmount));
        }
    }
    public void doAnimate(View view) {
        view.startAnimation(rotate);
    }
    /**
     * <p>Notifies the start of the animation.</p>
     *
     * @param animation The started animation.
     */
    @Override
    public void onAnimationStart(Animation animation) {}
    /**
     * <p>Notifies the end of the animation. This callback is not invoked
     * for animations with repeat count set to INFINITE.</p>
     *
     * @param animation The animation which reached its end.
     */
    @Override
    public void onAnimationEnd(Animation animation) {
        changeFlowers();
        if(bankAmount <= 0){
            go.setVisibility(View.INVISIBLE);
        }
    }
    /**
     * <p>Notifies the repetition of the animation.</p>
     *
     * @param animation The animation which was repeated.
     */
    @Override
    public void onAnimationRepeat(Animation animation) {}
}
