package com.oalvarez.compartefacil;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oalvarez.compartefacil.entidad.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class RegistrarActivity extends BaseActivity {

    @BindView(R.id.etCorreoElectronicoRegistro)
    EditText etCorreoElectronicoRegistro;
    @BindView(R.id.tilCorreoRegistro)
    TextInputLayout tilCorreoRegistro;
    @BindView(R.id.etNombreRegistro)
    EditText etNombreRegistro;
    @BindView(R.id.tilNombreRegistro)
    TextInputLayout tilNombreRegistro;
    @BindView(R.id.etFechaRegistro)
    EditText etFechaRegistro;
    @BindView(R.id.tilFechaRegistro)
    TextInputLayout tilFechaRegistro;
    @BindView(R.id.etContrasenaRegistro)
    EditText etContrasenaRegistro;
    @BindView(R.id.tilContrasenaRegistro)
    TextInputLayout tilContrasenaRegistro;
    @BindView(R.id.etContrasenaRepRegistro)
    EditText etContrasenaRepRegistro;
    @BindView(R.id.tilContrasenaRepRegistro)
    TextInputLayout tilContrasenaRepRegistro;
    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;
    @BindView(R.id.btnCancelar)
    Button btnCancelar;
    @BindView(R.id.etCiudadRegistro)
    EditText etCiudadRegistro;
    @BindView(R.id.tilCiudad)
    TextInputLayout tilCiudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sCorreo = etCorreoElectronicoRegistro.getText().toString(),
                        sNombre = etNombreRegistro.getText().toString(),
                        sFechaNacimiento = etFechaRegistro.getText().toString(),
                        sCiudad = etCiudadRegistro.getText().toString(),
                        sContrasena = etContrasenaRegistro.getText().toString(),
                        sContrasenaRep = etContrasenaRepRegistro.getText().toString();

                if (sCorreo.trim().length() == 0) {
                    tilCorreoRegistro.setError("No dejes vacío tu correo");
                    return;
                } else {
                    tilCorreoRegistro.setErrorEnabled(false);
                }

                if (sNombre.trim().length() == 0) {
                    tilNombreRegistro.setError("No dejes vacío tu nombre");
                    return;
                } else {
                    tilNombreRegistro.setErrorEnabled(false);
                }

                if (sFechaNacimiento.trim().length() == 0) {
                    tilFechaRegistro.setError("No dejes vacío tu fecha de nacimiento");
                    return;
                } else {
                    tilFechaRegistro.setErrorEnabled(false);
                }

                if (sCiudad.trim().length() == 0) {
                    tilCiudad.setError("No dejes vacía tu ciudad");
                    return;
                } else {
                    tilCiudad.setErrorEnabled(false);
                }

                if (sContrasena.trim().length() == 0) {
                    tilContrasenaRegistro.setError("No dejes vacía tu contraseña");
                    return;
                } else {
                    tilContrasenaRegistro.setErrorEnabled(false);
                }

                if (sContrasenaRep.trim().length() == 0) {
                    tilContrasenaRepRegistro.setError("No dejes vacía la confirmación de contraseña");
                    return;
                } else {
                    tilContrasenaRepRegistro.setErrorEnabled(false);
                }

                if (!sContrasena.trim().equals(sContrasenaRep.trim())) {
                    tilContrasenaRepRegistro.setError("Las contraseñas no coinciden");
                    return;
                } else {
                    tilContrasenaRepRegistro.setErrorEnabled(false);
                }

                SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
                Date dFechaNacimiento = null;
                try {
                    dFechaNacimiento = formato.parse(sFechaNacimiento);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                final Date dFechaRegistro = dFechaNacimiento;
                Realm realm = Realm.getDefaultInstance();

                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        //Creamos un nuevo objeto o un nuevo registro para la base de datos
                        Usuario oUsuario = realm.createObject(Usuario.class);
                        oUsuario.setCorreoElectronico(sCorreo);
                        oUsuario.setNombre(sNombre);
                        oUsuario.setFechaNacimiento(dFechaRegistro);
                        oUsuario.setCiudad(sCiudad);
                        oUsuario.setContrasena(sContrasena);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(RegistrarActivity.this, "Te registraste correctamente", Toast.LENGTH_SHORT).show();
                        etCorreoElectronicoRegistro.setText("");
                        etNombreRegistro.setText("");
                        etFechaRegistro.setText("");
                        etCiudadRegistro.setText("");
                        etContrasenaRegistro.setText("");
                        etContrasenaRepRegistro.setText("");
                        etCorreoElectronicoRegistro.requestFocus();
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(RegistrarActivity.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}
