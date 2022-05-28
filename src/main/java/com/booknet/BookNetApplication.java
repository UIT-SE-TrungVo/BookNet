package com.booknet;

import com.booknet.config.network.NetworkConfig;
import com.booknet.system.network.NetworkConfigLoader;
import com.booknet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookNetApplication {
    static final NetworkConfig netConfig = NetworkConfig.DEV_1;
    static final String encryptor = "memoicunglen";
    static final Logger logger = LoggerFactory.getLogger(BookNetApplication.class);

    public static void main(String[] args) {
        initEncryptor();
        initNetwork();
        SpringApplication.run(BookNetApplication.class, args);
    }

    private static void initEncryptor() {
        System.setProperty("jasypt.encryptor.password", encryptor);
    }

    private static void initNetwork() {
        var netConfigProps = NetworkConfigLoader.getConfig(netConfig);
        netConfigProps.forEach((key, val) -> {
            logger.info("Setting network: {} ... {}", key.toString(), val.toString());
            System.setProperty(key.toString(), val.toString());
        });
    }
}