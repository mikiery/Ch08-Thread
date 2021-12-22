package tw.tcnr05.m0804;

public class UserThread extends Thread {
    M0804 activity;
    int what = 1;

    public UserThread(M0804 activity) {
        this.activity = activity;
    }


    @Override
    public void run() {
        while (true) {
            activity.myHandler.sendEmptyMessage((what++) % 4); // 回傳值 0,1,2,3 循環
            try {
                Thread.sleep(3000); // 休眠3000毫秒
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 回傳值 0,1,2,3 循環
    }
}
