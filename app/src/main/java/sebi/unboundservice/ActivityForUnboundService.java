package sebi.unboundservice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.IllegalFormatException;

public class ActivityForUnboundService extends Activity {

    private EditText editText;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void clickedOnStart(View v) {
        //teil a): count hinzufügen
        try {
            count = Integer.parseInt(editText.getText().toString());
        } catch (NumberFormatException e) {}


        Log.d(this.getClass().getName(), "starting service (" +
                android.os.Process.myPid() + "-" +
                Thread.currentThread().getName() + ")" + "count: " + count);
        // teil a): und dem Intent mitgeben
        startService(new Intent(this, MyUnboundService.class).putExtra("count", count));
    }

    public void clickedOnStop(View v) {
        Log.d(this.getClass().getName(), "stopping service (" +
                android.os.Process.myPid() + "-" +
                Thread.currentThread().getName() + ")");
        stopService(new Intent(this, MyUnboundService.class));
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
