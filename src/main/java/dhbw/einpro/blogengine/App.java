package dhbw.einpro.blogengine;

import dhbw.einpro.blogengine.exceptions.DuplicateEmailException;
import dhbw.einpro.blogengine.exceptions.DuplicateUserException;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;
import dhbw.einpro.blogengine.impl.BlogEngine;
import dhbw.einpro.blogengine.impl.Post;
import dhbw.einpro.blogengine.impl.User;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

//Achtung: Diese Datei darf nicht editiert werden!
/**
 * Diese Klasse ist eine Testanwendung der Blogengine.
 */
public class App
{


    
    public static void main(String[] argv)
            throws DuplicateUserException, DuplicateEmailException, IllegalOperationException, UserNotFoundException {

        System.out.println("Anwendung wird gestartet");
        
        IBlogEngine l_blogEngine = BlogEngineHelper.getDefaultBlogEngine();
        IUser l_user1 = BlogEngineHelper.createUser("Max", "Mustermann", "max.mustermann@mail.de");
        IUser l_user2 = BlogEngineHelper.createUser("Nathalie", "MusterFrau", "nathalie.musterfrau@mail.de");;

        l_blogEngine.addUser(l_user1);
        l_blogEngine.addUser(l_user2);

        IPost l_post1 = BlogEngineHelper.createPost("Titel 1", "Inhalt 1", l_user1);
        IPost l_post2 = BlogEngineHelper.createPost("Titel 2", "Inhalt 2", l_user2);
        IPost l_post3 = BlogEngineHelper.createPost("Titel 3", "Inhalt 3", l_user2);

        l_blogEngine.addPost(l_post1);
        l_blogEngine.addPost(l_post2);
        l_blogEngine.addPost(l_post3);
        
        System.out.println("Anwendung konnte gestartet werden!");
    }
}
