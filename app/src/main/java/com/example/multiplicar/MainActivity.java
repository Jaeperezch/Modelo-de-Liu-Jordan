package com.example.multiplicar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText angulo_zenital, num_dia, dist_relativa, latitud,altura, turvidez,hora_solar;
    TextView resultado;
    Button multiplicar, borrar, detalles;


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
        detalles = (Button) findViewById(R.id.bt_detalles);
        detalles.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        borrar.setOnClickListener(this);
    }
    @Override
    public void onClick(View a) {




        switch (a.getId()) {
            case R.id.bt_multiplicar:
                String masa_aire = "";
                String decli_solar = "";
                String angulo_horario = "";
                String rad_extr = "";
                String rad_terr = "";
                String rad_diff = "";
                String rad_direc = "";
                String rad_tott="";


                double ang_zenital = Double.parseDouble(angulo_zenital.getText().toString());
                double numero_dia = Double.parseDouble(num_dia.getText().toString());
                double hora_sol = Double.parseDouble(hora_solar.getText().toString());
                double dist_relativ = Double.parseDouble(dist_relativa.getText().toString());
                double lat = Double.parseDouble(latitud.getText().toString());
                double alt = Double.parseDouble(altura.getText().toString());
                double turv = Double.parseDouble(turvidez.getText().toString());


                double div = (float) (360) / (365);
                double masa_air = 1 / (Math.cos(Math.toRadians(ang_zenital)) + 0.50572 * Math.pow(96.07995 - Math.toRadians(ang_zenital), -1.6364));
                double decli_sola = Math.toRadians(23.45) * Math.sin(Math.toRadians(div * (284 + numero_dia)));
                double angulo_hor = Math.toRadians(15) * (hora_sol - 12);
                double rad_extra = (24 * 60 / Math.PI) * 1367 * dist_relativ * (Math.cos(lat) * Math.cos(decli_sola) * Math.sin(angulo_hor) + Math.sin(lat) * Math.sin(decli_sola));
                double rad_terre = rad_extra * (0.75 + 0.00002 * alt);
                double rad_dif = turv * rad_terre;
                double rad_dir = (rad_terre - rad_dif) * (0.98 - 0.0018 * masa_air);
                double Rad_Total= (rad_dif + rad_dir);

                DecimalFormat formato=new DecimalFormat("0.00");
                String Rad_TotalF= formato.format(Rad_Total);
                String unidad = " W/m²";
                rad_tott = "La Radiacion Total es de: " + Rad_TotalF + unidad + "\n";

                resultado.setText(rad_tott);


                break;

            case R.id.bt_borrar:
                angulo_zenital.setText("");
                num_dia.setText("");
                hora_solar.setText("");
                dist_relativa.setText("");
                latitud.setText("");
                altura.setText("");
                turvidez.setText("");
                resultado.setText("");
                break;

            case R.id.bt_detalles:



                double ang_zenital2 = Double.parseDouble(angulo_zenital.getText().toString());
                double numero_dia2 = Double.parseDouble(num_dia.getText().toString());
                double hora_sol2 = Double.parseDouble(hora_solar.getText().toString());
                double dist_relativ2 = Double.parseDouble(dist_relativa.getText().toString());
                double lat2 = Double.parseDouble(latitud.getText().toString());
                double alt2 = Double.parseDouble(altura.getText().toString());
                double turv2 = Double.parseDouble(turvidez.getText().toString());


                double div2 = (float) (360) / (365);
                double masa_air2 = 1 / (Math.cos(Math.toRadians(ang_zenital2)) + 0.50572 * Math.pow(96.07995 - Math.toRadians(ang_zenital2), -1.6364));
                double decli_sola2 = Math.toRadians(23.45) * Math.sin(Math.toRadians(div2 * (284 + numero_dia2)));
                double angulo_hor2 = Math.toRadians(15) * (hora_sol2 - 12);
                double rad_extra2 = (24 * 60 / Math.PI) * 1367 * dist_relativ2 * (Math.cos(lat2) * Math.cos(decli_sola2) * Math.sin(angulo_hor2) + Math.sin(lat2) * Math.sin(decli_sola2));
                double rad_terre2 = rad_extra2 * (0.75 + 0.00002 * alt2);
                double rad_dif2 = turv2 * rad_terre2;
                double rad_dir2 = (rad_terre2 - rad_dif2) * (0.98 - 0.0018 * masa_air2);
                double Rad_Total2= rad_dif2 + rad_dir2;




                Intent intent = new Intent(this, detalles.class);
                intent.putExtra("Rad_Dif", rad_dif2);
                intent.putExtra("Rad_Directa", rad_dir2);
                intent.putExtra("Rad_Terr", rad_terre2);
                intent.putExtra("Rad_Extrat", rad_extra2);
                intent.putExtra("Ang_Hora", angulo_hor2);
                intent.putExtra("Dec_Sol", decli_sola2);
                intent.putExtra("Masa_Aire", masa_air2);


                startActivity(intent);








                break;


        }
    }
}