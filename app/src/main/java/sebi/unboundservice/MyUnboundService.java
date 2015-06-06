package sebi.unboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Sebi on 06.06.15.
 */
public class MyUnboundService extends Service
{
    private static int instanceCounter = 0;
    private int number;
    private ServiceThread thread;
    private int countValue;

    public MyUnboundService()
    {
        instanceCounter++;
        number = instanceCounter;
    }

    public IBinder onBind(Intent intent)
    {

        return null; // => ungebunderner Service
    }

    public void onCreate() {
        Log.d(this.getClass().getName(), "onCreate (" + number + " / " +
                android.os.Process.myPid() + "-" +
                Thread.currentThread().getName() + ")");
    }

    public int onStartCommand(Intent intent, int flags, int startid)
    {
        countValue = intent.getIntExtra("count",0);
        Log.d(this.getClass().getName(), "onStartCommand (" + number + " / " +
                android.os.Process.myPid() + "-" +
                Thread.currentThread().getName() + ")" + "count: " + countValue);

        // Teil b): immer einen Thread starten:
        //if(thread == null)
        //{
            thread = new ServiceThread(countValue);
            thread.start();
        //}
        return START_STICKY;
    }

    public void onDestroy()
    {
        Log.d(this.getClass().getName(), "onDestroy (" + number + " / " +
                android.os.Process.myPid() + "-" +
                Thread.currentThread().getName() + ")");

        //thread.interrupt();
        // teil d):
        thread.getThreadGroup().interrupt();
    }
}
