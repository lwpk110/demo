package cn.deep.sample;

import cn.deep.sample.construct.Cloud;
import cn.deep.sample.construct.ExampleA;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ExampleATest {

    @Test
    void methodA_mockNewConstruct() {
        try (MockedConstruction<Cloud> mockedConstruction = Mockito.mockConstruction(Cloud.class,
                (sampleMock, context) ->
                        Mockito.when(sampleMock.invoke()).thenReturn("failure"))) {
            ExampleA exampleA = new ExampleA();
            String s = exampleA.methodA();
            assertNotEquals("invoke ok!!", s);
            assertEquals("failure", s);
        }
    }
}