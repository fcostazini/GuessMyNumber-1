using Alchemy.Classes;
using Gamify.Sdk;
using Gamify.Server.Configuration;
using Gamify.Server.Contracts.Notifications;
using Gamify.Server.Contracts.Requests;
using GuessMyNumber.Core;
using GuessMyNumber.Core.Game;
using System;
using System.Linq;
using WebSocketsTest.Server.Services;

namespace GuessMyNumber.Server
{
    public class GuessMyNumberWebSocketService : GamifyWebSocketService
    {
        public GuessMyNumberWebSocketService(IGamifyConfiguration configuration, IGameController controller)
            : base(configuration, controller)
        {
        }

        protected override IGamePlayerBase GetNewPlayer(UserConnectRequestObject userConnectObject, UserContext context)
        {
            return new GuessMyNumberPlayer
            {
                Name = userConnectObject.PlayerName
            };
        }

        protected override void PreOpenSession(OpenSessionRequestObject openSessionRequestObject)
        {
            base.PreOpenSession(openSessionRequestObject);

            var connectedClient = this.connectedClients
                .FirstOrDefault(c => c.Value.Player.Name == openSessionRequestObject.PlayerName)
                .Value;
            var player = connectedClient.Player as GuessMyNumberPlayer;
            var playerNumber = new Number(openSessionRequestObject.AdditionalInformation);

            player.AssignNumber(playerNumber);
        }

        protected override void HandleGameMove(string serializedRequestObject, UserContext context)
        {
            throw new NotImplementedException();
        }

        public override void OnSend(UserContext context)
        {
            base.OnSend(context);
        }
    }
}
