package jordan.converset.mytodolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by jconverset on 01/03/2017.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    public static final String PREFS_NAME = "MyPrefsFile";
    private final Context context;
    private final String[] values;
    private final String[] bools;
    public MySimpleArrayAdapter(Context context, String[] values, String[] bools) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
        this.bools = bools;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        textView.setText(values[position]);
        if(Boolean.parseBoolean(bools[position])) {
            textView.setTextColor(getContext().getResources().getColor(R.color.Red));
        }
        String s = values[position];
        return rowView;
    }
}
