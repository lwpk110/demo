package cn.deep.sample.allinterface;

public class SomeAImpl implements SomeA {
  @Override
  public void methodA1() {
    System.out.println(this.getClass().getInterfaces()[0].getSimpleName() + ": methodA1 do something");
  }

  @Override
  public String methodA2WithResult() {
    System.out.println(this.getClass().getInterfaces()[0].getSimpleName() + ":"+ this.getClass().getMethods()[1].getName()+" do something");
    return "successfully";
  }
}
