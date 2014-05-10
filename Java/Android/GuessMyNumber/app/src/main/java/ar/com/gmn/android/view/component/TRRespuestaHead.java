package ar.com.gmn.android.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import ar.com.gmn.android.R;
import ar.com.gmn.android.fragment.DuelFragment;
import ar.com.gmn.android.fragment.SinglePlayerFragment;

public class TRRespuestaHead extends LinearLayout {

    protected TextView numero;
    protected TextView bien;
    protected TextView regular;

    public TRRespuestaHead(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        TypedArray a = getStyleable(context, attrs);
        if (!this.isInEditMode()) {
            for (int i = 0; i < a.getIndexCount(); ++i) {

                int attr = a.getIndex(i);
                switch (attr) {

                    case R.styleable.NumeroView_styleId:
                        switch (a.getInteger(attr, -1)) {
                            case DuelFragment.STYLE:
                                setTextAppearance(this.getContext(), R.style.ResultadoDuelo);
                                this.setTextFont(Typeface.createFromAsset(context.getAssets(),
                                        "fonts/HandWrite.ttf"));

                                break;
                            case SinglePlayerFragment.STYLE:
                                setTextAppearance(this.getContext(), R.style.ResultadoSinglePLayer);
                                this.setTextFont(Typeface.createFromAsset(context.getAssets(),
                                        "fonts/EraserDust.ttf"));

                                break;

                        }
                        break;


                }
            }

            a.recycle();
        }
    }


    public TRRespuestaHead(Context context) {
        super(context);
        initView(context);
    }

    protected TypedArray getStyleable(Context context, AttributeSet attrs) {
        return context.obtainStyledAttributes(attrs, R.styleable.TRRespuestaHead);
    }

    protected void setTextAppearance(Context context, int style) {
        this.bien.setTextAppearance(context, style);
        this.numero.setTextAppearance(context, style);
        this.regular.setTextAppearance(context, style);
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


}
