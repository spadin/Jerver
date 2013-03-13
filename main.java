import com.jerver.http.route.Router;
import com.jerver.http.server.Server;

class Jerver {
    private static final Router router = Router.INSTANCE;
    public static void main(String[]args) {
        router.setPublicDirectory("resources");
        router.addRoute("GET", "/hello", "Hello World!");
        router.addRoute("GET", "/goodbye", "So long.");

        Server server = new Server(9999);
    }
}