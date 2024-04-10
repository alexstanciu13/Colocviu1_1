package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Colocviu1_1SecondaryActivity extends AppCompatActivity {

    private Button verifyButton, cancelButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_secondary);

        verifyButton = findViewById(R.id.register_button);
        cancelButton = findViewById(R.id.cancel_button);
        textView = findViewById(R.id.text_view);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("text")) {
            String text = intent.getStringExtra("text");
            textView.setText(text);
        }

        verifyButton.setOnClickListener(view -> {
            setResult(RESULT_OK);
            finish();
        });

        cancelButton.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}