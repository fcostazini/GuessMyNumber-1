using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(GuessMyNumber.WebClient.Startup))]
namespace GuessMyNumber.WebClient
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
