package ar.com.gmn.android.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import ar.com.gmn.android.R;
import ar.com.gmn.android.core.Duelo;
import ar.com.gmn.android.core.Evaluador;
import ar.com.gmn.android.core.GameApplication;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Player;
import ar.com.gmn.android.core.Respuesta;
import ar.com.gmn.android.view.component.CustomNumberPicker;
import ar.com.gmn.android.view.component.TRRespuesta;

public class DuelFragment extends Fragment {
    public final static int STYLE = 1;
    private Evaluador e;
    private CustomNumberPicker digit1;
    private CustomNumberPicker digit2;
    private CustomNumberPicker digit3;
    private CustomNumberPicker digit4;
    private ImageView probar;
    private Integer turno = 0;
    private Numero codigo;
    private View container;
    private Typeface type;
    private ImageView me;
    private TextView him;
    private Duelo duelo;

    protected void addRespuestaP1(Respuesta r) {
        LinearLayout tResultados = (LinearLayout) container
                .findViewById(R.id.results_me);
        TRRespuesta trRespuesta = new TRRespuesta(container.getContext(), r);
        trRespuesta.setTextFont(type);
        tResultados.addView(trRespuesta);

    }

    protected void addRespuestaP2(Respuesta r) {
        LinearLayout tResultados = (LinearLayout) container
                .findViewById(R.id.results_him);
        TRRespuesta trRespuesta = new TRRespuesta(container.getContext(), r);
        trRespuesta.setTextAppearence(R.style.ResultadoDuelo);
        trRespuesta.setTextFont(type);
        tResultados.addView(trRespuesta);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c,
                             Bundle savedInstanceState) {
        Player user;
        user = ((GameApplication) getActivity().getApplication()).getUser();

        this.container = inflater.inflate(R.layout.fragment_duel, null);

        this.me = (ImageView) this.container.findViewById(R.id.meDuel);
        new DownloadImageTask(this.me).execute(user.getFoto());


        this.him = (TextView) this.container.findViewById(R.id.himDuel);


        codigo = Numero.getRandom(4);
        e = new Evaluador(codigo);
        this.type = Typeface.createFromAsset(
                container.getContext().getAssets(), "fonts/HandWrite.ttf");
        digit1 = (CustomNumberPicker) this.container.findViewById(R.id.digit1);
        digit2 = (CustomNumberPicker) this.container.findViewById(R.id.digit2);
        digit3 = (CustomNumberPicker) this.container.findViewById(R.id.digit3);
        digit4 = (CustomNumberPicker) this.container.findViewById(R.id.digit4);


        for (Respuesta r : duelo.getRespuestasP1()) {
            addRespuestaP1(r);
        }
        this.him.setText(duelo.getP2().getNombre());
        for (Respuesta r : duelo.getRespuestasP2()) {
            addRespuestaP2(r);
        }
        this.him.setTypeface(type);

        probar = (ImageView) container.findViewById(R.id.prueba);


        probar.setBackgroundResource(R.drawable.downarrow);
        // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40,
        // 120);
        probar.getBackground().setColorFilter(0x80000000,
                PorterDuff.Mode.SRC_ATOP);

        probar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Numero n = new Numero();
                n.set(1, digit1.getValue());
                n.set(2, digit2.getValue());
                n.set(3, digit3.getValue());
                n.set(4, digit4.getValue());

                if (n.tieneRepetidos()) {

                    CharSequence text = getResources().getString(R.string.repetidos);
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(v.getContext(), text, duration);
                    toast.show();
                } else {
                    turno++;
                    Respuesta r = e.evaluar(n);
                    addRespuestaP1(r);
                    if (r.resuelto()) {
                        digit1.setCorrecto();
                        digit2.setCorrecto();
                        digit3.setCorrecto();
                        digit4.setCorrecto();

                        probar.setEnabled(false);
                    } else {
//						if (turno >= 7) {
//							digit1.setIncorrecto(codigo.get(1));
//							digit2.setIncorrecto(codigo.get(2));
//							digit3.setIncorrecto(codigo.get(3));
//							digit4.setIncorrecto(codigo.get(4));
//							probar.setText("X");
//							probar.setEnabled(false);
//						}
                    }

                }
            }

        });

        return this.container;
    }


    public void setDuelo(Duelo duelo) {
        this.duelo = duelo;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_duel_fragment, container,
                    false);
            return rootView;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }


    }

}
