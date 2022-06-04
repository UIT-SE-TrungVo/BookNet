package com.booknet.system.network;

import com.booknet.config.network.NetworkConnectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class NetworkConfigLoader {
    private static final Logger logger = LoggerFactory.getLogger(NetworkConfigLoader.class);

    public static Properties getConfig(NetworkConnectType networkConfig) {
        var properties = new Properties();
        var fileName = getNetworkConfigFilePath(networkConfig);
        try (FileInputStream fis = new FileInputStream(fileName)) {
            properties.load(fis);
        } catch (FileNotFoundException ex) {
            logger.error("Config load failed: File not found");
            ex.printStackTrace();
        } catch (IOException ex) {
            logger.error("Config load failed: IO Error");
            ex.printStackTrace();
        }
        logger.info("Get config success !!!");
        return properties;
    }

    public static String getNetworkConfigFilePath(NetworkConnectType networkConfig) {
        switch (networkConfig) {
            case LOCAL:
                return "src/main/resources/local.config";
            case DEV_1:
                return "src/main/resources/dev_1.config";
            case DEV_2:
                return "src/main/resources/dev_2.config";
            default:
                logger.error("Unsupported network config: {}", networkConfig);
                return "";
        }
    }
}