package com.tuya.connector.api.handler;

import com.tuya.connector.api.annotations.Query;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * <p> TODO
 *
 * @author qiufeng.yu@tuya.com
 * @since 2021/1/20 7:26 下午
 */
public class QueryAnnotationHandler extends BaseAnnotationHandler<Query> {
    @Override
    public Object handle(Query srcQuery, CtClass ctClass, CtMethod ctMethod) {
        ConstPool constPool = ctClass.getClassFile().getConstPool();
        Annotation query = new Annotation(retrofit2.http.Query.class.getTypeName(), constPool);
        query.addMemberValue("value", new StringMemberValue(srcQuery.value(), constPool));
        query.addMemberValue("encoded", new BooleanMemberValue(srcQuery.encoded(), constPool));

        return query;
    }
}
