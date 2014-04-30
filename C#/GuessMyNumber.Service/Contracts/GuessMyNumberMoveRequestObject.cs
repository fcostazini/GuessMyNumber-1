using Gamify.Service.Contracts.Requests;

namespace GuessMyNumber.Server.Contracts
{
    public class GuessMyNumberMoveRequestObject : IRequestObject
    {
        public string SessionId { get; set; }

        public string PlayerName { get; set; }

        public string Number { get; set; }
    }
}
