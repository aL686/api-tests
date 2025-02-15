import checkers.AlbumsCheckers;
import checkers.PostsCheckers;
import checkers.UsersCheckers;
import executors.AlbumsExecutor;
import executors.PostsExecutor;
import executors.UsersExecutor;
import provider.PostProvider;

public class BaseTest {

    public UsersExecutor usersExecutor = new UsersExecutor();
    public PostsExecutor postsExecutor = new PostsExecutor();
    public AlbumsExecutor albumsExecutor = new AlbumsExecutor();

    public UsersCheckers usersCheckers = new UsersCheckers();
    public PostsCheckers postsCheckers = new PostsCheckers();
    public AlbumsCheckers albumsCheckers = new AlbumsCheckers();

    public PostProvider provider = new PostProvider();

}
