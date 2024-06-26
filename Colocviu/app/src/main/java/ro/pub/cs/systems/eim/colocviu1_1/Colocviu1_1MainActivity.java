package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Colocviu1_1MainActivity extends AppCompatActivity {

    Button north_button, south_button, west_button, east_button;
    Button navigateToSecondaryActivity, clean_button;

    TextView textView;

    int total_buttons_clicked = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);

        north_button = findViewById(R.id.north_button);
        south_button = findViewById(R.id.south_button);
        west_button = findViewById(R.id.west_button);
        east_button = findViewById(R.id.east_button);
        north_button.setOnClickListener(buttonClickListener);
        south_button.setOnClickListener(buttonClickListener);
        west_button.setOnClickListener(buttonClickListener);
        east_button.setOnClickListener(buttonClickListener);

        textView = findViewById(R.id.text_view);

        navigateToSecondaryActivity = findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivity.setOnClickListener(buttonClickListener);

        clean_button = findViewById(R.id.clean_button);
        clean_button.setOnClickListener(buttonClickListener);

    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String textViewContent = textView.getText().toString();
            if (!textViewContent.isEmpty()) {
                textViewContent += ", ";
            }

            if (view.getId() == R.id.north_button) {
                total_buttons_clicked++;
                textView.setText(textViewContent + "North");
            }

            if (view.getId() == R.id.south_button) {
                total_buttons_clicked++;
                textView.setText(textViewContent + "South");
            }

            if (view.getId() == R.id.west_button) {
                total_buttons_clicked++;
                textView.setText(textViewContent + "West");
            }
            if (view.getId() == R.id.east_button) {
                total_buttons_clicked++;
                textView.setText(textViewContent + "East");
            }

            if (view.getId() == R.id.navigate_to_secondary_activity_button) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
                intent.putExtra("text", textViewContent);
                startActivityForResult(intent, 1);
            }

            if (view.getId() == R.id.clean_button) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
                intent.putExtra("text", textViewContent);
                total_buttons_clicked = 0;
                textView.setText("");
                startActivityForResult(intent, 1);
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        total_buttons_clicked = 0;
        textView.setText("");

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Result is OK", Toast.LENGTH_SHORT).show();
            }

            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Result is CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("total_buttons_clicked", total_buttons_clicked);
        outState.putString("strings", textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("total_buttons_clicked")) {
            total_buttons_clicked = savedInstanceState.getInt("total_buttons_clicked");
            Toast.makeText(this, "Total buttons clicked " + total_buttons_clicked, Toast.LENGTH_SHORT).show();

            textView.setText(savedInstanceState.getString("strings"));
        }
        Log.d("colocviu1_1", "onRestoreInstanceState: Total buttons clicked: " + total_buttons_clicked);
    }
}