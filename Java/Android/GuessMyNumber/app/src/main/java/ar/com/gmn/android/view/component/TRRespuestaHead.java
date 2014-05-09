package ar.com.gmn.android.view.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import ar.com.gmn.android.R;

public class TRRespuestaHead extends LinearLayout {

    protected TextView numero;
    protected TextView bien;
    protected TextView regular;


    public TRRespuestaHead(Context context, AttributeSet attr) {
        super(context, attr);
        initView(context);
    }

    protected void initView(Context context) {
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.respuesta_header, this);
        }
        bien = (TextView) findViewById(R.id.textBien);
        regular = (TextView) findViewById(R.id.textRegular);
        numero = (TextView) findViewById(R.id.textNumero);


    }


    public void setTextFont(Typeface type) {

        bien.setTypeface(type);
        regular.setTypeface(type);
        numero.setTypeface(type);
    }

    public void setTextAppearence(int style) {
        bien.setTextAppearance(this.getContext(), style);
        regular.setTextAppearance(this.getContext(), style);
        numero.setTextAppearance(this.getContext(), style);
    }


}
