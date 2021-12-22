package tw.tcnr05.m0805;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class M0805 extends AppCompatActivity {

    private SeekBar seekbar;
    private RatingBar ratBar;
    private TextView t001,t002,t003;
    private Button b001,b002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0805);
        setupViewConpoment();
    }

    private void setupViewConpoment() {

        seekbar=(SeekBar)findViewById(R.id.m0805_seekBar);
        ratBar=(RatingBar)findViewById(R.id.m0805_ratBar);
        t001=(TextView)findViewById(R.id.m0805_t001);
        t002=(TextView)findViewById(R.id.m0805_t002);
        t003=(TextView)findViewById(R.id.m0805_t003);
        b001=(Button)findViewById(R.id.m0805_b001);
        b002=(Button)findViewById(R.id.m0805_b002);
        seekbar.setOnSeekBarChangeListener(seekbar01);
        ratBar.setOnRatingBarChangeListener(ratBar01);
    }

        SeekBar.OnSeekBarChangeListener seekbar01=new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String s= getString(R.string.m0805_SeekBar);
                        t001.setText(s+(progress));
                        b001.getBackground().setAlpha((progress)*255/100);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

    RatingBar.OnRatingBarChangeListener ratBar01=new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            String s=getString(R.string.m0805_RatBar1);
            t002.setText(s+(rating));
            s=getString(R.string.m0805_RatBar2);
//            t003.setText(s+ratingBar.getProgress());
            t003.setText(s+ratingBar.getProgress());
            b002.getBackground().setAlpha(ratingBar.getProgress()*255/10);



            }




    };



}