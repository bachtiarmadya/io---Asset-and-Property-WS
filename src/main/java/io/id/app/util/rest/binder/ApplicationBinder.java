package io.id.app.util.rest.binder;

import javax.inject.Singleton;
import org.atteo.classindex.ClassIndex;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import io.id.app.controller.Controller;

public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        ClassIndex.getAnnotated(Controller.class).forEach(clazz -> bind(clazz).to(clazz).in(Singleton.class));
    }
}
