package com.firecod.avcm_android.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class CodificadorImagenes {

    /**
     * Este método codifica una imagen utilizando el algoritmo Base64
     * y devuelve como resultado un objeto de tipo String que representa
     * la imagen
     */
    public static String codificar(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;

    }

    /**
     * Este método recibe como parámetro un objeto de tipo String
     * que representa el código de la imagen y, devuelve
     * como resultado, la imagen generada a partir de la
     * decodificación del parámetro.
     * @param codigoImagen
     * @return
     */
    public static Bitmap decodificar(String codigoImagen)
    {
        byte[] decodedBytes = Base64.decode(codigoImagen.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

    }

}
