package sebi.unboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sebi on 06.06.15.
 */
public class MyUnboundService extends Service
{
    private static int instanceCounter = 0;
    private int number;
    private ServiceThread thread;
    private int countValue;
    public ArrayList<ServiceThread> threads;

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
        if (intent != null)
        {
            countValue = intent.getIntExtra("count",0);
        }
        Log.d(this.getClass().getName(), "onStartCommand (" + number + " / " +
                android.os.Process.myPid() + "-" +
                Thread.currentThread().getName() + ")" + "count: " + countValue);


        if (threads == null)
        {
            threads = new ArrayList<>();
        }
        // Teil b): immer einen Thread starten:
        //if(thread == null)
        //{
            thread = new ServiceThread(countValue, this);
            threads.add(thread);
            thread.start();
        Log.d(this.getClass().getName(), "" +thread.getThreadGroup().activeCount());

        //}
        return START_STICKY;

        //Service wird nicht erneut gestartet
        //return START_NOT_STICKY;

        //Service wird wieder gestartet mit gleichem Inhalt
        //return START_REDELIVER_INTENT;
    }

    // stopSelf fall keine threads mehr laufen
    public void checkThreads()
    {
        if (threads.size() <= 0){
            stopSelf();
        }
    }

    public void onDestroy()
    {
        Log.d(this.getClass().getName(), "onDestroy (" + number + " / " +
                android.os.Process.myPid() + "-" +
                Thread.currentThread().getName() + ")");

        //thread.interrupt();
        // teil d):
        if (thread.getThreadGroup() != null) {
            thread.getThreadGroup().interrupt();
        }


    }
}
