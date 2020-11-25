package org.interview.aequilibrium;

import org.glassfish.jersey.server.ResourceConfig;
import org.interview.aequilibrium.api.endpoints.BattleEndpoint;
import org.interview.aequilibrium.api.endpoints.TransformerEndpoint;
import org.springframework.context.annotation.Configuration;
import javax.ws.rs.ApplicationPath;

/**
 * Jersey config.
 */
@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    /**
     * Instantiates a new Jersey config and register the endpoints.
     */
    public JerseyConfig() {
        register(TransformerEndpoint.class);
        register(BattleEndpoint.class);
    }
}
