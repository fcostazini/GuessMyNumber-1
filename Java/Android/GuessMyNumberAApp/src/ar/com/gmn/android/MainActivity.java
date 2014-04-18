package ar.com.gmn.android;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.os.Build;
import ar.com.gmn.android.core.Evaluador;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Respuesta;
import ar.com.gmn.android.view.component.TRRespuesta;

public class MainActivity extends ActionBarActivity {
	private Evaluador e;

	private Integer turno =0;
	protected void addRespuesta(Respuesta r) {
		TableLayout tResultados = (TableLayout)findViewById(R.id.results);
		TRRespuesta trRespuesta= new TRRespuesta(this, r);
		trRespuesta.setTurno(turno);
		tResultados.addView(trRespuesta);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		e = new Evaluador(new Numero("1572"));

		NumberPicker digit1 = (NumberPicker)findViewById(R.id.numberPicker1);
		digit1.setMinValue(0);
		digit1.setMaxValue(9);
		digit1.setWrapSelectorWheel(true);
		
		
		
    	NumberPicker digit2 = (NumberPicker)findViewById(R.id.numberPicker2);
    	digit2.setMinValue(0);
		digit2.setMaxValue(9);
		digit2.setWrapSelectorWheel(true);
		
    	NumberPicker digit3 = (NumberPicker)findViewById(R.id.numberPicker3);
    	digit3.setMinValue(0);
		digit3.setMaxValue(9);
		digit3.setWrapSelectorWheel(true);
		
		
    	NumberPicker digit4 = (NumberPicker)findViewById(R.id.numberPicker4);
    	digit4.setMinValue(0);
		digit4.setMaxValue(9);
		digit4.setWrapSelectorWheel(true);
		
//		
//		
		final Button button = (Button) findViewById(R.id.probar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	turno++;
            	NumberPicker digit1 = (NumberPicker)findViewById(R.id.numberPicker1);
            	NumberPicker digit2 = (NumberPicker)findViewById(R.id.numberPicker2);
            	NumberPicker digit3 = (NumberPicker)findViewById(R.id.numberPicker3);
            	NumberPicker digit4 = (NumberPicker)findViewById(R.id.numberPicker4);
            	Numero n = new Numero();
            	n.set(1,digit1.getValue());
            	n.set(2,digit2.getValue());
            	n.set(3,digit3.getValue());
            	n.set(4,digit4.getValue());
//            	
            	Respuesta r = e.evaluar(n);
            	addRespuesta(r);
            	if(r.resuelto()){
            		findViewById(R.id.winner).setVisibility(View.VISIBLE);
            		findViewById(R.id.probar).setVisibility(View.GONE );
            		findViewById(R.id.probar).setEnabled(false);
            	}
            	
            }


        });
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
