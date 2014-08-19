package ar.com.gmn.android.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ar.com.gmn.android.R;
import ar.com.gmn.android.core.Evaluador;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Respuesta;
import ar.com.gmn.android.view.component.CustomNumberPicker;
import ar.com.gmn.android.view.component.TRRespuestaSP;

public class SinglePlayerFragment extends Fragment {
    public final static int STYLE = 2;
    private Evaluador e;
    private CustomNumberPicker digit1;
    private CustomNumberPicker digit2;
    private CustomNumberPicker digit3;
    private CustomNumberPicker digit4;
    private ImageView probar;
    private Integer turno = 0;
    private Numero codigo;
    private LinearLayout tResultados;
    private Typeface type;
    private View container;

    protected void addRespuesta(Respuesta r) {
        TRRespuestaSP trRespuesta = new TRRespuestaSP(this.container.getContext(), r);
        trRespuesta.setTurno(turno);
        trRespuesta.setTextAppearence(R.style.ResultadoSinglePLayer);
        trRespuesta.setTextFont(type);

        tResultados.addView(trRespuesta);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c,
                             Bundle savedInstanceState) {
        this.container = inflater.inflate(R.layout.single_player_fragment, null);
        // Remove title bar
        tResultados = (LinearLayout) this.container.findViewById(R.id.results);
        codigo = Numero.getRandom(4);
        e = new Evaluador(codigo);
        this.type = Typeface.createFromAsset(this.container.getContext()
                .getAssets(), "fonts/EraserDust.ttf");
        digit1 = (CustomNumberPicker) container.findViewById(R.id.digit1);
        digit2 = (CustomNumberPicker) container.findViewById(R.id.digit2);
        digit3 = (CustomNumberPicker) container.findViewById(R.id.digit3);
        digit4 = (CustomNumberPicker) container.findViewById(R.id.digit4);


        probar = (ImageView) container.findViewById(R.id.prueba);
        probar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Numero n = new Numero();
                n.set(1, digit1.getValue());
                n.set(2, digit2.getValue());
                n.set(3, digit3.getValue());
                n.set(4, digit4.getValue());

                if (n.tieneRepetidos()) {

                    CharSequence text = container.getResources().getString(R.string.repetidos);
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(v.getContext(), text,
                            duration);
                    toast.show();
                } else {
                    turno++;
                    Respuesta r = e.evaluar(n);
                    addRespuesta(r);
                    if (r.resuelto()) {
                        digit1.setCorrecto();
                        digit2.setCorrecto();
                        digit3.setCorrecto();
                        digit4.setCorrecto();

                        probar.setEnabled(false);
                    } else {
                        if (turno >= 7) {
                            digit1.setIncorrecto(codigo.get(1));
                            digit2.setIncorrecto(codigo.get(2));
                            digit3.setIncorrecto(codigo.get(3));
                            digit4.setIncorrecto(codigo.get(4));

                            probar.setEnabled(false);
                        }
                    }

                }
            }

        });
        return this.container;
    }


}
