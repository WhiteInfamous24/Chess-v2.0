package factory;

public abstract class Factory<T> {
    protected abstract T build(String s);
}
