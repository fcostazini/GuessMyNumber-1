using Gamify.Sdk;
using GuessMyNumber.Core.Interfaces;
using System;

namespace GuessMyNumber.Core.Game
{
    public class GuessMyNumberPlayer : IGamePlayer<INumber, IAttemptResult>
    {
        private readonly INumberComparer numberComparer;

        public bool IsPlaying { get; set; }

        public string Name { get; set; }

        public INumber Number { get; private set; }

        public GuessMyNumberPlayer()
        {
            this.numberComparer = new NumberComparer();
        }

        public void AssignNumber(INumber number)
        {
            if (this.Number != default(INumber))
            {
                var errorMessage = string.Format("The number of Player {0} can't be changed once assigned", this.Name);

                throw new ApplicationException(errorMessage);
            }

            this.Number = number;
        }

        public IGameMoveResponse<IAttemptResult> ProcessMove(IGameMove<INumber> move)
        {
            if (this.Number == default(INumber))
            {
                var errorMessage = string.Format("The player {0} is not ready because it doesn't have a number assigned", this.Name);

                throw new ApplicationException(errorMessage);
            }

            var triedNumber = move.MoveObject;
            var result = this.numberComparer.Compare(this.Number, triedNumber);

            return new GuessMyNumberResponse(result);
        }
    }
}
