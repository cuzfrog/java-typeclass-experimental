package demo;

import demo.json.AJsonable;
import demo.json.JsonMapper;

final class App {
    public static void main(String[] args) {
        JsonMapper.toJson(new ObjectA(), new AJsonable());
    }
}
