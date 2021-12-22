package tw.tcnr05.m0803;

import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import java.util.Calendar;

public class DoLengthyWork extends Thread {
    private Handler mHandler;
    private ProgressBar mProBar;
    String TAG="oldpa=>";
    private int max=100;

    public void run() {
        Calendar begin = Calendar.getInstance();
        do {
            Calendar now = Calendar.getInstance();
            final int iDiffSec = 60 * (now.get(Calendar.MINUTE)
                    - begin.get(Calendar.MINUTE)) + now.get(Calendar.SECOND)
                    - begin.get(Calendar.SECOND);
            if (iDiffSec * 10 > max) {
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setProgress(max);
                    }
                });
                System.exit(0);
                break;
            }
            mHandler.post(new Runnable() {
                public void run() {
                    mProBar.setProgress(iDiffSec * 10);
                }
            });
            if (iDiffSec * 20 < max)
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setSecondaryProgress(iDiffSec * 20);
                    }
                });
            else
                mHandler.post(new Runnable() {
                    public void run() {
                        mProBar.setSecondaryProgress(max);
                    }
                });
        } while (true);
        Log.d(TAG,"end");
    }
    //---------------------------------
    void setProgressBar(ProgressBar proBar) {
        mProBar = proBar;
    }
    //------------------------------------
    void setHandler(Handler h) {
        mHandler = h;
    }
}