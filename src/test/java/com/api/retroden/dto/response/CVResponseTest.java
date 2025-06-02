package com.api.retroden.dto.response;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class CVResponseTest {

    @Test
    void testNoArgsConstructor() {
        CVResponse response = new CVResponse();
        assertNotNull(response);
        assertNull(response.getName());
        assertNull(response.getData());
    }

    @Test
    void testAllArgsConstructor() {
        String name = "resume.pdf";
        byte[] data = "test data".getBytes();
        CVResponse response = new CVResponse(name, data);

        assertNotNull(response);
        assertEquals(name, response.getName());
        assertArrayEquals(data, response.getData());
    }

    @Test
    void testBuilder() {
        String name = "cv.pdf";
        byte[] data = "sample content".getBytes();

        CVResponse response = CVResponse.builder()
                .name(name)
                .data(data)
                .build();

        assertNotNull(response);
        assertEquals(name, response.getName());
        assertArrayEquals(data, response.getData());
    }

    @Test
    void testSetterAndGetter() {
        CVResponse response = new CVResponse();
        String name = "document.pdf";
        byte[] data = "test content".getBytes();

        response.setName(name);
        response.setData(data);

        assertEquals(name, response.getName());
        assertArrayEquals(data, response.getData());
    }

    @Test
    void testEqualsAndHashCode() {
        String name = "test.pdf";
        byte[] data = "content".getBytes();

        CVResponse response1 = new CVResponse(name, data);
        CVResponse response2 = new CVResponse(name, data.clone());
        CVResponse response3 = new CVResponse("other.pdf", "different".getBytes());

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    void testToString() {
        String name = "doc.pdf";
        byte[] data = "test data".getBytes();
        CVResponse response = new CVResponse(name, data);

        String toString = response.toString();

        assertTrue(toString.contains("name=" + name));
        assertTrue(toString.contains("data="));
    }


}