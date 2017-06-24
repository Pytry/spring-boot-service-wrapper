package org.xitikit.cloud.wijee.examples.itcases;

import org.junit.Before;
import org.junit.Test;
import org.xitikit.cloud.wijee.WijeeWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertNotNull;

/**
 * Copyright Xitikit.org ${year}
 *
 * @author J. Keith "Is he wearing his headphones again?" Hoopes
 */
public class WijeeITCase{

    private String projectVersion;

    @Before
    public void setup() throws IOException{

        final Properties properties = new Properties();

        try(InputStream input =
                this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("application.properties")){

            assertNotNull(input);
            properties.load(input);
            projectVersion = properties.getProperty("simple-boot.version", null);
            assertNotNull(projectVersion);
        }
    }

    @Test
    public void wrap() throws Exception{

        WijeeWrapper.build(
            "../simple-boot/target/simple-boot-" + projectVersion + ".jar",
            "simple-wijee-boot"
        ).wrap();
    }
}