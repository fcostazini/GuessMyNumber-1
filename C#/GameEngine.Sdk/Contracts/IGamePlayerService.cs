using System.ServiceModel;

namespace GameEngine.Sdk.Contracts
{
	public interface IGamePlayerService
	{
		[OperationContract(IsOneWay = true)]
		GameMoveResponse ReceiveMove<T> (string senderPlayerName, GameMoveRequest moveRequest);
	}
}
