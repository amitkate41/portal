package executionEngine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class KeyWordExecution {
	static final Logger LOGGER = Logger.getLogger(KeyWordExecution.class);
	private static KeyWordExecution keyWordDriven;

	private KeyWordExecution() {

	}

	public void runReflectionMethod(String strClassName, String strMethodName, Object... inputArgs) {

		Class<?> params[] = new Class[inputArgs.length];

		for (int i = 0; i < inputArgs.length; i++) {
			if (inputArgs[i] instanceof String) {
				params[i] = String.class;
			}
		}
		try {
			Class<?> cls = Class.forName(strClassName);
			Object _instance = cls.newInstance();
			Method myMethod = cls.getDeclaredMethod(strMethodName, params);
			myMethod.invoke(_instance, inputArgs);

		} catch (ClassNotFoundException e) {
			LOGGER.error(strClassName + ":- Class not found%n");
		} catch (IllegalArgumentException e) {
			LOGGER.error("Method invoked with wrong number of arguments%n");
		} catch (NoSuchMethodException e) {
			LOGGER.error("In Class " + strClassName + "::" + strMethodName + ":- method does not exists%n");
		} catch (InvocationTargetException e) {
			LOGGER.error("Exception thrown by an invoked method%n");
		} catch (IllegalAccessException e) {
			LOGGER.error("Can not access a member of class with modifiers private%n", e);
		} catch (InstantiationException e) {
			LOGGER.error("Object cannot be instantiated for the specified class using the newInstance method%n");
		}
	}

	public static synchronized KeyWordExecution getInstace() {
		if (keyWordDriven == null) {
			keyWordDriven = new KeyWordExecution();
		}
		return keyWordDriven;
	}

}
