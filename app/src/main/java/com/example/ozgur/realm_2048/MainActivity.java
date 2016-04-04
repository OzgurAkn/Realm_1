package com.example.ozgur.realm_2048;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textview_name)
    TextView name;
    @Bind(R.id.textview_age)
    TextView age;
    @Bind(R.id.textview_adress)
    TextView adress;
    @Bind(R.id.textview_zipcode)
    TextView zipcode;
    @Bind(R.id.textview_city)
    TextView city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        this.showDetailsUser();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UpdateUserActivity.class));
            }
        });
    }

    private void showDetailsUser() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfiguration);

        User user = realm.where(User.class).findFirst();
        if (user != null) {
            name.setText(Html.fromHtml("<b>Naam: </b>" + user.getName()));
            age.setText(Html.fromHtml("<b>Leeftijd: </b>" + user.getAge()));
            adress.setText(Html.fromHtml("<b>Adres: </b>" + user.getAdress()));
            zipcode.setText(Html.fromHtml("<b>Postcode: </b>" + user.getZipcode()));
            city.setText(Html.fromHtml("<b>Gemeente: </b>" + user.getCity()));
        } else {
            name.setText("Nog geen gegevens ingevuld.");
        }
    }
}
