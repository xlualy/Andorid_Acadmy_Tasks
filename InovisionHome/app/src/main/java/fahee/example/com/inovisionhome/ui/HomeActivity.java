package fahee.example.com.inovisionhome.ui;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import fahee.example.com.inovisionhome.R;

public class HomeActivity extends AppCompatActivity {
    FragmentManager fm;
    DrawerLayout drawerLayout;
    ListView listView;
    String days[];
    ImageButton btnHideDrawer;
    private ImageView bckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        days = getResources().getStringArray(R.array.items);
        listView = (ListView) findViewById(R.id.list);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        btnHideDrawer  =findViewById(R.id.btn_close);
        bckButton= findViewById(R.id.list_img);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_service_action_bar);
        getSupportActionBar().setElevation(0);
        updateUi();
    }

    private void updateUi() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,days){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);

                return view;
            }
        };
        listView.setAdapter(adapter);

        fm = getSupportFragmentManager();
        ServiceFragment fragment = new ServiceFragment();
        fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        btnHideDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        bckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  DrawerFragment fragment= new DrawerFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,fragment)
                        .addToBackStack(null).commit();*/
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

}
