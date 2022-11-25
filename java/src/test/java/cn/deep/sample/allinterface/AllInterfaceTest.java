package cn.deep.sample.allinterface;

import cn.deep.sample.allinterface.proxy.MultiInterfaceProxyHandler;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.Arrays;


class AllInterfaceTest {

  @Test
  void testAllInterface() {
    AllInterface allInterface = (AllInterface) Proxy.newProxyInstance(AllInterfaceTest.class.getClassLoader(),
            new Class[]{AllInterface.class},
            new MultiInterfaceProxyHandler(Arrays.asList(new SomeAImpl(), new SomeBImpl())));

    allInterface.methodA1();
    String actualReturnBySomeAMethod2 = allInterface.methodA2WithResult();
    allInterface.methodB1();
  }

}