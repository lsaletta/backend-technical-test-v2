package com.tui.proof.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class BaseTest {

    public <T> List<T> getFileFromListResources(String fileName, Class<T> clasz) throws IOException {
        ObjectMapper mapper = mapper();
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        File file;
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        }
        file = new File(resource.getFile());

        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, clasz));
    }

    public <T> T getFileFromResources(String json, Class<T> clasz) throws IOException {
        ObjectMapper mapper = mapper();
        return mapper.readValue(json, clasz);
    }


    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true);
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);

        return builder;
    }

    public ObjectMapper mapper() {
        final ObjectMapper mapper = jacksonBuilder().build();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        return mapper;
    }
}
