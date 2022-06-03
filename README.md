# Typeclass experimental impl in Java

Goland style method impl/Monkey patch experiments in Java.

## How to run:
```java
// 1. define types-class:
public interface Jsonable<T> {
    String toJson(T t);
}

// 2. write your POJO:
public final class ObjectB {
    private String value;
    //getter/setter
}

// 3. implement a method somewhere:
@Implementation(Jsonable.class)
public static String toJson(ObjectB self) {
    return String.format("{\"value\": \"%s\"}", self.getValue());
}

// 4. directly call it:
ObjectB b = new ObjectB();
b.setValue("abc")
System.out.println(b.toJson());
```

```shell
mvn install
mvn compile -pl clientapp exec:java
# {"value": "abc"}
```
See `demo.App` in clientapp executed with method `toJson` added to `ObjectB`
## How to debug:
`mvndebug` + your IDE's remote JVM debug.

## How does it work?
Similar to [Project Lombok](https://github.com/projectlombok/lombok)

# Author:
Cause Chung <cuzfrog@gmail.com>
