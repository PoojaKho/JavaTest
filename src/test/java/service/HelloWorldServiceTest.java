package service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloWorldServiceTest {

    @Test
    public void testPrintMessage() {
        HelloWorldService service = new HelloWorldService();
        assertTrue(service.printMessage("print").getClass().getTypeName().equalsIgnoreCase("java.lang.String"));
        assertEquals(service.printMessage("print"), "Hello print!");
    }
}
