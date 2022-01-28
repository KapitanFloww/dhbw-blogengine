package dhbw.einpro.blogengine.impl;

import dhbw.einpro.blogengine.exceptions.DuplicateEmailException;
import dhbw.einpro.blogengine.exceptions.DuplicateUserException;
import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.exceptions.PostNotFoundException;
import dhbw.einpro.blogengine.exceptions.UserNotFoundException;
import dhbw.einpro.blogengine.interfaces.IBlogEngine;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Klasse implementiert die Funktionalität einer Blog Engine.
 */
@NoArgsConstructor
public class BlogEngine implements IBlogEngine
{
    private final List<IUser> registeredUsers = new ArrayList<>();
    @Getter
    private final List<IPost> posts = new ArrayList<>();
    private static int nextPostId = 1;

    @Override
    public int size() {
        return registeredUsers.size();
    }

    @Override
    public boolean addUser(IUser p_user) throws DuplicateEmailException, DuplicateUserException {
        if(p_user == null) {
            return false;
        }
        //User does already exist
        if(registeredUsers.contains(p_user)) {
            throw new DuplicateUserException("User already exists");
        }
        //User's mail is already registered
        if(containsUser(p_user.getEmail())) {
            throw new DuplicateEmailException("E-Mail already taken");
        }
        registeredUsers.add(p_user);
        return true;
    }

    @Override
    public boolean removeUser(IUser p_user) {
        if (p_user == null) {
            return false;
        }
        return registeredUsers.remove(p_user);
    }

    @Override
    public int addPost(IPost p_post) throws UserNotFoundException {
        p_post.setId(nextPostId);
        //author not registered
        if(!registeredUsers.contains(p_post.getAuthor())) {
            throw new UserNotFoundException("User not found");
        }
        //check if comment authors are valid
        if(p_post.getComments() != null) {
            if(!existsAllCommentAuthors(p_post)) {
                throw new UserNotFoundException("Comment user not found");
            }
        }
        posts.add(p_post);
        return nextPostId++;
    }

    @Override
    public void removePost(IUser p_author, int p_postId) throws PostNotFoundException, IllegalOperationException {
        if(!containsPost(p_postId)) {
            throw new PostNotFoundException("No post found matching id: " + p_postId);
        }
        IPost post = findPostById(p_postId);
        if(!post.getAuthor().equals(p_author)) {
            throw new IllegalOperationException("Author does not match");
        }
        posts.remove(post);
    }

    @Override
    public IPost findPostById(int p_postId) {
        List<IPost> list = posts.stream()
                .filter(iPost -> iPost.getId() == p_postId)
                .distinct()
                .collect(Collectors.toList());
        if(list.size() != 1){
            throw new IllegalStateException("No post found or more than one posts found");
        }
        return list.get(0);
    }

    @Override
    public List<IPost> findPostsByAuthor(IUser p_author) {
        return posts.stream()
                .filter(iPost -> iPost.getAuthor().equals(p_author))
                .collect(Collectors.toList());
    }

    @Override
    public IUser findUserByEmail(String p_email) throws UserNotFoundException {
        Optional<IUser> userOptional = registeredUsers.stream()
                .filter(user -> user.getEmail().equals(p_email))
                .findFirst();
        if(userOptional.isEmpty()){
            throw  new UserNotFoundException("User not found");
        }
        return userOptional.get();
    }

    @Override
    public List<IPost> findPostsByTitle(String title) {
        return posts.stream()
                .filter(iPost -> iPost.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<IPost> sortPostsByTitle() {
        return posts.stream()
                .sorted(Comparator.comparing(IPost::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public boolean containsPost(int p_postId) {
        return posts.stream()
                .anyMatch(iPost -> iPost.getId() == p_postId);
    }

    @Override
    public boolean containsUser(String p_email) {
        return registeredUsers.stream()
                .anyMatch(iUser -> iUser.getEmail().equals(p_email));
    }

    private boolean existsAllCommentAuthors(IPost p_post) {
        return p_post.getComments().stream()
                .map(IComment::getAuthor)
                .map(IUser::getEmail)
                .allMatch(this::containsUser);
    }
}
