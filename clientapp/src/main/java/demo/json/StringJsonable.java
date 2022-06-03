package demo.json;

final class StringJsonable implements Jsonable<String> {
    @Override
    public String toJson(String str) {
        return str;
    }
}
