package cn.deep.sample.allinterface;

public class SomeBImpl implements SomeB {
  @Override
  public void methodB1() {
    System.out.println(this.getClass().getInterfaces()[0].getSimpleName() +": " + this.getClass().getMethods()[0].getName() + " do something");
  }
}
