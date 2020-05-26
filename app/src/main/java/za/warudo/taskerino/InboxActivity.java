package za.warudo.taskerino;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;



public class InboxActivity extends AppCompatActivity {
    ArrayList<HashMap<String,Object>> taskList = new ArrayList<>();
    HashMap<String, Object> map;
    ListView tasks;
    SimpleAdapter adapter;
    String taskNameString;

    public static final String INBOX_TASKS = "Inbox Tasks";
    public static final String INBOX_TASKS_NAMES = "Name";

    SharedPreferences gTasks;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        gTasks = getSharedPreferences(INBOX_TASKS, Context.MODE_PRIVATE);
        tasks = findViewById(R.id.listView);

        String[] from = {"IsDone", "TaskName"};
        int[] to = {R.id.checkbox_feed, R.id.firstText};
        //TODO восстановить taskList из sharedPreferences
        //restoreTasklist();
        adapter = new SimpleAdapter(this, taskList, R.layout.list_item2, from, to);
        tasks.setAdapter(adapter);

    }

    private void restoreTasklist(){
        StringBuilder str = new StringBuilder(gTasks.getString(INBOX_TASKS_NAMES,"Nothing"));
        for (int i = 0; i < str.length() ; i++) {
            if (str.charAt(i)=='[' || str.charAt(i)==']' || str.charAt(i)=='{'||str.charAt(i)=='}') {
                str.deleteCharAt(i);
                i--;
            }
        }

        String [] sharedStr = str.toString().split(",");
        if (sharedStr.length > 1 && taskList.isEmpty()) {
            for (int i = 0; i < sharedStr.length; i++) {
                //Log.i("List", sharedStr[i]);
                String[] task = sharedStr[i].split("=");
                if (i % 2 == 0) {
                    map = new HashMap<>();
                    map.put("TaskName", task[1]);
                }
                if (i % 2 == 1) {
                    map.put("IsDone", Boolean.valueOf(task[1]));
                    taskList.add(map);
                }

            }
        }
        editor = gTasks.edit();
        editor.clear();
        editor.apply();
    }

    public void fabClick2(View v){
        startActivityForResult(new Intent(InboxActivity.this, NewTaskActivity.class), 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //при возврате из предыдущей активности переменная taskList пуста. Ее данные надо восстанавливать
        restoreTasklist();
     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //taskNameString = gTasks.getString(INBOX_TASKS_NAMES, "");
            taskNameString = data.getStringExtra("task name");
            //добавляем новую задачу в shared
            map = new HashMap<>();
            map.put("TaskName", taskNameString);
            map.put("IsDone", false);
            taskList.add(map);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //TODO положить содержимое taskList в shared
        editor = gTasks.edit();
        editor.putString(INBOX_TASKS_NAMES, taskList.toString());
        editor.apply();
    }
}