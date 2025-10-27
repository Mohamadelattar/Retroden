package com.api.retroden.service;

import com.api.retroden.dto.mapper.CertificationMapper;
import com.api.retroden.dto.request.CertificationRequest;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.model.Certification;
import com.api.retroden.model.Professional;
import com.api.retroden.repository.CertificationRepository;
import com.api.retroden.repository.ProfessionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CertificationServiceTest {

    private CertificationRepository certificationRepository;
    private CertificationMapper certificationMapper;
    private ProfessionalRepository professionalRepository;
    private CertificationService certificationService;

    @BeforeEach
    void setUp() {
        certificationRepository = mock(CertificationRepository.class);
        certificationMapper = mock(CertificationMapper.class);
        professionalRepository = mock(ProfessionalRepository.class);
        certificationService = new CertificationService(certificationRepository, certificationMapper);
    }
    @Test
    void testCreate(){
        CertificationRequest request = mock(CertificationRequest.class);
        Certification certification = mock(Certification.class);
        Professional professional = mock(Professional.class);
        Certification savedCertification = mock(Certification.class);
        CertificationResponse response = mock(CertificationResponse.class);
        when(request.professionalId()).thenReturn(1L);
        when(certificationMapper.toCertification(request)).thenReturn(certification);
        when(certificationRepository.findProfessionelByIdCertification(1L)).thenReturn(professional);
        when(certificationRepository.save(certification)).thenReturn(savedCertification);
        when(certificationMapper.toCertificationResponse(savedCertification)).thenReturn(response);

        CertificationResponse result = certificationService.create(request);

        assertEquals(response,result);
        verify(certification).setProfessional(professional);

    }
    @Test
    void testFindAll(){
        Certification certification = new Certification();
        certification.setIdCertification(1L);
        List<Certification> certifications = List.of(certification);
        CertificationResponse response = new CertificationResponse("Java");
        when(certificationRepository.findAll()).thenReturn(certifications);
        when(certificationMapper.toCertificationResponse(certification)).thenReturn(response);
        List<CertificationResponse> result = certificationService.findAll();
        assertEquals(1, result.size());
        assertEquals(response, result.get(0));
    }

    @Test
    void testFindById(){
        Certification certification = new Certification();
        certification.setIdCertification(1L);
        CertificationResponse response = new CertificationResponse("Java");
        when(certificationRepository.findById(1L)).thenReturn(Optional.of(certification));
        when(certificationMapper.toCertificationResponse(certification)).thenReturn(response);
        CertificationResponse result = certificationService.findById(1L);
        assertEquals(response, result);
    }

    @Test
    void testFindByIdNotFound(){
        when(certificationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> certificationService.findById(1L));
    }

    @Test
    void testUpdateFound(){
        CertificationRequest request = new CertificationRequest(1L, "Java", new byte[]{1, 2, 3}, 1L);

        Certification existing = new Certification();
        existing.setIdCertification(1L);
        existing.setName("Java");
        existing.setData(new byte[]{1, 2, 3});
        existing.setProfessional(new Professional());

        Certification updated = new Certification();
        updated.setIdCertification(1L);
        updated.setName("Java");
        updated.setData(new byte[]{1, 2, 3});
        updated.setProfessional(new Professional());

        CertificationResponse response = new CertificationResponse("Java");

        when(certificationRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(certificationRepository.save(updated)).thenReturn(updated);
        when(certificationMapper.toCertificationResponse(updated)).thenReturn(response);

        CertificationResponse result = certificationService.update(request);

        assertEquals(response, result);
        assertEquals(updated.getName(), existing.getName());
        assertEquals(updated.getProfessional().getIdProfessional(), existing.getProfessional().getIdProfessional());

    }
    @Test
    void testDelete() {
        certificationService.delete(1L);
        verify(certificationRepository).deleteById(1L);
    }

    @Test
    void testFindCertificationProfessionelById(){
        Professional professional = new Professional();
        when(certificationRepository.findProfessionelByIdCertification(1L)).thenReturn(professional);
        Professional result = certificationService.findCertificationProfessionelById(1L);
        assertEquals(professional, result);
    }
}
