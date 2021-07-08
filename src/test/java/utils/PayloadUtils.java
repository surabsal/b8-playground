package utils;

public class PayloadUtils {

    public static String getPayload(int petID, String petName, String petStatus) {
        String requestBody = "{\n" +
                "  \"id\": "+petID+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \""+petName+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [],\n" +
                "  \"status\": \""+petStatus+"\"\n" +
                "}";
        return requestBody;
    }

    public static String getSlackMessagePayload(String channelID, String msg){
        String payload = "{\n" +
                "    \"channel\":\""+channelID+"\",\n" +
                "    \"text\": \""+msg+"\"\n" +
                "}";
        return payload;
    }

}

