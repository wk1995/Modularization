package com.wk.rxjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/28
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
public class Reflect {

    public static List<Method> way(Class class1,String annotationTypeName){
        List<Method> methodList=new ArrayList<>();
        Method[] methods=class1.getDeclaredMethods();
        for(Method method:methods){
            Annotation[] annotations=method.getDeclaredAnnotations();
            for(Annotation annotation:annotations){
                if(annotation.annotationType().getSimpleName().equals(annotationTypeName)) {
                    methodList.add(method);
                    Timber.i(method.getName());
                }
            }
        }
        return methodList;
    }
}
