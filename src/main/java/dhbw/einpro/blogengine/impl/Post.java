package dhbw.einpro.blogengine.impl;

import java.util.List;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;
import lombok.Data;

/**
 * Die Klasse implementiert einen Post im Blog-System.
 */
@Data
public class Post implements IPost
{

    private int id;
    private String title;
    private String content;

    private IUser author;

    private List<IUser> likes;
    private List<IUser> disLikes;
    private int score;

    private List<IComment> comments;

    public Post(String titel, String content, IUser author) {
        this.title = titel;
        this.content = content;
        this.author = author;
    }

    @Override
    public void addComment(IComment p_comment) throws IllegalOperationException {
        if(this.getAuthor().equals(p_comment.getAuthor())) {
            throw new IllegalOperationException("Post author must not be comment author");
        }
        this.comments.add(p_comment);
    }

    @Override
    public void removeComment(IUser p_user, IComment p_comment) throws IllegalOperationException {
        if(p_user != null) {
            if(!p_comment.getAuthor().equals(p_user)) {
                throw new IllegalOperationException("The given user is not author of the comment");
            }
        }
        this.comments.remove(p_comment);
    }

    @Override
    public void like(IUser p_person) throws IllegalOperationException {
        if(p_person == null) {
            throw new IllegalOperationException("The user cant be null");
        }
        if(this.getAuthor().equals(p_person)) {
            throw new IllegalOperationException("This post cant be liked because the user is the author of the post");
        }
        if(!likes.contains(p_person)) {
            likes.add(p_person);
        }
    }

    @Override
    public void disLike(IUser p_person) throws IllegalOperationException {
        if(p_person == null) {
            throw new IllegalOperationException("Person must not be null");
        }
        if(this.getAuthor().equals(p_person)) {
            throw new IllegalOperationException("Person must not be the author");
        }
        if(!this.disLikes.contains(p_person)) {
            this.disLikes.add(p_person);
        }
    }
}
