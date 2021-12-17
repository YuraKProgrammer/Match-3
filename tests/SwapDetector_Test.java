package tests;

import models.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tests.mocks.SequenceDetectorMock;

import java.util.ArrayList;

import static org.mockito.Matchers.any;

public class SwapDetector_Test {
    @Test
    public void check_TestXY(){
        SwapDetector detector=new SwapDetector(new SequenceDetectorMock());
        var result =detector.check(new GameField(10,10),new Point(-1,-5),new Point(-10,12));
        Assertions.assertFalse(result);
    }
    @Test
    public void check_TestNear(){
        SwapDetector detector=new SwapDetector(new SequenceDetectorMock());
        var result =detector.check(new GameField(10,10),new Point(1,1),new Point(5,3));
        Assertions.assertFalse(result);
    }
}
