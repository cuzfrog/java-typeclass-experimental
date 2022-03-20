package demo.json;

import demo.ObjectA;

final class AJsonable implements Jsonable<ObjectA> {
    @Override
    public String toJson(ObjectA objectA) {
        return String.format("{\"value\":%d}", objectA.getValue());
    }
}
