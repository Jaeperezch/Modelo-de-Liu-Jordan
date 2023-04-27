package com.example.multiplicar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class detalles extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);



        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            DecimalFormat formato=new DecimalFormat("0.00");


            String masaaire = "";
            String declinacionsolar = "";
            String angulohorario = "";
            String radextraterrestre = "";
            String raddifuza="";
            String raddirecta="";
            String radterrestre="";

            TextView Text_detalles = findViewById(R.id.detalles);

            Intent intent = getIntent();
            double Rad_dif = intent.getDoubleExtra("Rad_Dif",0.0);
            String Rad_DifS= formato.format(Rad_dif);
            //String Rad_difS = Double.toString(Rad_DifS);

            double Rad_dir = intent.getDoubleExtra("Rad_Directa",0.0);
            //String Rad_dirS = Double.toString(Rad_dir);
            String Rad_dirS= formato.format(Rad_dir);
            double Rad_terr = intent.getDoubleExtra("Rad_Terr",0.0);
            //String Rad_terrS = Double.toString(Rad_terr);
            String Rad_terrS= formato.format(Rad_terr);
            double Rad_extrat = intent.getDoubleExtra("Rad_Extrat",0.0);
            //String Rad_extratS = Double.toString(Rad_extrat);
            String Rad_extratS= formato.format(Rad_extrat);
            double Ang_hora = intent.getDoubleExtra("Ang_Hora",0.0);
            //String Ang_horaS = Double.toString(Ang_hora);
            String Ang_horaS= formato.format(Ang_hora);
            double Dec_sol = intent.getDoubleExtra("Dec_Sol",0.0);
            //String Dec_solS = Double.toString(Dec_sol);
            String Dec_solS = formato.format(Dec_sol);
            double Masa_aire = intent.getDoubleExtra("Masa_Aire",0.0);
            //String Masa_aireS = Double.toString(Masa_aire);
            String Masa_aireS = formato.format(Masa_aire);



            raddifuza= "la Radiancia Difuza es: " + Rad_DifS+ " W/m²"+"\n";
            raddirecta="\n"+"la Radiancia Directa es: " + Rad_dirS + " W/m²"+"\n";
            radterrestre="\n"+"la Radiancia Terrestre es: " + Rad_terrS + " W/m²"+"\n";
            radextraterrestre="\n"+"la Radiancia Extraterrestre es: " + Rad_extratS + " W/m²"+"\n";
            declinacionsolar="\n"+"La Declinación Solar es: " + Dec_solS + "°"+"\n";
            masaaire="\n"+"La Masa del Aire es: " + Masa_aireS + " gr"+"\n";

            Text_detalles.setText(raddifuza+raddirecta+radterrestre+radextraterrestre+angulohorario+declinacionsolar+masaaire);

        }


       }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    }


