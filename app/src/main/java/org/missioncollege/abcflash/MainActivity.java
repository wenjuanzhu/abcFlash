package org.missioncollege.abcflash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {
    final int QUESTION_POS = 24;
    final int NUMBER_OF_LETTERS = 26;
    final int UPPER_LOWER_CASE_POS = 27;

    Integer[] cards = {R.drawable.flashcarda, R.drawable.flashcardb, R.drawable.flashcardc,
            R.drawable.flashcardd, R.drawable.flashcarde, R.drawable.flashcardf ,R.drawable.flashcardg,
            R.drawable.flashcardh, R.drawable.flashcardi, R.drawable.flashcardj, R.drawable.flashcardk,
            R.drawable.flashcardl,  R.drawable.flashcardm, R.drawable.flashcardn, R.drawable.flashcardo,
            R.drawable.flashcardp, R.drawable.flashcardq, R.drawable.flashcardr, R.drawable.flashcards,
            R.drawable.flashcardt,  R.drawable.flashcardu, R.drawable.flashcardv, R.drawable.flashcardw,
            R.drawable.flashcardx,  R.drawable.flashcardy, R.drawable.flashcardz};
    Integer[] sounds = {R.raw.asound, R.raw.bsound, R.raw.csound,
            R.raw.dsound, R.raw.esound, R.raw.fsound,R.raw.gsound,
            R.raw.hsound, R.raw.isound, R.raw.jsound,
            R.raw.ksound, R.raw.lsound, R.raw.msound,R.raw.nsound,
            R.raw.osound, R.raw.psound, R.raw.qsound,R.raw.rsound,
            R.raw.ssound, R.raw.tsound, R.raw.usound,R.raw.vsound,
            R.raw.wsound,  R.raw.xsound,R.raw.whichonepcm, R.raw.ysound,
            R.raw.zsound};
    private static AudioPlayer m_player=new AudioPlayer();
    MainActivity thisMainActivity;

    int expectedLetter;
    boolean questionMode = false;
    boolean upperCase = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        thisMainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView grid = (GridView) findViewById(R.id.gridView);
        final TextAdapter textAdapter = new TextAdapter(this);
        grid.setAdapter(textAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == QUESTION_POS) {
                    questionMode = true;
                    MediaPlayer m_player_1 = MediaPlayer.create(thisMainActivity.getApplicationContext(), sounds[QUESTION_POS]);

                    m_player_1.start();

                    Random random = new Random();
                    int index = 0xff;
                    index = random.nextInt(NUMBER_OF_LETTERS);
                    MediaPlayer m_player_2 = MediaPlayer.create(thisMainActivity.getApplicationContext(), sounds[index]);
                    expectedLetter = index;
                    m_player_1.setNextMediaPlayer(m_player_2);

                } else if (position == UPPER_LOWER_CASE_POS) {
                    if (upperCase) {
                        upperCase = false;
                    } else {
                        upperCase = true;
                    }
                    textAdapter.notifyDataSetChanged();
                } else {
                    if (questionMode == false) {
                        m_player.play(thisMainActivity.getApplicationContext(), sounds[position]);
                        Intent intent = new Intent(MainActivity.this, SinglePic.class);
                        intent.putExtra("picId", position);
                        startActivity(intent);
                    } else {
                           if (expectedLetter >= QUESTION_POS) {
                               expectedLetter = expectedLetter + 1;
                        }

                        if (expectedLetter == position) {
                            m_player.play(thisMainActivity.getApplicationContext(), R.raw.goodjob); //play good job
                            Intent intent = new Intent(MainActivity.this, SinglePic.class);
                            intent.putExtra("picId", position);
                            startActivity(intent);
                            questionMode = false;
                        } else {
                            m_player.play(thisMainActivity.getApplicationContext(), R.raw.tryagain); //play try again
                        }

                    }

                }
            }
        });

        textAdapter.notifyDataSetChanged();
    }

    public class TextAdapter extends BaseAdapter{

        private Context context;
        private String[] texts = {"A","B","C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
        "O","P","Q","R","S","T", "U","V","W","X","?","Y","Z","="};


        Random random = new Random();

        public TextAdapter(Context context)
        {
            this.context = context;
        }

        @Override
        public int getCount() {
            return texts.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            //ViewHolder holder;
            if (convertView == null) {
                tv = new TextView(context);
            }
            else {
                tv = (TextView) convertView;
            }

            if(upperCase)
            {
                tv.setText(texts[position]);
            }
            else
            {
                tv.setText(texts[position].toLowerCase());
            }


            int r = 127 + random.nextInt(128);
            int g = 127 + random.nextInt(128);
            int b = 127 + random.nextInt(128);

            int color = Color.rgb(r, g, b);
            tv.setBackgroundColor(color);
            tv.setTextSize(50);
            tv.setGravity(Gravity.CENTER );

            return tv;
        }
    }
}
