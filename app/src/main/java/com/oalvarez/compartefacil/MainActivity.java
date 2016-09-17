package com.oalvarez.compartefacil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.oalvarez.compartefacil.entidad.Usuario;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity {


    @BindView(R.id.sdvLogo)
    SimpleDraweeView sdvLogo;
    @BindView(R.id.etCorreoElectronico)
    EditText etCorreoElectronico;
    @BindView(R.id.tilCorreoElectronico)
    TextInputLayout tilCorreoElectronico;
    @BindView(R.id.etContrasena)
    EditText etContrasena;
    @BindView(R.id.tilContrasena)
    TextInputLayout tilContrasena;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sdvLogo.setImageURI(Uri.parse("res:/" + R.drawable.calculator_logo));

    }


    @Override
    protected void onResume() {
        super.onResume();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sCorreoElectronico = etCorreoElectronico.getText().toString(),
                        sContrasena = etContrasena.getText().toString();

                Realm realm = Realm.getDefaultInstance();
                RealmQuery<Usuario> consulta = realm.where(Usuario.class);
                consulta.equalTo("correoElectronico", sCorreoElectronico);
                consulta.equalTo("contrasena", sContrasena);

                RealmResults<Usuario> resultado = consulta.findAll();

                if (resultado.isEmpty()){
                    Toast.makeText(MainActivity.this, "Datos Inválidos", Toast.LENGTH_SHORT).show();
                }
                else{
                    for (Usuario oUsuario: resultado) {
                        Toast.makeText(MainActivity.this, "Bienvenido: " + oUsuario.getNombre(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                        intent.putExtra("nombreusuario",oUsuario.getNombre() );
                        startActivity(intent);
                    }
                }


            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });
    }
}
