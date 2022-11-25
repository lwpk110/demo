package cn.deep.sample.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

class TimingDynamicInvocationHandlerTest {

  @Test
  void test() {
    Map mapProxyInstance = (Map) Proxy.newProxyInstance(
            TimingDynamicInvocationHandlerTest.class.getClassLoader(), new Class[]{Map.class},
            new TimingDynamicInvocationHandler(new HashMap<>()));

    mapProxyInstance.put("hello", "world");

    CharSequence csProxyInstance = (CharSequence) Proxy.newProxyInstance(
            TimingDynamicInvocationHandlerTest.class.getClassLoader(),
            new Class[]{CharSequence.class},
            new TimingDynamicInvocationHandler("Hello World"));

    csProxyInstance.length();
  }
}