package com.example.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.util.Map;

/**
 */
public class Main2 {
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

		Object foo = mapper.readValue(new File("test.yml"), Map.class);
		System.out.println(foo.toString());
	}
}
