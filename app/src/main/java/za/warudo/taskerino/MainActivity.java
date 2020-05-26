package za.warudo.taskerino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mainList = findViewById(R.id.mainList);
        FloatingActionButton fab = findViewById(R.id.fab);

        String[] categories = getResources().getStringArray(R.array.categoryItems);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this,
                R.layout.list_item, categories);
        mainList.setAdapter(categoriesAdapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();

                if (strText.equals("Входящие")) {
                    startActivity(new Intent(MainActivity.this, InboxActivity.class));
                }
                if (strText.equals("Сегодня")) {
                    startActivity(new Intent(MainActivity.this, TodayActivity.class));
                }
                if (strText.equals("Планы")) {
                    startActivity(new Intent(MainActivity.this, UpcomingActivity.class));
                }
                if (strText.equals("В любое время")) {
                    startActivity(new Intent(MainActivity.this, AnytimeActivity.class));
                }
                if (strText.equals("Когда-нибудь")) {
                    startActivity(new Intent(MainActivity.this, SomedayActivity.class));
                }
            }
        });
    }

    public void fabClick(View v){
        startActivity(new Intent(MainActivity.this, NewTaskActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
