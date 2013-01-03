package org.scribe.builder.api;

import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.*;
import org.scribe.utils.*;

/**
 * API for https://www.dailycred.com/.
 * @author Paul Wheeler, https://github.com/pwheel
 */
public class DailyCredApi extends DefaultApi20
{
    private static final String AUTHORIZE_URL = "https://www.dailycred.com/oauth/gateway?client_id=%s";
    private static final String AUTHORIZE_URL_WITH_REDIRECT = AUTHORIZE_URL + "&redirect_uri=%s";

    @Override
    public String getAccessTokenEndpoint()
    {
        return "https://www.dailycred.com/oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config)
    {
        if (config.getCallback() == null) {
            return String.format(AUTHORIZE_URL, config.getApiKey());
        } else {
            return String.format(AUTHORIZE_URL_WITH_REDIRECT, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
        }
    }

    @Override
    public AccessTokenExtractor getAccessTokenExtractor()
    {
        return new JsonTokenExtractor();
    }
}
