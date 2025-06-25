package com.api.retroden.service;

import com.api.retroden.dto.mapper.CvMapper;
import com.api.retroden.dto.request.CvRequest;
import com.api.retroden.dto.response.CVResponse;
import com.api.retroden.model.CV;
import com.api.retroden.model.Professionel;
import com.api.retroden.repository.CVRepository;
import com.api.retroden.repository.ProfessionelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CvServiceTest {
    @Mock
    private CVRepository cvRepository;

    @Mock
    private CvMapper cvMapper;

    @Mock
    private ProfessionelRepository professionelRepository;

    @InjectMocks
    private CVService cvService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate(){
        CvRequest request = new CvRequest(1L, "Java", new byte[]{1, 2, 3}, 1L);
        CV cv = new CV();
        Professionel professionel = new Professionel();
        CV savedCv = new CV();
        CVResponse response = new CVResponse();

        // Mapping from request to entity
        when(cvMapper.toCV(request)).thenReturn(cv);

        // Mocking professional repository
        when(professionelRepository.findById(1L)).thenReturn(Optional.of(professionel));

        // Saving and mapping back to response
        when(cvRepository.save(cv)).thenReturn(savedCv);
        when(cvMapper.toCvResponse(savedCv)).thenReturn(response);
        System.out.println("professionelId = " + request.professionelId());

        // Testing create method
        CVResponse result = cvService.create(request);

        assertNotNull(result);
        verify(cvMapper).toCV(request);
        verify(professionelRepository).findById(1L);
        verify(cvRepository).save(cv);
        verify(cvMapper).toCvResponse(savedCv);
    }

    @Test
    void testUpdate(){
        CvRequest request = new CvRequest(1L, "Java", new byte[]{1, 2, 3}, 1L);
        CV existingCv = new CV();
        Professionel professionel = new Professionel();
        CV updatedCv = new CV();
        CVResponse response = new CVResponse();

        // Mocking repository and mapper behavior
        when(cvRepository.findById(1L)).thenReturn(Optional.of(existingCv));
        when(professionelRepository.findById(1L)).thenReturn(Optional.of(professionel));
        when(cvRepository.save(existingCv)).thenReturn(updatedCv);
        when(cvMapper.toCvResponse(updatedCv)).thenReturn(response);

        // Testing update method
        CVResponse result = cvService.update(request);

        assertNotNull(result);
        assertEquals(response, result);
        verify(cvRepository).findById(1L);
        verify(professionelRepository).findById(1L);
        verify(cvRepository).save(existingCv);
    }

    @Test
    void testDelete(){
        Long id = 5L;
        cvService.delete(id);
        verify(cvRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById(){
        Long id = 1L;

        CV cv = new CV();
        CVResponse response = new CVResponse();

        when(cvRepository.findById(id)).thenReturn(Optional.of(cv));
        when(cvMapper.toCvResponse(cv)).thenReturn(response);

        CVResponse result = cvService.findById(id);

        assertNotNull(result);
        assertEquals(response, result);
        verify(cvRepository).findById(id);
        verify(cvMapper).toCvResponse(cv);
    }

    @Test
    void testFindByIdNotFound(){
        Long id = 99L;
        when(cvRepository.findById(id)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> cvService.findById(id));
        assertEquals("CV not found with id: " + id, exception.getMessage());
    }
}
