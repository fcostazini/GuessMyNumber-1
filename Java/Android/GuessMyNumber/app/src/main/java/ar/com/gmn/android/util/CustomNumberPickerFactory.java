package ar.com.gmn.android.util;

import android.content.Context;
import android.graphics.Typeface;
import ar.com.gmn.android.R;
import ar.com.gmn.android.view.component.CustomNumberPicker;

public class CustomNumberPickerFactory {
	public final static int DUELO = 0;
	public final static int SINGLE_PLAYER = 1;

	public static CustomNumberPicker createNumberPiker(Context context, int type) {

		switch (type) {
		case CustomNumberPickerFactory.DUELO:
			return createDuelNumberPicker(context);
		case CustomNumberPickerFactory.SINGLE_PLAYER:
			return createSinglePLayerNumberPicker(context);

		default:
			return new CustomNumberPicker(context);

		}

	}

	private static CustomNumberPicker createDuelNumberPicker(Context context) {
		CustomNumberPicker cPiker = new CustomNumberPicker(context);
		Typeface tf = Typeface.createFromAsset(context.getAssets(),
				"fonts/HandWrite.ttf");
		cPiker.setButtonStyle(R.style.DueloNumberPiker_Button);
		cPiker.setNumberStyle(R.style.DueloNumberPiker_Number);
		cPiker.setNumberFrame(R.drawable.border);
		cPiker.setButtonTypeFace(tf);
		cPiker.setNumberTypeFace(tf);
		return cPiker;
	}

	private static CustomNumberPicker createSinglePLayerNumberPicker(Context context) {
		CustomNumberPicker cPiker = new CustomNumberPicker(context);
		Typeface tf = Typeface.createFromAsset(context.getAssets(),
				"fonts/EraserDust.ttf");
		cPiker.setButtonStyle(R.style.SP_NumberPiker_Button);
		cPiker.setNumberStyle(R.style.SP_NumberPiker_Number);
		cPiker.setNumberFrame(R.drawable.chalk_frame);
		cPiker.setButtonTypeFace(tf);
		cPiker.setNumberTypeFace(tf);
		return cPiker;
	}
}
