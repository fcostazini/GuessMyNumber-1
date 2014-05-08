using Gamify.WebServer.Controllers;
using Microsoft.Web.WebSockets;

namespace GuessMyNumber.WebServer.Controllers
{
    public class GuessMyNumberController : GamifyController
    {
        protected override WebSocketHandler GetWebSocketHandler(string userName)
        {
            return new GuessMyNumberWebSocketHandler(userName);
        }
    }
}