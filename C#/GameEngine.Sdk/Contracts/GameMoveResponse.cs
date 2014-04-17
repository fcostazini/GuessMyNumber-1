using System.Runtime.Serialization;
using GameEngine.Sdk.Interfaces;

namespace GameEngine.Sdk.Contracts
{
	[DataContract]
	public class GameMoveResponse
	{
		[DataMember]
		private object gameMoveResponse;

		public IGameMoveResponse<T> GetResponse<T>()
		{
			return this.gameMoveResponse as IGameMoveResponse<T>;
		}

		public static GameMoveResponse Create<T>(IGameMoveResponse<T> genericMoveResponse)
		{
			var moveResponse = new GameMoveResponse ();

			moveResponse.gameMoveResponse = genericMoveResponse;

			return moveResponse;
		}
	}
}
