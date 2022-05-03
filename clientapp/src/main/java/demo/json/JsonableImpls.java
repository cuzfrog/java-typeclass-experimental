package demo.json;

import com.github.cuzfrog.ap.Implementation;
import demo.ObjectA;
import demo.ObjectB;

final class JsonableImpls {
    @Implementation(Jsonable.class)
    final Jsonable<ObjectA> aJsonable = new AJsonable();

    @Implementation(Jsonable.class)
    static String toJson(ObjectB self) {
        return self.toString();
    }
}
