using Gamify.Service.Contracts.Notifications;

namespace GuessMyNumber.Server.Contracts
{
    public class GuessMyNumberMoveResultNotificationObject : INotificationObject
    {
        public string Message
        {
            get
            {
                return string.Format("The player {0} has informed {1} Goods, {2} Regulars and {3} Bads for the number {4}", 
                    this.PlayerName, this.Goods, this.Regulars, this.Bads, this.Number);
            }
        }

        public string SessionId { get; set; }

        public string PlayerName { get; set; }

        public string Number { get; set; }

        public int Goods { get; set; }

        public int Regulars { get; set; }

        public int Bads { get; set; }
    }
}
