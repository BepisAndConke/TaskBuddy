package nl.fontys.TaskBuddy.configuration.security.token;

import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
