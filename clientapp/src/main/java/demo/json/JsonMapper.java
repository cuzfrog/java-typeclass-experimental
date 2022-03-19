package demo.json;

public final class JsonMapper {
    public static <T> String toJson(T t, Jsonable<T> evidence) {
        return evidence.toJson(t);
    }
}
