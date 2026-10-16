package src.tests;

import org.junit.*;
import src.core.controller.Controller;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class ControllerTest {

    private Controller  controller;

    @Test
    public void enableUIViewUnitTest(){
        Controller test = new Controller();
        test.enableUIView();
        assertTrue(test.logsContains(test.getStateAsLog(),"User is Connected"));
        //System.exit(0);
    }


    @Test
    public void disableUIViewUnitTest(){
        Controller test = new Controller();
        test.enableUIView();
        test.disableUIView();
        assertTrue(test.logsContains(test.getStateAsLog(),"User is Disconnected"));
        //System.exit(0);
    }

    @Test
    public void getStateAsLogTest(){
        Controller test = new Controller();
    }
}
