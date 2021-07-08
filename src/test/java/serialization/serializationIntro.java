package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import pojo.PetPojo;

import java.io.File;
import java.io.IOException;

public class serializationIntro {

    @Test
    public void test1() throws IOException {

        PetPojo pet = new PetPojo();
        pet.setId(117);
        pet.setName("susu");
        pet.setStatus("waiting");

        File jsonFile = new File("pet.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(jsonFile, pet);
    }
}
