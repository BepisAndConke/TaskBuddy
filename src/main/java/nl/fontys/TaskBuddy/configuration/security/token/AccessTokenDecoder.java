package nl.fontys.TaskBuddy.configuration.security.token;

import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
