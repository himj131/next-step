package core.ref;


import himj.nextstep.model.Question;
import himj.nextstep.model.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    public void newInstanceWithConstructorArgs() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        Class[] params = {String.class, String.class, String.class, String.class};
        Constructor<User> constructor =clazz.getConstructor(params);
        User user = constructor.newInstance("id", "pwd", "name", "email");

        logger.debug("user : {}", user);
    }
    
    @Test
    public void privateFieldAccess() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<Student> clazz = Student.class;
        Constructor<Student> constructor = clazz.getConstructor();

        Student student = constructor.newInstance();
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(student, "name");

        Field ageField = clazz.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(student, 33);

        logger.debug("student : {}", student);
    }
}
