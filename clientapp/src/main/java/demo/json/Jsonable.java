package demo.json;

import com.github.cuzfrog.ap.Typeclass;

@Typeclass
public interface Jsonable<T> {
    String toJson(T t);
}
