package ar.com.gmn.android.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Respuesta;
import ar.com.gmn.android.service.observers.IDuelManagerObserver;
import ar.com.gmn.android.service.observers.IGamePlayObserver;
import ar.com.gmn.android.service.observers.IMessageObserver;

public class DuelService implements IMessageObserver{
	private IDuelManagerObserver duelObserver;
	private Map<String,IGamePlayObserver> gamePlayObservers;
	private String playerId;
	private WebSocketConnector connector;

	public DuelService(String playerId) {
		this.playerId = playerId;
		
		this.gamePlayObservers = new HashMap<String,IGamePlayObserver>();
	}

	public void solicitarDuelo(IDuelManagerObserver dObserver,IDuelManagerObserver observer,String oponente, Numero numero) {
		connector.sendMessage("nuevo duelo solicitado Oponente: " + oponente
				+ " Numero: " + numero);
		if(duelObserver== null){
			duelObserver = dObserver;
		}
		
	}
	public void nuevoIntento(IGamePlayObserver gpObserver,String oponente, Numero numero) {
		connector.evaluarNumero(oponente,numero);
		if(!gamePlayObservers.containsKey(oponente)){
			gamePlayObservers.put(oponente, gpObserver);
		}
	}

	@Override
	public void actulizar(Message m) {
		switch (m.getType()) {
		case Message.RESPUESTA_INTENTO:
			if(gamePlayObservers.containsKey(m.getOponente())){
				this.gamePlayObservers.get(m.getOponente()).actualizarIntento(new Respuesta());
			}
			break;

		default:
			break;
		}
		
	}
	
	

}
