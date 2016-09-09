package com.oalvarez.compartefacil.aplicacion;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by oalvarez on 09/09/2016.
 */
public class Configuracion extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder(getApplicationContext())
                    .name("ComparteFacil.realm")
                    .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        Fresco.initialize(this);
    }
}
