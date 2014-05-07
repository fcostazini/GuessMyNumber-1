using Gamify.Sdk;
using GuessMyNumber.Core.Interfaces;
using System;

namespace GuessMyNumber.Core.Game
{
    public class GuessMyNumberPlayer : ISessionGamePlayer<INumber, IAttemptResult>
    {
        private readonly INumberComparer numberComparer;

        public IGamePlayer Information { get; set; }

        public bool IsReady { get; set; }

        public bool NeedsToMove { get; set; }

        public INumber Number { get; private set; }

        public GuessMyNumberPlayer(IGamePlayer information)
        {
            this.numberComparer = new NumberComparer();
            this.Information = information;
        }

        public void AssignNumber(INumber number)
        {
            if (this.Number != default(INumber))
            {
                var errorMessage = string.Format("The number of Player {0} can't be changed once assigned", this.Information.UserName);

                throw new ApplicationException(errorMessage);
            }

            this.Number = number;
        }

        public IGameMoveResponse<IAttemptResult> ProcessMove(IGameMove<INumber> move)
        {
            if (this.Number == default(INumber))
            {
                var errorMessage = string.Format("The player {0} is not ready because it doesn't have a number assigned", this.Information.UserName);

                throw new ApplicationException(errorMessage);
            }

            var triedNumber = move.MoveObject;
            var result = this.numberComparer.Compare(this.Number, triedNumber);

            return new GuessMyNumberResponse(result);
        }
    }
}
