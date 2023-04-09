package com.tms.webshop.utils;

import com.tms.webshop.dao.CategoryDao;
import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.OrderDao;
import com.tms.webshop.dao.ProductDao;
import com.tms.webshop.dao.UserDao;
import com.tms.webshop.dao.database.CategoryDaoDb;
import com.tms.webshop.dao.database.ImageDaoDb;
import com.tms.webshop.dao.database.OrderDaoDb;
import com.tms.webshop.dao.database.ProductDaoDb;
import com.tms.webshop.dao.database.UserDaoDb;
import com.tms.webshop.model.Inject;
import com.tms.webshop.service.CategoryService;
import com.tms.webshop.service.CategoryServiceAware;
import com.tms.webshop.service.ImageService;
import com.tms.webshop.service.ImageServiceAware;
import com.tms.webshop.service.OrderService;
import com.tms.webshop.service.OrderServiceAware;
import com.tms.webshop.service.ProductService;
import com.tms.webshop.service.ProductServiceAware;
import com.tms.webshop.service.UserService;
import com.tms.webshop.service.UserServiceAware;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class InjectObjectsFactory {
    private static Map<Class, Object> OBJECT_MAP = new ConcurrentHashMap<>();

    public static void createAndInjectInstances(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            Inject inject = field.getAnnotation(Inject.class);
            if (inject != null) {
                Object newObject = putInstanceToMapIfAbsent(field.getType());
                Method injectMethod = findInjectMethod(object, field.getType());
                try {
                    injectMethod.invoke(object, newObject);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("Error, while trying to invoke setter method in InjectObjectsFactory", e);
                }
                createAndInjectInstances(newObject);
            }
        }
    }

    private static <T> Method findInjectMethod(Object object, Class<T> clazz) {
        for (Method declaredMethod : object.getClass().getDeclaredMethods()) {
            boolean allMatch = Arrays.stream(declaredMethod.getParameterTypes())
                    .allMatch(parameterType -> parameterType == clazz) && declaredMethod.getReturnType() == Void.TYPE
                    && declaredMethod.getName().startsWith("set");
            if (allMatch) {
                return declaredMethod;
            }
        }
        throw new IllegalArgumentException("Cannot find method with parameter " + clazz);
    }

    private static <T> Object putInstanceToMapIfAbsent(Class<T> clazz) {
        Object value = OBJECT_MAP.get(clazz);
        if (value == null) {
            value = createInstance(clazz);
            OBJECT_MAP.put(clazz, value);
        }
        return value;
    }

    private static <T> Object createInstance(Class<T> clazz) {
        if (clazz == UserServiceAware.class) {
            return new UserService();
        } else if (clazz == CategoryServiceAware.class) {
            return new CategoryService();
        } else if (clazz == ImageServiceAware.class) {
            return new ImageService();
        } else if (clazz == OrderServiceAware.class) {
            return new OrderService();
        } else if (clazz == ProductServiceAware.class) {
            return new ProductService();
        } else if (clazz == UserDao.class) {
            return new UserDaoDb();
        } else if (clazz == CategoryDao.class) {
            return new CategoryDaoDb();
        } else if (clazz == ImageDao.class) {
            return new ImageDaoDb();
        } else if (clazz == OrderDao.class) {
            return new OrderDaoDb();
        } else if (clazz == ProductDao.class) {
            return new ProductDaoDb();
        }
        throw new IllegalArgumentException("Cannot create instance of class " + clazz);
    }
}
