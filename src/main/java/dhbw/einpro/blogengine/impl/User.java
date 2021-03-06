package dhbw.einpro.blogengine.impl;

import dhbw.einpro.blogengine.interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Klasse enthält Informationen zu einem Benutzer des Blog-Systems
 *
 * @author rbimaz
 */
@Data
@AllArgsConstructor
public class User implements IUser{
    private String firstName;
    private String lastName;
    private String email;
}
