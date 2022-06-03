package demo.json;

import com.github.cuzfrog.ap.Implementation;
import demo.ObjectA;
import demo.ObjectB;

public final class JsonableImpls {
    @Implementation(Jsonable.class)
    public final Jsonable<ObjectA> aJsonable = new AJsonable();

    @Implementation(Jsonable.class)
    public static String toJson(ObjectB self) {
        return String.format("{\"value\": \"%s\"}", self.getValue());
    }
}
