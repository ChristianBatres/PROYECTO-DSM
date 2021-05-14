package sv.edu.udb.proyectodsm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class restablecer extends AppCompatActivity {
    private EditText emailTV;
    private Button regBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer);
        initializeUI();
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestablecerContraseña();
            }
        });
    }

    private void RestablecerContraseña() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress =emailTV.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Correo Enviado para restablecer contraseña", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error, porfavor verificar su correo", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
    private void initializeUI() {
        emailTV = findViewById(R.id.email);
        regBtn = findViewById(R.id.registrar);
    }
}