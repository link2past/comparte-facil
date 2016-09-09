package com.oalvarez.compartefacil;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @OnClick({R.id.btnLogin, R.id.btnRegistrar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                break;
            case R.id.btnRegistrar:
                break;
        }
    }
}
