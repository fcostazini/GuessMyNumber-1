package ar.com.gmn.android.view.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import ar.com.gmn.android.R;

public class TRRespuestaSPHead extends TRRespuestaHead {


    private TextView turno;

    public TRRespuestaSPHead(Context context, AttributeSet attr) {
        super(context, attr);
        turno = (TextView) findViewById(R.id.textTurno);
    }

    @Override
    public void setTextFont(Typeface type) {
        super.setTextFont(type);
        turno.setTypeface(type);
    }

    @Override
    protected void initView(Context context) {
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.sp_respuesta_header, this);
        }
        turno = (TextView) findViewById(R.id.textTurno);
        bien = (TextView) findViewById(R.id.textBien);
        regular = (TextView) findViewById(R.id.textRegular);
        numero = (TextView) findViewById(R.id.textNumero);
    }

}
