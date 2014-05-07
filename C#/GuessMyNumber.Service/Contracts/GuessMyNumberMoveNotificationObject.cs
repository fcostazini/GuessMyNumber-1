using Gamify.Service.Contracts.Notifications;

namespace GuessMyNumber.Server.Contracts
{
    class GuessMyNumberMoveNotificationObject : INotificationObject
    {
        public string Message
        {
            get
            {
                return string.Format("The player {0} has tried the {1} number", this.PlayerName, this.Number);
            }
        }

        public string SessionId { get; set; }

        public string PlayerName { get; set; }

        public string Number { get; set; }
    }
}
