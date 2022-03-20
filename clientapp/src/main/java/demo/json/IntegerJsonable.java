package demo.json;

final class IntegerJsonable implements Jsonable<Integer> {
    @Override
    public String toJson(Integer integer) {
        return integer == null ? null : integer.toString();
    }
}
