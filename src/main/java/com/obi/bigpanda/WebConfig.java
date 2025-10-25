

package com.obi.bigpanda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(  // ← PLURAL: setBasenames()


//                ten nationa circle parks
                "language/gorge/messages",
                "language/kilimanjaro/messages",
                "language/kikuletwa/messages",
                "language/manyara/messages",
                "language/serengeti/messages",
                "language/ngorongoro/messages",
                "language/arusha/messages",
                "language/tarangire/messages",
                "language/mkomazi/messages",
                "language/ruaha/messages",


//                This part is for header,from wildbeast to family
                "myhead/adventure/messages",
                "myhead/honeymoon/messages",
                "myhead/family/messages",
                "myhead/daytrip/messages",
                "myhead/celebration/messages"

        );

        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH); // Set default locale
        return slr;
    }
}