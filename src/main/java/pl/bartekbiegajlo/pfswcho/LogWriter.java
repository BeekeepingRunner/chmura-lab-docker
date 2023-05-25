package pl.bartekbiegajlo.pfswcho;


import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogWriter.class);

    @PostConstruct
    public void logStart() {
        LOGGER.info("Container started.");
        LOGGER.info("Author: Bartosz Biegajło.");
    }
}
