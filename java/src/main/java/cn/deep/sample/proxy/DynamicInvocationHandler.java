package cn.deep.sample.allinterface.proxy;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class DynamicInvocationHandler implements InvocationHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(
          DynamicInvocationHandler.class);

  public static void main(String[] args) {
    Map proxyInstance = (Map) Proxy.newProxyInstance(
            DynamicInvocationHandler.class.getClassLoader(),
            new Class[]{Map.class},
            new DynamicInvocationHandler());
    int value = (int) proxyInstance.get("hello");// 42
    proxyInstance.put("hello", "world"); // exception
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
          throws Throwable {
    if (method.getName().equals("get")) {
      return 42;
    } else {
      throw new UnsupportedOperationException(
              "Unsupported method: " + method.getName());
    }
  }
}
