using System.Runtime.Serialization;
using GameEngine.Sdk.Interfaces;

namespace GameEngine.Sdk.Contracts
{
	[DataContract]
	public class GameMoveRequest
	{
		[DataMember]
		private object gameMoveRequest;

		public IGameMove<T> GetMove<T>()
		{
			return this.gameMoveRequest as IGameMove<T>;
		}

		public static GameMoveRequest Create<T>(IGameMove<T> genericMove)
		{
			var moveRequest = new GameMoveRequest ();

			moveRequest.gameMoveRequest = genericMove;

			return moveRequest;
		}
	}
}
