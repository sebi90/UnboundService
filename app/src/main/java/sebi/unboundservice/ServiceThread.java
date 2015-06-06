package sebi.unboundservice;

import android.util.Log;

/**
 * Created by Sebi on 06.06.15.
 */
class ServiceThread extends Thread
{

    private int counter;

    public ServiceThread (int counter)
    {
        // teil a):
        if(counter > 0) {
            this.counter = counter;
        }
    }

    public void run()
    {
        int i = 0;
        try {
            // teil a)
            while (!isInterrupted() && counter > 0) {
                i++;
                Log.d(this.getClass().getName(), "service with thread " +
                        android.os.Process.myPid() + "-" +
                        Thread.currentThread().getName() +
                        ", iteration no. " + i);
                Thread.sleep(2000);
                counter--; // teil a)
            }

        }
        catch (InterruptedException e){}
    }
}
