package classes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Properties;

public class InitVariables implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        final String props = "/protocol.properties";
        final Properties propsFromFile = new Properties();
        try {
            propsFromFile.load(getClass().getResourceAsStream(props));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        for (String prop : propsFromFile.stringPropertyNames()) {
            if (System.getProperty(prop) == null) {
                System.setProperty(prop, propsFromFile.getProperty(prop));
            }
        }
    }
}