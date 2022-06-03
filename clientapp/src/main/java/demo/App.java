package demo;

public final class App {
    public static void main(String[] args) {
        ObjectB b = new ObjectB();
        b.setValue("abc");
        System.out.println(b.toJson());
    }
}
