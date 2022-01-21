package dhbw.einpro.blogengine.impl;

import java.util.*;

import dhbw.einpro.blogengine.exceptions.DuplicateEmailException;
import dhbw.einpro.blogengine.exceptions.DuplicateUserException;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.PostNotFoundException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse implementiert die Funktionalität einer Blog Engine.
 */
public class BlogEngine implements IBlogEngine
{
    private List<IUser> registeredUsers;
    @Override
    public int size() {

        return this.registeredUsers.size();
    }

    @Override
    public boolean addUser(IUser p_user) throws DuplicateEmailException, DuplicateUserException {
        if(p_user == null) {
            return false;
        }
        if(registeredUsers.contains(p_user)) {
            throw new DuplicateUserException("User already exists.");
        }

        if(existMail(p_user.getEmail())){
            throw new DuplicateEmailException("E-Mail already taken");
        }

        this.registeredUsers.add(p_user);
        return true;
    }

    public boolean existMail(String email) {
        return registeredUsers.stream()
                .anyMatch(iUser -> iUser.getEmail().equals(email));
    }

    @Override
    public boolean removeUser(IUser p_user) {
        return false;
    }

    @Override
    public int addPost(IPost p_post) throws UserNotFoundException {
        return 0;
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
        Post.
    }

    @Override
    public boolean containsPost(int p_postId) {
        return false;
    }

    @Override
    public boolean containsUser(String p_email) {

        if (existMail(p_email)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public IUser findUserByEmail(String p_email) throws UserNotFoundException {
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
