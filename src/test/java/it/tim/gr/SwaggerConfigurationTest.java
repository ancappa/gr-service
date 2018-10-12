package it.tim.gr;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Created by alongo on 02/05/18.
 */
public class SwaggerConfigurationTest {

    @Test
    public void api() throws Exception {
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
        assertNotNull(swaggerConfiguration.api());
    }

}