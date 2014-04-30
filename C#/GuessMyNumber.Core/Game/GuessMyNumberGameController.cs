using Gamify.Sdk;

namespace GuessMyNumber.Core.Game
{
    public class GuessMyNumberGameController : GamifyGameController
    {
        protected override ISessionGamePlayerBase GetSessionPlayer(IGamePlayer player)
        {
            return new GuessMyNumberPlayer(player);
        }
    }
}
