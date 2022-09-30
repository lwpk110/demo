package cn.deep.sample.mockito.construct;

public class ExampleA {

    public String methodA(){
       Cloud cloud = new Cloud();
       return cloud.invoke();
    }
}
