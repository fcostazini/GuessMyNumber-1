using Gamify.Service.Contracts.Notifications;
using System.Collections.Generic;

namespace GuessMyNumber.Service.Contracts
{
    public class GuessMyNumberGameInformationNotificationObject : INotificationObject
    {
        public string Message
        {
            get
            {
                return string.Format("Game information displayed for Player {0} and {1}", this.Player1History.PlayerName, this.Player2History.PlayerName);
            }
        }

        public string SessionId { get; set; }

        public PlayerHistoryObject Player1History { get; set; }

        public PlayerHistoryObject Player2History { get; set; }
    }

    public class PlayerHistoryObject
    {
        private readonly IList<PlayerHistoryItemObject> moves;

        public string PlayerName { get; set; }

        public IEnumerable<PlayerHistoryItemObject> Moves
        {
            get
            {
                return this.moves;
            }
        }

        public PlayerHistoryObject(string playerName)
        {
            this.moves = new List<PlayerHistoryItemObject>();

            this.PlayerName = playerName;
        }

        public void AddMove(PlayerHistoryItemObject move)
        {
            this.moves.Add(move);
        }
    }

    public class PlayerHistoryItemObject
    {
        public string Number { get; set; }

        public int Goods { get; set; }

        public int Regulars { get; set; }

        public int Bads { get; set; }
    }
}
