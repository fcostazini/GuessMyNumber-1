using System;

namespace GameEngine.Sdk.Interfaces
{
    public interface IGamePlayer
    {
		event EventHandler<GameMoveReceivedEventArgs> ReceiveMove;

		string Name { get; }

		bool IsPlaying { get; }
    }
}
