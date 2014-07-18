package cst407.oit.edu.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


public class GenieActivity extends ActionBarActivity {

    static final String button_one = "One";
    static final String button_two = "Two";
    static final String button_three = "Three";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genie);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        boolean state = sharedPref.getBoolean( button_one, true);

        //For each screen button
        setButtonListener(R.id.toggleButton1, button_one);
        setButtonListener(R.id.toggleButton2, button_two);
        setButtonListener(R.id.toggleButton3, button_three);

    }

    private void setButtonListener(int buttonId, String buttonNumber)
    {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        boolean state = sharedPref.getBoolean( buttonNumber, true);

        ToggleButton toggle = (ToggleButton) findViewById(buttonId);
        toggle.setChecked(state);
        toggle.setEnabled(state);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   buttonView.setChecked(false);
                } else {
                    buttonView.setEnabled(false);

                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    switch (buttonView.getId())
                    {
                        case R.id.toggleButton1:
                            editor.putBoolean(button_one, false);
                            break;

                        case R.id.toggleButton2:
                            editor.putBoolean(button_two, false);
                            break;

                        case R.id.toggleButton3:
                            editor.putBoolean(button_three, false);
                            break;
                    }
                    editor.commit();
                }
            }
        } );
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.genie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
