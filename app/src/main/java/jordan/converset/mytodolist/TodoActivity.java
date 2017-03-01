package jordan.converset.mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jconverset on 01/03/2017.
 */
public class TodoActivity extends Activity implements View.OnClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    private Button btnCreer ;
    private Button btnVider ;
    private ListView listView;
    private ArrayList<String> myArrayOfStrings;
    private ArrayList<String> myArrayOfBooleans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings =  getSharedPreferences(PREFS_NAME, 0);
        myArrayOfStrings = new ArrayList<String>(settings.getStringSet("Todos", new HashSet<String>()));
        myArrayOfBooleans = new ArrayList<String>(settings.getStringSet("Important", new HashSet<String>()));
        if(Singleton.getInstance().getMyArrayOfStrings().size() == 0 || Singleton.getInstance().getMyArrayOfBooleans().size() == 0) {
            Singleton.getInstance().initMyArrayOfStrings(myArrayOfStrings);
            Singleton.getInstance().initMyArrayOfBooleans(myArrayOfBooleans);
        }
        setContentView(R.layout.task_layout);
        btnCreer = (Button) findViewById(R.id.buttonCreer);
        btnCreer.setOnClickListener(this);
        btnVider = (Button) findViewById(R.id.buttonVider);
        btnVider.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MySimpleArrayAdapter(getApplicationContext(), myArrayOfStrings.toArray(new String[myArrayOfStrings.size()]), myArrayOfBooleans.toArray(new String[myArrayOfBooleans.size()])));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(new MySimpleArrayAdapter(getApplicationContext(), Singleton.getInstance().getMyArrayOfStrings().toArray(new String[Singleton.getInstance().getMyArrayOfStrings().size()]), Singleton.getInstance().getMyArrayOfBooleans().toArray(new String[Singleton.getInstance().getMyArrayOfBooleans().size()])));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonCreer:
                Intent myIntent = new Intent(getApplicationContext(),CreateTodoActivity.class);
                startActivity(myIntent);
                break;
            case R.id.buttonVider:
                Singleton.getInstance().clearMyArrayOfStrings();
                Singleton.getInstance().clearMyArrayOfBooleans();
                listView.setAdapter(new MySimpleArrayAdapter(getApplicationContext(), Singleton.getInstance().getMyArrayOfStrings().toArray(new String[Singleton.getInstance().getMyArrayOfStrings().size()]), Singleton.getInstance().getMyArrayOfBooleans().toArray(new String[Singleton.getInstance().getMyArrayOfBooleans().size()])));
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
