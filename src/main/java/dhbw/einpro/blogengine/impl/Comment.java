package dhbw.einpro.blogengine.impl;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

/**
 * Klasse implementiert einen Kommentar zu einem Post.
 */
@Data
public class Comment implements IComment
{
    private String content;
    private IUser author;
    @Getter(AccessLevel.NONE)
    private IPost post;

    public Comment(String content, IUser author) {
        this.content = content;
        this.author = author;
    }

    @Override
    public void setContent(String p_content) throws IllegalOperationException {
        if(p_content.length() > 256) {
            throw new IllegalOperationException("Content has more than 256 characters");
        }
        this.content = p_content;
    }
}
