package tw.tcnr05.m0801;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.ConversationAction;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class M0801 extends AppCompatActivity implements View.OnClickListener {

    private DatePicker mDatePik;
    private TimePicker mTimePik;
    private Button b001;
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0801);
        setupviewconpomed();

    }

    private void setupviewconpomed() {
        mTxtResult = (TextView) findViewById(R.id.m0801_t001);
        mDatePik = (DatePicker) findViewById(R.id.date01);
        mTimePik = (TimePicker) findViewById(R.id.time01);
        b001 = (Button) findViewById(R.id.m0801_b001);

        mTimePik.setOnClickListener(this);
        mTimePik.setOnClickListener(this);
        b001.setOnClickListener(this);
        mTxtResult.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        String s = getString(R.string.m0801_t001);
        //轉化前的格式"hh:mm:ss"
        String ss = Convert24to12(mTimePik.getHour() + ":" + mTimePik.getMinute() + ":00");


//        mTxtResult.setText(s+"\n"+
//        mDatePik.getYear()+"年"+mDatePik.getMonth()+"月"+mDatePik.getDayOfMonth()+"日"+"\n"+
//        mTimePik.getHour()+"時"+mTimePik.getMinute()+"分");
                mTxtResult.setText(s + "\n" +
                mDatePik.getYear() + "年" + mDatePik.getMonth() + "月" + mDatePik.getDayOfMonth() + "日" + "\n" + ss);


    }

    private String Convert24to12(String time) {

        String converTime = "";

        try {
            SimpleDateFormat input = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("hh:mm a");
            Date date = input.parse(time);
            converTime = output.format(date);

//--------------------------------------回傳錯誤訊息
        } catch (ParseException e) {

            e.printStackTrace();

        }


        return converTime;
    }


}