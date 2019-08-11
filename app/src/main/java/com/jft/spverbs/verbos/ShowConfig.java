package com.jft.spverbs.verbos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class ShowConfig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_config);


        Button button1 = findViewById(R.id.buttonreset);
        Button button2 = findViewById(R.id.buttonexit);
        final Intent activity = new Intent(getApplicationContext(), MainActivity.class);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowConfig.this);
                builder.setTitle("Advertencia");
                builder.setMessage("Todos los cambios son irreversibles. ¿Estás seguro de que quieres proceder?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        try {
                            try {
                                DdBb myddbb = new DdBb(getApplicationContext());
                                myddbb.resetLevels();
                                Toast.makeText(getApplicationContext(), "¡Hecho!", 20).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Ha habido un error procesando su solicitud", 20).show();
                            }
                        } catch (Exception e) {}
                        startActivity(activity);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(activity);
            }
        });

    }
}
