package com.robinson.luis.editorimg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imgFoto;
    ProgressDialog dialog;
    TextView txtInfo;
    TextView txtExtras;
    Bitmap tomCinza;

    int MEDIA;
    int MEDIANA;
    int MODA;
    int VARIANCIA;

    MetodoFoto foto = new MetodoFoto();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //dialog = new ProgressDialog(this);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(MainActivity.this,PegaFoto.class);
                // startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_histogram) {
            return true;
        }
        if (id == R.id.action_settings1) {
            return true;
        }
        if (id == R.id.action_settings2) {
            return true;
        }
        if (id == R.id.action_settings3) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action


        } else if (id == R.id.nav_converte_cinza) {

            try {

                //screenLoad("ON");
                // aqui manipulo a imagem.
                imgFoto = (ImageView) findViewById(R.id.imgFoto);

                BitmapDrawable drawable = (BitmapDrawable) imgFoto.getDrawable();
                tomCinza = drawable.getBitmap();


                foto.setFoto(tomCinza);
                tomCinza = foto.converteCinza();

                //txt

                foto.calculaMedia();
                foto.calculaMediana();
                foto.calculaModa();
                foto.calculaVariancia();

                MEDIA = foto.getMEDIA();
                MEDIANA = foto.getMEDIANA();
                MODA = foto.getMODA();
                VARIANCIA = foto.getVARIANCIA();

                imgFoto.setImageBitmap(tomCinza);

                txtInfo = (TextView) findViewById(R.id.txtInfo);
                txtInfo.setText(" Media:" + foto.getMEDIA() + " Mediana: " + foto.getMEDIANA() + "\n" +
                                " Moda: " + foto.getMODA() + " Variância: " + foto.getVARIANCIA());


                //requisições específicas do trabalho sem um fim concreto

                int pattern1,pattern2,pattern3,pattern4;
                pattern1 = foto.calculaMediaMetadeDireita();
                pattern2 = foto.calculaMedianaMetadeEsquerda();
                pattern3 = foto.calculaModaAcimaDiagonal();
                pattern4 = foto.calculaVarianciaAbaixoDiagonal();

                txtExtras = (TextView) findViewById(R.id.txtExtras);
                txtExtras.setText("média das tonalidades de cinza da metade direita da imagem: " + pattern1 + "\n"
                                 + "mediana das tonalidades de cinza metade esquerda da imagem: " + pattern2 + "\n"
                                 + "moda das tonalidades de cinza da parte acima da diagonal principal da imagem: " + pattern3 + "\n"
                                 + "variância das tonalidades de cinza da parte abaixo da diagonal principal da imagem: " + pattern4 + "\n");

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Erro:" + e, Toast.LENGTH_SHORT).show();
            } finally {
                //screenLoad("OFF");


            }


            //tomCinza =
            //imgFoto.setImageBitmap(tomCinza);
            //Bitmap foto = BitmapFactory.decodeResource(getResources(), R.id.imgFoto);


        } else if (id == R.id.nav_efeito1) {
            if (tomCinza!=null){
                imgFoto.setImageBitmap(foto.functionA());
            } else {
                Toast.makeText(getBaseContext(),"Converta a foto para cinza, FDP!",Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_efeito2) {

        }else if (id == R.id.nav_efeito3) {

        }else if (id == R.id.nav_efeito4) {

        }else if (id == R.id.nav_efeito5) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // não imlementado ainda, é o load enquanto o seu smartphone tentar parrir 10 crianças pra converter essa imagem.
    public void screenLoad(String status)

    {
        if (status.equals("ON")) {
            dialog.setMessage("Carregando!");
            dialog.setCancelable(false);
            dialog.setInverseBackgroundForced(false);
            dialog.show();
        } else {
            dialog.hide();
        }
    }
}
