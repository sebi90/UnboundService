package sebi.unboundservice;

import android.util.Log;

/**
 * Created by Sebi on 06.06.15.
 */
class ServiceThread extends Thread
{

    private int counter;
    private MyUnboundService service;

    public ServiceThread (int counter, MyUnboundService service)
    {
        // teil a):
        if(counter > 0) {
            this.counter = counter;
        }
        this.service = service;
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

        Log.d(this.getClass().getName(), "thread ends: " + service.countActiveThreads());

        // aktuellen thread aus array löschen
        service.threads.remove(this);

        // überprüfen ob noch threads laufen
        service.checkAlive();

        Log.d(this.getClass().getName(), "thread ends after remove: " + service.countActiveThreads());
    }
}
