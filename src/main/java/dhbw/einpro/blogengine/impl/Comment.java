package dhbw.einpro.blogengine.impl;

import java.time.LocalDateTime;

import dhbw.einpro.blogengine.exceptions.IllegalOperationException;
import dhbw.einpro.blogengine.interfaces.IComment;
import dhbw.einpro.blogengine.interfaces.IPost;
import dhbw.einpro.blogengine.interfaces.IUser;
import lombok.Data;

/**
 * Klasse implementiert einen Kommentar zu einem Post.
 */
@Data
public class Comment implements IComment
{
    private String content;
    private IUser author;
    private IPost post;

    @Override
    public void setContent(String p_content) throws IllegalOperationException {
        if(p_content.length() > 256) {
            throw new IllegalOperationException("Content has more than 256 characters");
        }
        this.content = p_content;
    }
}
