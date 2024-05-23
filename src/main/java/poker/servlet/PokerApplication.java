package poker.servlet;

import java.util.Set;

import org.springframework.web.WebApplicationInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletException;

/*
public class PokerApplication implements WebApplicationInitializer {

    public void onStartup(ServletContext context) {
        System.out.printf("Servlet context is started: {%s}\n", context.getContextPath());
    }
}
*/

//@HandlesTypes(WebApplicationInitializer.class)
public class PokerApplication implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        for (Class<?> clazz : c) {
            System.out.println(clazz);
            System.out.println(clazz.getResource('/' + clazz.getName().replace('.', '/') + ".class"));
            System.out.println("----------------");
        }

    }
}