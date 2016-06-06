package za.ac.cput.accommodationapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    public void goToActivityTwo(View view)
    {
        Intent getSecondActivity = new Intent(this, SecondActivity.class);
        final int result = 1;

        startActivityForResult(getSecondActivity, result);
    }

    public void goToActivityThree(View view) {
        Intent getThirdActivity = new Intent(this, ThirdActivity.class);
        startActivity(getThirdActivity);
    }

    public void goToActivityFour(View view) {
        Intent getFourthActivity = new Intent(this, FourthActivity.class);
        startActivity(getFourthActivity);
    }

    public void goToActivityViewData(View view) {
        //Intent getSecondActivity = new Intent(this, SecondActivity.class);
    }
}
