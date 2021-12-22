package tw.tcnr05.m0802a;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Calendar;

public class M0802a extends AppCompatActivity implements View.OnClickListener {

    private DatePicker mDatePik;
    private TimePicker mTimePik;
    private Button b001;
    private TextView mTxtResult;
    private TextView t001;
    private TextView anser;
    private Calendar cg;

    private Integer years01, months01, dates01, hours01, minius01;
    private MediaPlayer starmusic;
    private Handler handler = new Handler();
    private long spentTime;
    private long seconds;
    private long minius;
    private long hours;
    private TextView timer;
    private long endTime;
    private Button b002;
    private int years02;
    private int month02;
    private int days02;
    private int hours02;
    private int min02;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802a);
        setupviewconpomed();

    }

    private void setupviewconpomed() {
        mTxtResult = (TextView) findViewById(R.id.anser01);
        mDatePik = (DatePicker) findViewById(R.id.date01);
        mTimePik = (TimePicker) findViewById(R.id.time01);
        b001 = (Button) findViewById(R.id.m0802a_b001);
        b002 = (Button) findViewById(R.id.m0802b_b002);
        t001 = (TextView) findViewById(R.id.m0802a_t001);
        anser = (TextView) findViewById(R.id.anser01);
        timer = (TextView) findViewById(R.id.m0802a_timer);
        b001.setOnClickListener(this);
        b002.setOnClickListener(this);
        starmusic = MediaPlayer.create(M0802a.this, R.raw.s01);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m0802a_b001:
                b001.setVisibility(View.INVISIBLE);
                b002.setVisibility(View.VISIBLE);


                String s = getString(R.string.m0802a_t001);
                //轉化前的格式"hh:mm:ss"

                years01 = mDatePik.getYear();//取得畫面的"年"
                months01 = mDatePik.getMonth();//取得畫面的"月"
                dates01 = mDatePik.getDayOfMonth();//取得畫面的"日"
                hours01 = mTimePik.getHour();// 取得畫面的"小時"
                minius01 = mTimePik.getMinute();// 取得畫面的"分鐘"

                // 顯示選擇的日期和時間
                anser.setText(s +
                        years01 + getString(R.string.n_yy) +
                        (months01 + 1) + getString(R.string.n_mm) +
                        dates01 + getString(R.string.m_dd) +
                        hours01 + getString(R.string.d_hh) +
                        minius01 + getString(R.string.d_mm));
                //--------------------------------------

                cg = Calendar.getInstance();//----------設定月曆
                cg.set(years01, months01, dates01, hours01, minius01);
                endTime = cg.getTimeInMillis();


                handler.postDelayed(updateTimer, 10);// 停多久開始做這個動作

                break;
            case R.id.m0802b_b002:

                b001.setVisibility(View.VISIBLE);
                b002.setVisibility(View.INVISIBLE);

                Toast.makeText(getApplicationContext(),R.string.m0802a_b002,Toast.LENGTH_SHORT).show();
                music_set();
                timer.setText(R.string.m0802a_t003);
                t001.setText(R.string.m0802a_t001);
                setnowtime();
                handler.removeCallbacks(updateTimer);




                break;





        }















    }

    private void setnowtime() {
        Calendar c = Calendar.getInstance();
        years02 = c.get(Calendar.YEAR);
        month02 = c.get(Calendar.MONTH);
        days02 = c.get(Calendar.DAY_OF_MONTH);
        hours02 = c.get(Calendar.HOUR_OF_DAY);
        min02 = c.get(Calendar.MINUTE);
        //-----------------
        mDatePik.updateDate(years02, month02, days02);
        mTimePik.setHour(hours02);
        mTimePik.setMinute(min02);

    }

    private Runnable updateTimer = new Runnable() {


        @Override
        public void run() {
            spentTime = endTime - System.currentTimeMillis();
            hours = (spentTime / 1000) / 60 / 60;// 計算目前已過小時數
            minius = (spentTime / 1000) / 60 % 60;// 計算目前已過分鐘數
            seconds = (spentTime / 1000) % 60;// 計算目前已過秒數(取餘數)

            if (spentTime < 0 || hours > 999) {
                Toast.makeText(getApplicationContext(), getString(R.string.msg),
                        Toast.LENGTH_LONG).show();
                timer.setText(getString(R.string.m0802a_t003));
                t001.setText("");


                handler.removeCallbacks(updateTimer);
            } else {
                music_set();
                t001.setText(getString(R.string.m0802_alerm));
                music_set();//音樂重設
                // format("%02d", minius)將時間格式化為兩位數
                timer.setText(String.format("%02d", hours) + "：" + String.format("%02d", minius) + "："
                        + String.format("%02d", seconds));

                handler.postDelayed(this, 1000);// 真正延遲的時間，若要5秒更新一次，則改成5000
                if (hours == 0 && minius == 0 && seconds == 0) {
                    starmusic.start();
                    t001.setText(getString(R.string.m0802a_t002));
                    handler.removeCallbacks(updateTimer);// 結束緒


                }
            }


        }
    };

    private void music_set() {
        if (starmusic.isPlaying()) {
            starmusic.stop();
            try {
                starmusic.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}