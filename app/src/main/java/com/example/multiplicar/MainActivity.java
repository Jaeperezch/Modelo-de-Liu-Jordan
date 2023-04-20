package com.example.multiplicar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText angulo_zenital, num_dia, dist_relativa, latitud,altura, turvidez,hora_solar;
    TextView resultado;
    Button multiplicar, borrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angulo_zenital = (EditText) findViewById(R.id.angulo_zenital);
        num_dia = (EditText) findViewById(R.id.num_dia);
        dist_relativa = (EditText) findViewById(R.id.dist_relativa);
        latitud = (EditText) findViewById(R.id.latitut);
        altura = (EditText) findViewById(R.id.altura);
        turvidez = (EditText) findViewById(R.id.turvidez);
        hora_solar = (EditText) findViewById(R.id.hora_solar);

        resultado = (TextView) findViewById(R.id.resultado);
        multiplicar = (Button) findViewById(R.id.bt_multiplicar);
        borrar = (Button) findViewById(R.id.bt_borrar);
        multiplicar.setOnClickListener(this);
        borrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View a) {

        switch (a.getId()) {
            case R.id.bt_multiplicar:
                String masa_aire = "";
                String decli_solar ="";
                String angulo_horario ="";
                String rad_extr ="";
                String rad_terr ="";
                String rad_diff ="";
                String rad_direc ="";
                String rad_tott ="";

                double ang_zenital = Double.parseDouble(angulo_zenital.getText().toString());
                double numero_dia = Double.parseDouble(num_dia.getText().toString());
                double hora_sol= Double.parseDouble(hora_solar.getText().toString());
                double dist_relativ=Double.parseDouble(dist_relativa.getText().toString());
                double lat=Double.parseDouble(latitud.getText().toString());
                double alt=Double.parseDouble(altura.getText().toString());
                double turv=Double.parseDouble(turvidez.getText().toString());


                double div = (float)(360)/(365);
                double masa_air= 1 / (Math.cos(Math.toRadians(ang_zenital)) + 0.50572 * Math.pow(96.07995 - Math.toRadians(ang_zenital), -1.6364));
                double decli_sola = Math.toRadians(23.45) * Math.sin(Math.toRadians(div * (284 + numero_dia)));
                double angulo_hor = Math.toRadians(15) * (hora_sol-12 );
                double rad_extra = (24 * 60 / Math.PI) * 1367 * dist_relativ * (Math.cos(lat) * Math.cos(decli_sola) * Math.sin(angulo_hor) + Math.sin(lat) * Math.sin(decli_sola));
                double rad_terre =  rad_extra * (0.75 + 0.00002 * alt);
                double rad_dif = turv * rad_terre;
                double rad_dir = (rad_terre - rad_dif) * (0.98 - 0.0018 * masa_air);
                double Rad_Total = rad_dif + rad_dir;

                masa_aire = "la masa del aire es: " + masa_air+"\n";
                decli_solar = "la declinacion Solar es: " + decli_sola +"\n";
                angulo_horario= "El angulo horario es: " +  angulo_hor+"\n";
                rad_extr="la radiacion estraterreste es de: " + rad_extra+"\n" ;
                rad_terr= "la radiacion terrestes es de: " + rad_terre+"\n";
                rad_diff= "la radiacion difusa es de: " + rad_dif+"\n";
                rad_direc= "la radiacion difusa es de: " + rad_dir+"\n";
                rad_tott= "la radiacion difusa es de: " + Rad_Total+"\n";

                        resultado.setText(masa_aire+ decli_solar+ angulo_horario + rad_extr+ rad_terr+rad_diff+rad_direc+rad_tott);
                break;


            }
    }
}