package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesReader {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesReader.class);

    private PropertiesReader() {
    }

    public static Properties getPropertiesFromFile(String pathToFile) {
        Properties props = new Properties();
        try (final InputStream stream = PropertiesReader.class.getClassLoader().getResourceAsStream(pathToFile)) {
            props.load(stream);
        } catch (IOException e) {
            LOG.error("Problem occurred during reading properties file", e);
        }
        return props;
    }
}
