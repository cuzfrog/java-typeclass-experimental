package demo.json;

import com.github.cuzfrog.ap.Implementation;
import demo.ObjectA;

@Implementation
public final class AJsonable implements Jsonable<ObjectA> {
    @Override
    public String toJson(ObjectA objectA) {
        return objectA.toString();
    }
}
