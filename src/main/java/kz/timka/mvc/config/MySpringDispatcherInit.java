package kz.timka.mvc.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

// класс AbstractAnnotationConfigDispatcherServletInitializer использует аннотации из javax.servlet-api
// поэтому мы ее добавили в зависимостях
public class MySpringDispatcherInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    // замена web.xml с помощью java
    // настраиваем dispatcher servlet
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class}; // указываем конфиг класс
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"}; //указываем что любой url с корня должен перенаправляться к дистатчеру сервлету
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // метод запускается при старте приложении
        super.onStartup(servletContext);
        registerHiddenFieldFilter(servletContext);

    }

    private void registerHiddenFieldFilter(ServletContext servletContext) {
        servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(
                null, true,"/*"
        );
        // /* для любых запросов означает это
    }
}
