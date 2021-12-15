package io.github.emfsilva.restfull.serialization.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public final class YamlJacksonHttpMessageConverter extends AbstractJackson2HttpMessageConverter {


    public YamlJacksonHttpMessageConverter() {
        super(new YAMLMapper(), MediaType.parseMediaType("application/x-yaml"));
    }
}
