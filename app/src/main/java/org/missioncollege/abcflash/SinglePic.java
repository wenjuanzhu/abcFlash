package org.missioncollege.abcflash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.media.MediaPlayer;

public class SinglePic extends Activity {
    Integer[] cards = {R.drawable.flashcarda, R.drawable.flashcardb, R.drawable.flashcardc,
            R.drawable.flashcardd, R.drawable.flashcarde, R.drawable.flashcardf ,R.drawable.flashcardg,
            R.drawable.flashcardh, R.drawable.flashcardi, R.drawable.flashcardj, R.drawable.flashcardk,
            R.drawable.flashcardl,  R.drawable.flashcardm, R.drawable.flashcardn, R.drawable.flashcardo,
            R.drawable.flashcardp, R.drawable.flashcardq, R.drawable.flashcardr, R.drawable.flashcards,
            R.drawable.flashcardt,  R.drawable.flashcardu, R.drawable.flashcardv, R.drawable.flashcardw,
            R.drawable.flashcardx,  24, R.drawable.flashcardy, R.drawable.flashcardz, 27};

    ImageView pic;
    int id;
    private static AudioPlayer m_player=new AudioPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pic);
        Intent intent = getIntent();
        id = intent.getExtras().getInt("picId");
        pic = (ImageView) findViewById(R.id.imgSingle);
        pic.setImageResource(cards[id]);
        //m_player.play(this.getApplicationContext(), sounds[id]);
        //ImageView view = (ImageView) findViewById(R.id.imgSingle);
        //view.setImageResource(id);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_pic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
