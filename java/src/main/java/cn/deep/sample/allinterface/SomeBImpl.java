package cn.deep.sample.allinterface;

public class SomeAImpl implements SomeA {
  @Override
  public void methodA1() {
    System.out.println(this.getClass().getInterfaces()[0].getSimpleName() + ": methodA1 do something");
  }
}
