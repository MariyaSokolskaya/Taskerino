package za.warudo.taskerino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

public class NewTaskActivity extends AppCompatActivity {
    String taskName;
    EditText taskField;

    public static final String INBOX_TASKS = "Inbox Tasks";
    public static final String INBOX_TASKS_NAMES = "Name";

    SharedPreferences gTasks;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        taskField = findViewById(R.id.newTaskNameField);

        gTasks = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void newTaskButtonClick(View view){
        if (taskField.getText().toString().equals("")) Toast.makeText(this,
                "Введите название", Toast.LENGTH_SHORT).show();
        else {
            taskName = taskField.getText().toString();
            editor = gTasks.edit();
            editor.putString(INBOX_TASKS_NAMES, taskName);
            editor.apply();
            Intent intent = new Intent(this, InboxActivity.class);
            intent.putExtra("task name", taskName);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
