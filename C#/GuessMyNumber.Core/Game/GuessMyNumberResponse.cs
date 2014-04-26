using Gamify.Sdk;
using GuessMyNumber.Core.Interfaces;

namespace GuessMyNumber.Core.Game
{
	public class GuessMyNumberResponse : IGameMoveResponse<IAttemptResult>
	{
		public IAttemptResult MoveResponseObject { get; private set; }

		public GuessMyNumberResponse (IAttemptResult attemptResult)
		{
			this.MoveResponseObject = attemptResult;
		}
	}
}
