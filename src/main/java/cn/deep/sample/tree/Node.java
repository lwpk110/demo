package cn.deep.sample.tree;

public class Node<T> {
    private T value;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getStrValue(){
        return  value.toString();
    }

    public Number getNumValue(){
        return (Number) value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
