package nl.hu.IPASS.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        packages("nl.hu.IPASS.controllers, nl.hu.IPASS.IPASS.security");
        register(RolesAllowedDynamicFeature.class);
    }
}

