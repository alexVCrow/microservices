package ua.alexcrow.carservice.controller;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            String conflictionDetails;
            try {
                conflictionDetails = IOUtils.toString(response.body().asInputStream(), Charsets.toCharset("UTF-8"));
            } catch (IOException e) {
                conflictionDetails = "{}";
            }
            System.out.println("conflictionDetails ----- " + conflictionDetails);
            return new IllegalAccessException(conflictionDetails);
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
