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
    public int[][] Matriz=null;

    public Bitmap converteCinza(Bitmap foto){
        int h = foto.getHeight();
        int w = foto.getWidth();

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap tomCinza = Bitmap.createBitmap(w, h, conf);;


        Matriz= new int[w][h];
        for(int i=0; i<w;i++){
            for(int j=0; j<h; j++){
                int color = foto.getPixel(i,j);

                //Color c = new Color(foto.getPixel(i,j));
                int cinza = (color >> 16) & 0xFF;
                //Log.i("COR","pegou a cor:" + cinza);
                tomCinza.setPixel(i,j,Color.rgb(cinza,cinza,cinza));
                //Matriz[i][j]=cinza;
            }
        }


        return tomCinza;
    }
}
