package edu.upc.dsa;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jersey.listing.ApiListingResourceJSON;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

import edu.upc.dsa.orm.util.ObjectHelper;
import edu.upc.dsa.orm.dao.*;

/**
 * Main class.
 *
 */
public class Main {

    private static final boolean remote_machine = false;

    private static final String remote_ip = "147.83.7.207";
    private static final int remote_port = 8080;

    private static final String local_ip = "localhost";
    private static final int local_port = 8080;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.dsa.orm.services");

        rc.register(io.swagger.jaxrs.listing.ApiListingResource.class);
        rc.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();

        if (remote_machine) {
            beanConfig.setHost(remote_ip + ":" + local_port);
        } else {
            beanConfig.setHost(local_ip + ":" + remote_port);
        }

        beanConfig.setBasePath("/dsaApp");
        beanConfig.setContact("support@grup5dsa.com");
        beanConfig.setDescription("REST API for GAME G5 Manager");
        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        beanConfig.setResourcePackage("edu.upc.dsa.orm.services");
        beanConfig.setTermsOfServiceUrl("http://www.example.com/resources/eula");
        beanConfig.setTitle("REST API");
        beanConfig.setVersion("1.0.0");
        beanConfig.setScan(true);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application
        if (remote_machine) {
            return GrizzlyHttpServerFactory.createHttpServer(URI.create("http://" + remote_ip + ":" + remote_port + "/dsaApp/"), rc);
        } else {
            return GrizzlyHttpServerFactory.createHttpServer(URI.create("http://" + local_ip + ":" + local_port + "/dsaApp/"), rc);
        }
    }


    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        if (remote_machine) {
        System.out.println("Jersey app started with WADL available at "
                + "http://" + remote_ip + ":" + remote_port + "/dsaApp/application.wadl\nHit enter to stop it...");
        } else {
            System.out.println("Jersey app started with WADL available at "
                    + "http://" + local_ip + ":" + local_port + "/dsaApp/application.wadl\nHit enter to stop it...");
        }

        System.in.read();
        server.stop();

    }
}

