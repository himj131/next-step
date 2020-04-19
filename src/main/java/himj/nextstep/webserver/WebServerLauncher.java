package himj.nextstep.webserver;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class WebServerLauncher {
    public static void main(String[] args) throws LifecycleException {
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.parseInt(webPort));

        Connector connector = tomcat.getConnector();
        connector.setURIEncoding("UTF-8");

        Context context = tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        File additionalWebInfClasses = new File("target/classes");
        StandardRoot standardRoot = new StandardRoot(context);
        standardRoot.addPreResources(new DirResourceSet(standardRoot, "/WEB-INF/classes",
                                                 additionalWebInfClasses.getAbsolutePath(), "/"));
        context.setResources(standardRoot);


        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());


        tomcat.start();
        tomcat.getServer().await();
    }
}
