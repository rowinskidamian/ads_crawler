package pl.damianrowinski.app_spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.damianrowinski")
public class AppConfig implements WebMvcConfigurer {

    // ustawienie kodowania dla żądań // wcześniej robiliśmy filtrem
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain",
                Charset.forName("UTF-8"))));
        converters.add(stringConverter);
    }

    // ustawienie ViewResolver aby widoki były w web-inf z sufiksem .jsp
    // ponizsze będzie prawodopodobnie mieszać w poprzednich zadaniach, gdzie na sztywno adresy są podane
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // ustawienie, aby View Resolver nie wpływał na pliki statyczne, jak obrazki
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
