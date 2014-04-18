using System;
using GameEngine.Sdk.Interfaces;

namespace GameEngine.Sdk
{
	public class GameMoveReceivedEventArgs : EventArgs
	{
		public IGameMove<T> GetMove<T> ()
		{
			throw new NotImplementedException ();
		}
	}
}
