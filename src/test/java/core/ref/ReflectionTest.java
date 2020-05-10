package core.ref;


import himj.nextstep.model.Question;
import himj.nextstep.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() {
        Class<Question> clazz = Question.class;
        logger.debug(clazz.getName());

        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields) {
            logger.debug("fields : {}", field.getName());
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method method : declaredMethods) {
            logger.debug("methods : {}", method.getName());
        }

        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            logger.debug("constructor : {}", constructor.toString());
        }

    }
    
    @Test
    public void newInstanceWithConstructorArgs() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
    }
    
    @Test
    public void privateFieldAccess() {
        Class<Student> clazz = Student.class;
        logger.debug(clazz.getName());
    }
}
