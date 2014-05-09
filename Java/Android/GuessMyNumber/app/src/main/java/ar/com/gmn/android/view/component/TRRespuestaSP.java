package ar.com.gmn.android.view.component;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.TextView;

import ar.com.gmn.android.R;
import ar.com.gmn.android.core.Respuesta;

public class TRRespuestaSP extends TRRespuesta {


    private TextView turno;

    public TRRespuestaSP(Context context, Respuesta r) {
        super(context, r);
        turno = (TextView) findViewById(R.id.textTurno);
    }

    @Override
    public void setTextFont(Typeface type) {
        super.setTextFont(type);
        turno.setTypeface(type);
    }


    public void setTurno(int turno) {
        this.turno.setText(String.valueOf(turno));
    }

    @Override
    protected void initView(Context context) {
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.sp_respuesta_view, this);
        }
        turno = (TextView) findViewById(R.id.textTurno);
        bien = (TextView) findViewById(R.id.textBien);
        regular = (TextView) findViewById(R.id.textRegular);
        numero = (NumeroView) findViewById(R.id.textNumero);
    }

}
