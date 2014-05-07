using Gamify.Service;
using Gamify.WebServer;
using GuessMyNumber.Core.Game;
using GuessMyNumber.Server;

namespace GuessMyNumber.WebServer
{
    public class GuessMyNumberWebSocketHandler : GamifyWebSocketHandler
    {
        public GuessMyNumberWebSocketHandler(string userName)
            : base(userName)
        {
        }

        protected override IGamifyService IntializeGamifyService()
        {
            var gameController = new GuessMyNumberGameController();

            return new GuessMyNumberService(gameController);
        }
    }
}