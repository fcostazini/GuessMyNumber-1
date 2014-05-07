using Gamify.Server;
using Gamify.Service;
using GuessMyNumber.Core.Game;

namespace GuessMyNumber.Server
{
    public class GuessMyNumberWebSocketAsyncHandler : GamifyWebSocketAsyncHandler
    {
        protected override IGamifyService IntializeGamifyService()
        {
            var gameController = new GuessMyNumberGameController();

            return new GuessMyNumberService(gameController);
        }

        protected override void OnConnecting()
        {
            base.OnConnecting();
        }

        protected override void OnClosing(bool isClientRequest)
        {
            base.OnClosing(isClientRequest);
        }

        protected override void OnClosed()
        {
            base.OnClosed();
        }
    }
}
