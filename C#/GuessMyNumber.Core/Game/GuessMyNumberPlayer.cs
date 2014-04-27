using Gamify.Sdk;
using GuessMyNumber.Core.Interfaces;
using System;

namespace GuessMyNumber.Core.Game
{
    public class GuessMyNumberPlayer : IGamePlayer<INumber, IAttemptResult>
    {
        private readonly INumberComparer numberComparer;
        private INumber playerNumber;

        public bool IsPlaying { get; set; }

        public string Name { get; set; }

        public GuessMyNumberPlayer()
        {
            this.numberComparer = new NumberComparer();
        }

        public void AssignNumber(INumber number)
        {
            this.playerNumber = number;
        }

        public IGameMoveResponse<IAttemptResult> ProcessMove(IGameMove<INumber> move)
        {
            if (this.playerNumber == default(INumber))
            {
                var errorMessage = string.Format("The player {0} is not ready because it doesn't have a number assigned", this.Name);

                throw new ApplicationException(errorMessage);
            }

            var triedNumber = move.MoveObject;
            var result = this.numberComparer.Compare(this.playerNumber, triedNumber);

            return new GuessMyNumberResponse(result);
        }
    }
}
