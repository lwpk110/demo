package cn.deep.sample.allinterface.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiInterfaceProxyHandler implements InvocationHandler {

  private final Map<String, Object> methodTarget;

  public MultiInterfaceProxyHandler(List<Object> subImplementationTargets) {
    this. methodTarget= new HashMap<>();
    for (Object target : subImplementationTargets) {
      Method[] methods = target.getClass().getMethods();
      this.fitByMethod(methodTarget, methods, target);
    }
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String name = method.getName();
    if (methodTarget.containsKey(name)) {
      Object realTarget = methodTarget.get(name);
      return method.invoke(realTarget, args);
    }
    return null;
  }

  private void fitByMethod(Map<String,Object> methodTarget, Method[] methods, Object target){
    for (Method method : methods) {
      methodTarget.put(method.getName(), target);
    }
  }
}
