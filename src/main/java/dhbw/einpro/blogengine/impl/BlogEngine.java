package dhbw.einpro.blogengine.impl;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import dhbw.einpro.blogengine.exceptions.DuplicateEmailException;
import dhbw.einpro.blogengine.exceptions.DuplicateUserException;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.PostNotFoundException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;
import lombok.AllArgsConstructor;

/**
 * Klasse implementiert die Funktionalität einer Blog Engine.
 */
@AllArgsConstructor
public class BlogEngine implements IBlogEngine
{
    private List<IUser> registeredUsers;
    private List<IPost> posts;
    private static int nextPostId = 1;

    @Override
    public int size() {
        return this.registeredUsers.size();
    }

    //TODO wann soll false ausgeben werden?
    @Override
    public boolean addUser(IUser p_user) throws DuplicateEmailException, DuplicateUserException {
        if(p_user == null) {
            return false;
        }
        if(registeredUsers.contains(p_user)) {
            throw new DuplicateUserException("User already exists");
        }
        if(existsMail(p_user)) {
            throw new DuplicateEmailException("E-Mail already taken");
        }
        this.registeredUsers.add(p_user);
        return true;
    }

    private boolean existsMail(IUser p_user) {
        return registeredUsers.stream()
                .anyMatch(user -> user.getEmail().equals(p_user.getEmail()));
    }

    @Override
    public boolean removeUser(IUser p_user) {
        if (p_user == null) {
            return false;
        }
        if (!registeredUsers.contains(p_user)) {
            return false;
        }
        this.registeredUsers.remove(p_user);
        return true;
    }

    @Override
    public int addPost(IPost p_post) throws UserNotFoundException {
        p_post.setId(nextPostId);
        if(!registeredUsers.contains(p_post.getAuthor())) {
            throw new UserNotFoundException("User not found");
        }
        if(!commentAuthorsValid(p_post)) {
            throw new UserNotFoundException("Comment user not found");
        }
        this.posts.add(p_post);
        return nextPostId++;
    }

    private boolean commentAuthorsValid(IPost p_post) {
        return p_post.getComments().stream()
                .map(IComment::getAuthor)
                .allMatch(user -> registeredUsers.contains(user));
    }

    @Override
    public void removePost(IUser p_author, int p_postId) throws PostNotFoundException, IllegalOperationException {

    }

    @Override
    public List<IPost> getPosts() {
        return null;
    }

    @Override
    public List<IPost> findPostsByAuthor(IUser p_author) {
        return null;
    }

    @Override
    public IPost findPostById(int p_postId) {
        return null;
    }

    @Override
    public boolean containsPost(int p_postId) {
        return false;
    }

    @Override
    public boolean containsUser(String p_email) {
        return false;
    }

    @Override
    public IUser findUserByEmail(String p_email) throws UserNotFoundException {
        IUser foundUser = registeredUsers.stream()
                .filter(user -> user.getEmail().equals(p_email));
        return null;
    }

    @Override
    public List<IPost> sortPostsByTitle() {
        return null;
    }

    @Override
    public List<IPost> findPostsByTitle(String title) {
        return null;
    }
}
