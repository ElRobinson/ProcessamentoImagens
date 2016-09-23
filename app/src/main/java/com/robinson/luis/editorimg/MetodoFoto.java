package com.robinson.luis.editorimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by Luis on 19/09/2016.
 */
public class MetodoFoto {
    public int[][] Matriz = null;
    public int MEDIA = 0;
    public int MEDIANA = 0;
    public int MODA = 0;
    public int VARIANCIA = 0;
    public int PARAMETRO_BYTE = 16;

    public Bitmap foto;

    public Bitmap getFoto() {
        return foto;
    }

    public int getMEDIA() {
        return MEDIA;
    }

    public void setMEDIA(int MEDIA) {
        this.MEDIA = MEDIA;
    }

    public int getMEDIANA() {
        return MEDIANA;
    }

    public void setMEDIANA(int MEDIANA) {
        this.MEDIANA = MEDIANA;
    }

    public int getMODA() {
        return MODA;
    }

    public void setMODA(int MODA) {
        this.MODA = MODA;
    }

    public int getVARIANCIA() {
        return VARIANCIA;
    }

    public void setVARIANCIA(int VARIANCIA) {
        this.VARIANCIA = VARIANCIA;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Bitmap converteCinza() {
        int h = foto.getHeight();
        int w = foto.getWidth();

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap tomCinza = Bitmap.createBitmap(w, h, conf);



        Matriz = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int color = foto.getPixel(i, j);

                //Color c = new Color(foto.getPixel(i,j));
                //int cinza = (color >> 16) & 0xFF;
                //int cinza = (color >> 8) & 0xFF;
                int cinza = (color >> PARAMETRO_BYTE) & 0xFF;
                //Log.i("COR","pegou a cor:" + cinza);
                tomCinza.setPixel(i, j, Color.rgb(cinza, cinza, cinza));
                Matriz[i][j]=cinza;


            }
        }


        return tomCinza;
    }

    public void calculaMedia() {
        int media = 0,cont = 0;
        int h = foto.getHeight();
        int w = foto.getWidth();

        Matriz = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                int color = foto.getPixel(i, j);
                int cinza = (color >> PARAMETRO_BYTE) & 0xFF;
                media = media + cinza;
                cont++;

            }
        }
        media = media/cont;

        setMEDIA(media);
    }

    public void calculaMediana(){
        int h = foto.getHeight();
        int w = foto.getWidth();

        int[][] Matriz = new int[w][h];
        int[] vet = new int[(w*h)];
        int cont = 0;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                int color = foto.getPixel(i, j);
                int cinza = (color >> PARAMETRO_BYTE) & 0xFF;
                Matriz[i][j]= cinza;
                vet[cont]= Matriz [i][j];
                cont++;

            }
        }
        int mediana=0;
        for(int l = 0; l<cont; l++){

            if(vet[l] == vet[h/2]){
                mediana = vet[l];
            }
        }
        setMEDIANA(mediana);
    }

    public void calculaModa(){
        int pixel = 0;
        int[] vetor = new int[256];
        int h = foto.getHeight();
        int w = foto.getWidth();

        Matriz = new int[w][h];
        for(int i=0; i<w;i++){
            for(int j=0; j<h; j++){
                int color = foto.getPixel(i, j);
                int cinza = (color >> PARAMETRO_BYTE) & 0xFF;
                Matriz[i][j]=cinza;
            }
        }

        int moda=0;
        int aux;

        for(int i=0;i<Matriz.length;i++){
            for(int j=0; j<Matriz[0].length;j++){
                aux=Matriz[i][j];
                vetor[aux]++;
            }
        }

        aux=0;

        for(int i=0;i<vetor.length;i++){
            if(vetor[i]>aux){
                aux=vetor[i];
                moda=i;
            }
        }

        setMODA(moda);
    }

    public void calculaVariancia(){
        int pixel=0;
        int mediaPixel=0;
        int totalMatriz=0;
        int variancia=0;

        int w = foto.getWidth();
        int h = foto.getHeight();

        Matriz=new int[w][h];
        for(int i=0; i<w;i++){
            for(int j=0; j<h; j++){
                int color = foto.getPixel(i, j);
                int cinza = (color >> PARAMETRO_BYTE) & 0xFF;

                Matriz[i][j]=cinza;
                pixel = Matriz[i][j];
                mediaPixel = pixel - MEDIA;
                totalMatriz = totalMatriz +( mediaPixel * mediaPixel );
            }
        }

        variancia = totalMatriz/(w*h);

        setVARIANCIA(variancia);
    }

    public int calculaMediaMetadeDireita(){
        int media=0,cont = 0;
        int h = foto.getHeight();
        int w = foto.getWidth();

        for(int i=w/2; i<w;i++){
            for(int j=0; j<h; j++){
                int color = foto.getPixel(i, j);
                int cinza = (color >> PARAMETRO_BYTE) & 0xFF;
                media = media + cinza;
                cont++;
            }
        }
        media=media/cont;

        return media;
    }

    public int calculaMedianaMetadeEsquerda(){
        int h = foto.getHeight();
        int w = foto.getWidth();

        int[][] Matriz = new int[w][h];
        int[] vet = new int[(w*h)];
        int cont = 0;

        for (int i = 0; i < w/2; i++) {
            for (int j = 0; j < h; j++) {

                int color = foto.getPixel(i, j);
                int cinza = (color >> PARAMETRO_BYTE) & 0xFF;
                Matriz[i][j]= cinza;
                vet[cont]= Matriz [i][j];
                cont++;

            }
        }
        int mediana=0;
        for(int l = 0; l<cont; l++){

            if(vet[l] == vet[h/2]){
                mediana = vet[l];
            }
        }
        return mediana;
    }

    public int calculaModaAcimaDiagonal(){
        int pixel = 0;
        int[] vetor = new int[256];
        int h = foto.getHeight();
        int w = foto.getWidth();

        Matriz = new int[w][h];
        for(int i=0; i<w;i++){
            for(int j=0; j<h; j++){
                if (j > i) {
                    int color = foto.getPixel(i, j);
                    int cinza = (color >> PARAMETRO_BYTE) & 0xFF;
                    Matriz[i][j] = cinza;
                }
            }
        }

        int moda=0;
        int aux;

        for(int i=0;i<Matriz.length;i++){
            for(int j=0; j<Matriz[0].length;j++){
                if (j > i) {
                    aux = Matriz[i][j];
                    vetor[aux]++;
                }
            }
        }

        aux=0;

        for(int i=0;i<vetor.length;i++){
            if(vetor[i]>aux){
                aux=vetor[i];
                moda=i;
            }
        }

        return (moda);
    }

    public int calculaVarianciaAbaixoDiagonal(){
        int pixel=0;
        int mediaPixel=0;
        int totalMatriz=0;
        int variancia=0;

        int w = foto.getWidth();
        int h = foto.getHeight();

        Matriz=new int[w][h];
        for(int i=0; i<w;i++){
            for(int j=0; j<h; j++){
                if (j < i) {
                    int color = foto.getPixel(i, j);
                    int cinza = (color >> PARAMETRO_BYTE) & 0xFF;

                    Matriz[i][j] = cinza;
                    pixel = Matriz[i][j];
                    mediaPixel = pixel - MEDIA;
                    totalMatriz = totalMatriz + (mediaPixel * mediaPixel);
                }
            }
        }

        variancia = totalMatriz/(w*h);

        return (variancia);
    }



}
