package core.ref;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Junit3TestRunner {
    @Test
    public void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;
        Method[] methods = clazz.getMethods();

        for(Method method : methods) {
            if(method.getName().startsWith("test")) {
                // "clazz.newInstance()" deplicated since 9
                method.invoke(clazz.getDeclaredConstructor().newInstance());
            }
        }
    }
}
