package demo.json;

import com.github.cuzfrog.ap.Implementation;
import demo.ObjectA;
import demo.ObjectB;

final class JsonableImpls {
    @Implementation(Jsonable.class)
    final Jsonable<ObjectA> aJsonable = new AJsonable();

    @Implementation
    static String toJson(ObjectB objectB) {
        return objectB == null ? null : objectB.toString();
    }
}
