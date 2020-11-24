package com.example.algorithm.demo.Controller;

import java.io.IOException;

import com.example.algorithm.demo.Exception.ExceptionExample;
import com.example.algorithm.demo.Models.AnimalResponseObject;
import com.example.algorithm.demo.Models.SampleObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Controller
@RequestMapping(value = "/test")
public class ControllerExample {

    private static final Logger logger = LogManager.getLogger(ControllerExample.class);
    private static final String BASE_URL = "http://127.0.0.1:8080/test/a/";
    private static final String TEST_URL = "https://cat-fact.herokuapp.com/facts/random";
    private static final OkHttpClient client = new OkHttpClient();
    private static final String[] nomes = {"Vitor","Nome2","Rotiv","Michael","Jeff","Mike","Dave"};
    @GetMapping(value = "/a/{numero}")
    @ResponseBody
    public SampleObject getTest(@PathVariable Integer numero) {

        if (numero.equals(4)) {
            logger.debug("Debugging log");
            logger.info("Info log");
            logger.warn("Hey, This is a warning!");
            logger.error("Oops! We have an Error. OK");
            logger.fatal("Damn! Fatal error. Please fix me.");
            throw new ExceptionExample("Something Went Wrong");
        }

        return new SampleObject(nomes[(int) (Math.floor(Math.random() * (6 - 0 + 1)) + 0)], numero);

    }

    @GetMapping(value = "/b/{numero}")
    @ResponseBody
    public String getB(@PathVariable Integer numero) throws IOException {

        Request request = new Request.Builder().url(BASE_URL + numero).build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    @GetMapping(value = "/fact")
    @ResponseBody
    public String getAnimalFact() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Request request = new Request.Builder().url(TEST_URL).get().build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        AnimalResponseObject obj = objectMapper.readValue(json, AnimalResponseObject.class);

        String result = new StringBuilder()
        .append("User:")
        .append(obj.getUser())
        .append("\n")
        .append("Fact:")
        .append(obj.getText())
        .toString();

        logger.info(result);
        return result;

    }



}
