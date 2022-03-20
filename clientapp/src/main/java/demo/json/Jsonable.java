package demo.json;

public interface Jsonable<T> {
    String toJson(T t);
}
