package tw.tcnr05.m0803;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class M0803 extends AppCompatActivity {

    private ProgressBar proBar;
    private Handler mHandler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0803);
        setupViewConpoment();
    }

    private void setupViewConpoment() {
        proBar=(ProgressBar)findViewById(R.id.m0803_p001);

        DoLengthyWork work=new DoLengthyWork();
        //-----------------------------------給工作者命令
        work.setHandler(mHandler);
        work.setProgressBar(proBar);
        work.start();
        work.checkAccess();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}