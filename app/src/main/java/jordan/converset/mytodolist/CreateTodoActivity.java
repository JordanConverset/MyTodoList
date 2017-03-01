package jordan.converset.mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by jconverset on 01/03/2017.
 */
public class CreateTodoActivity extends Activity implements View.OnClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    private Button btnValider ;
    private EditText editText;
    private CheckBox checkBox;
    private ArrayList<String> myArrayOfStrings;
    private ArrayList<String> myArrayOfBooleans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);
        btnValider = (Button) findViewById(R.id.buttonValider);
        btnValider.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editText);
        checkBox = (CheckBox) findViewById(R.id.checkboxImportant);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonValider:
                Singleton.getInstance().setMyArrayOfStrings(editText.getText().toString());
                Singleton.getInstance().setMyArrayOfBooleans(checkBox.isChecked());
                Intent myIntent = new Intent(getApplicationContext(),TodoActivity.class);
                startActivity(myIntent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        myArrayOfStrings = Singleton.getInstance().getMyArrayOfStrings();
        myArrayOfBooleans = Singleton.getInstance().getMyArrayOfBooleans();
        SharedPreferences settings =  getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet("Todos",new HashSet<String>(myArrayOfStrings));
        editor.putStringSet("Important",new HashSet<String>(myArrayOfBooleans));
        editor.apply();
    }
}
