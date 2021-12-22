package tw.tcnr05.m0802;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;

public class M0802 extends AppCompatActivity implements View.OnClickListener {

    private TextView time;
    private long starTime;
    private Handler handler=new Handler();
    private long seconds;
    private long miniu;
    private long spentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802);
        setupViewConpome();

    }

    private void setupViewConpome() {
        time=(TextView)findViewById(R.id.m0802_timer);
        //--------------------------------------------------------取得目前時間
        starTime=System.currentTimeMillis();
        //--------------------------------------------------------宣告工作命令單 於100秒後開始
        handler.postDelayed(updateTimer,100 );
        time.setText(System.currentTimeMillis()+"");



    }
    //----------------------------------------------------------Runnable工作命令單執行所要做的內容
    private Runnable updateTimer=new Runnable() {


        @Override
        public void run() {
            spentTime=System.currentTimeMillis()-starTime;

            miniu=(spentTime/1000)/60;


            seconds=(spentTime/1000)%60;


            time.setText(String.format("%02d",miniu)+":"+String.format("%02d",seconds));

           handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(updateTimer);
        this.finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {


    }
}