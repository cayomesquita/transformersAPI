package org.interview.aequilibrium;

import org.glassfish.jersey.server.ResourceConfig;
import org.interview.aequilibrium.api.endpoints.TransformerEndpoint;
import org.springframework.context.annotation.Configuration;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(TransformerEndpoint.class);
    }
}
