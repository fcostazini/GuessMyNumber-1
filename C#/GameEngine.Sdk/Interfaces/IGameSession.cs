namespace GameEngine.Sdk.Interfaces
{
    public interface ISingleGameSession
    {
		bool IsReady { get; }

        IGamePlayer Player1 { get; }

        IGamePlayer Player2 { get; }

		void AddPlayer (IGamePlayer player);

		void RemovePlayer (string playerName);
    }
}
