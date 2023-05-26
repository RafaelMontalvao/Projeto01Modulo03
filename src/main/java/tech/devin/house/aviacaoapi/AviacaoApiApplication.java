package tech.devin.house.aviacaoapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import tech.devin.house.aviacaoapi.dto.ConfirmacaoRequest;
import tech.devin.house.aviacaoapi.model.BilheteEmbarque;

@SpringBootApplication
public class AviacaoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviacaoApiApplication.class, args);
    }


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(ConfirmacaoRequest.class, BilheteEmbarque.class)
                .addMapping(src -> src.getCpf(), (dest, v) -> dest.getPassageiro().setCpf((Long)v));
        return modelMapper;
    }

}
