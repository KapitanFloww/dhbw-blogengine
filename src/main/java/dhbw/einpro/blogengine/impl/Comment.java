package dhbw.einpro.blogengine.impl;

import java.time.LocalDateTime;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse implementiert einen Kommentar zu einem Post.
 */
public class Comment implements IComment
{
    private String content;
    private IUser author;
    private IPost post;

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String p_content) throws IllegalOperationException {
        if (p_content.length() < 256) {
            this.content = p_content;
        }
        else {
            throw new IllegalOperationException("Content has more than 256 characters");
        }
    }

    @Override
    public IUser getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(IUser p_author) {
        this.author = p_author;
    }

    @Override
    public void setPost(IPost p_post) {
        this.post = p_post;
    }


}
