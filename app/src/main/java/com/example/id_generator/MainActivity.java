package com.example.id_generator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textUserId = findViewById(R.id.textUserId);
        Button btnGenerarId = findViewById(R.id.btnGenerarId);
        Button btnResetId = findViewById(R.id.btnResetId);

        mostrarNuevoId(); // Mostrar al abrir la app

        btnGenerarId.setOnClickListener(v -> mostrarNuevoId());

        btnResetId.setOnClickListener(v -> {
            getSharedPreferences("user_prefs", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();

            textUserId.setText("ID reiniciado. Presiona 'Generar nuevo ID'");
        });
    }

    // 💫 Animación al mostrar nuevo ID
    private void mostrarNuevoId() {
        String nuevoId = idGenerator.generateUserId(this);

        // Aplica animación de desvanecido (fade-in)
        textUserId.setAlpha(0f); // invisible
        textUserId.setText("Tu ID es: " + nuevoId);
        textUserId.animate().alpha(1f).setDuration(500).start(); // se va mostrando
    }
}
