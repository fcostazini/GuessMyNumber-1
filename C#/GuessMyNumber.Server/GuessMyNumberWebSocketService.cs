using Alchemy.Classes;
using Gamify.Sdk;
using Gamify.Server.Configuration;
using Gamify.Server.Contracts.Notifications;
using Gamify.Server.Contracts.Requests;
using GuessMyNumber.Core;
using GuessMyNumber.Core.Game;
using GuessMyNumber.Core.Interfaces;
using GuessMyNumber.Server.Contracts;
using Newtonsoft.Json;
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
                .First(c => c.Value.Player.Name == openSessionRequestObject.PlayerName)
                .Value;
            var player = connectedClient.Player as GuessMyNumberPlayer;
            var playerNumber = new Number(openSessionRequestObject.AdditionalInformation);

            player.AssignNumber(playerNumber);
        }

        protected override void PostOpenSession(GameInviteNotificationObject gameInviteNotificationObject)
        {
            base.PostOpenSession(gameInviteNotificationObject);

            var connectedClient = this.connectedClients
                .First(c => c.Value.Player.Name == gameInviteNotificationObject.Player1Name)
                .Value;
            var player = connectedClient.Player as GuessMyNumberPlayer;

            gameInviteNotificationObject.AdditionalInformation = player.Number.ToString();
        }

        protected override void PreGameAccepted(GameAcceptedRequestObject gameAcceptedRequestObject)
        {
            base.PreGameAccepted(gameAcceptedRequestObject);

            var connectedClient = this.connectedClients
                .First(c => c.Value.Player.Name == gameAcceptedRequestObject.PlayerName)
                .Value;
            var player = connectedClient.Player as GuessMyNumberPlayer;
            var playerNumber = new Number(gameAcceptedRequestObject.AdditionalInformation);

            player.AssignNumber(playerNumber);
        }

        protected override void HandleGameMove(string serializedRequestObject, UserContext context)
        {
            var moveRequestObject = JsonConvert.DeserializeObject<GuessMyNumberMoveRequestObject>(serializedRequestObject);
            var number = new Number(moveRequestObject.Number);
            var move = new GuessMyNumberMove(number);
            var moveResponse = this.gameController.HandleMove<INumber, IAttemptResult>(moveRequestObject.PlayerName, moveRequestObject.SessionId, move);

            var gameMoveNotificationObject = new GuessMyNumberMoveNotificationObject
            {
                SessionId = moveRequestObject.SessionId,
                PlayerName = moveRequestObject.PlayerName,
                Number = number.ToString()
            };
            var currentSession = this.gameController.GameSessions.First(s => s.Id == moveRequestObject.SessionId);
            var destinationPlayerName = currentSession.Player1.Name == moveRequestObject.PlayerName ? 
                currentSession.Player2.Name : 
                currentSession.Player1.Name;
            var destinationClient = this.connectedClients
                .First(c => c.Value.Player.Name == destinationPlayerName)
                .Value;

            this.SendNotification(GameNotificationType.GameMove, gameMoveNotificationObject, destinationClient);

            var gameMoveResultNotificationObject = new GuessMyNumberMoveResultNotificationObject
            {
                SessionId = moveRequestObject.SessionId,
                PlayerName = destinationPlayerName,
                Number = number.ToString(),
                Goods = moveResponse.MoveResponseObject.Goods,
                Regulars = moveResponse.MoveResponseObject.Regulars,
                Bads = moveResponse.MoveResponseObject.Bads
            };
            var originClient = this.connectedClients
                .First(c => c.Value.Player.Name == moveRequestObject.PlayerName)
                .Value;

            this.SendNotification(GameNotificationType.GameMoveResult, gameMoveResultNotificationObject, originClient);
        }

        public override void OnSend(UserContext context)
        {
            base.OnSend(context);
        }
    }
}
