package za.ac.cput.accommodationapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * Created by thembamalungani on 16/06/06.
 */
public class SecondActivity extends Activity
{

    public void goBack(View view) {
        Intent back = new Intent();

        startActivity(back);
    }
}
