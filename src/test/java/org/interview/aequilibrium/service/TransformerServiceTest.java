package org.interview.aequilibrium.service;

import org.interview.aequilibrium.api.hateoas.TransformerResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.persistence.TransformerRepository;
import org.interview.aequilibrium.service.impl.TransformerServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Transformer service test.
 */
@ExtendWith(SpringExtension.class)
class TransformerServiceTest {

    /**
     * The type Transformer endpoint test context configuration.
     */
    @TestConfiguration
    static class TransformerServiceTestContextConfiguration {

        /**
         * Transformer service transformer service.
         *
         * @return the transformer service
         */
        @Bean
        public TransformerService transformerService() {
            return new TransformerServiceImpl();
        }

    }

    @Autowired
    private TransformerService transformerService;

    @MockBean
    private TransformerRepository transformerRepository;

    /**
     * Test get all transformers case
     */
    @Test
    void getTransformers() {
        Integer value = 1;
        Transformer transformer1 = new Transformer("String name1", Transformer.TransformerType.DECEPTICON, value, value, value, value, value, value, value, value++);
        Transformer transformer2 = new Transformer("String name2", Transformer.TransformerType.DECEPTICON, value, value, value, value, value, value, value, value++);
        Transformer transformer3 = new Transformer("String name3", Transformer.TransformerType.DECEPTICON, value, value, value, value, value, value, value, value++);
        Transformer transformer4 = new Transformer("String name4", Transformer.TransformerType.AUTOBOT, value, value, value, value, value, value, value, value++);
        Transformer transformer5 = new Transformer("String name5", Transformer.TransformerType.AUTOBOT, value, value, value, value, value, value, value, value++);
        List<Transformer> aux = Arrays.asList(transformer1, transformer2, transformer3, transformer4, transformer5);

        Mockito.when(transformerRepository.findAll()).thenReturn(aux);

        Response response = transformerService.getTransformers();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.hasEntity());
        assertTrue(response.getEntity() instanceof List);
        List<TransformerResource> list = (List<TransformerResource>) response.getEntity();
        assertEquals(aux.size(), list.size());
        assertEquals(aux.get(0).getName(), list.get(0).getName());
        assertEquals(aux.get(4).getName(), list.get(4).getName());
    }

    /**
     * Test the basic insert case
     */
    @Test
    void insertTransformer() {
        Integer id = 1;
        Transformer newTransformer = new Transformer();
        newTransformer.setId(id++);
        Transformer newTransformer2 = new Transformer();
        newTransformer2.setId(id++);
        Mockito.when(transformerRepository.save(any(Transformer.class))).thenReturn(newTransformer, newTransformer2);
        Mockito.when(transformerRepository.findByName(anyString())).thenReturn(null);

        Integer value = 1;
        Transformer transformer1 = new Transformer("String name1", Transformer.TransformerType.DECEPTICON, value++, value++, value++, value++, value++, value++, value++, value++);
        Response response = transformerService.insertTransformer(transformer1);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.hasEntity());
        assertTrue(response.getEntity() instanceof Integer);
        assertEquals(Integer.valueOf(1), (Integer) response.getEntity());

        Transformer transformer2 = new Transformer("String name2", Transformer.TransformerType.AUTOBOT, value++, value++, value++, value++, value++, value++, value++, value++);
        response = transformerService.insertTransformer(transformer2);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.hasEntity());
        assertTrue(response.getEntity() instanceof Integer);
        assertEquals(Integer.valueOf(2), (Integer) response.getEntity());
    }

    /**
     * Test insert case when is going to duplicate
     */
    @Test
    void insertTransformerDuplicatedTest() {
        Integer value = 1;
        Transformer transformer1 = new Transformer("String name1", Transformer.TransformerType.DECEPTICON, value++, value++, value++, value++, value++, value++, value++, value++);
        Mockito.when(transformerRepository.save(any(Transformer.class))).thenReturn(transformer1);
        Mockito.when(transformerRepository.findByName("String name1")).thenReturn(null, transformer1);

        Response response = transformerService.insertTransformer(transformer1);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        response = transformerService.insertTransformer(transformer1);
        assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
    }

    /**
     * Test insert case when does not recognize the transformer type
     */
    @Test
    void insertTransformerNoTypeTest() {
        Integer value = 1;
        Transformer transformer = new Transformer("String name2", null, value++, value++, value++, value++, value++, value++, value++, value++);
        Response response = transformerService.insertTransformer(transformer);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    /**
     * Test basic update case
     */
    @Test
    void updateTransformer() {
        Transformer newTransformer1 = new Transformer("String name1", Transformer.TransformerType.AUTOBOT, 1, 1, 1, 1, 1, 1, 1, 1);
        newTransformer1.setId(1);

        Transformer updatedTransformer1 = new Transformer("String name1", Transformer.TransformerType.AUTOBOT, 3, 3, 3, 3, 3, 3, 3, 3);
        updatedTransformer1.setId(newTransformer1.getId());

        Mockito.when(transformerRepository.findById(1)).thenReturn(Optional.of(newTransformer1));
        Mockito.when(transformerRepository.save(ArgumentMatchers.argThat(transformer -> transformer.getId().equals(newTransformer1.getId())))).thenReturn(updatedTransformer1);

        Response response = transformerService.updateTransformer(updatedTransformer1);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.hasEntity());
        assertTrue(response.getEntity() instanceof Integer);
        assertEquals(newTransformer1.getId(), (Integer) response.getEntity());
    }

    /**
     * Test update case when Id is null. It has to insert a new transformer
     */
    @Test
    void updateTransformerIdNull() {
        Transformer newTransformer2 = new Transformer("String name2", Transformer.TransformerType.AUTOBOT, 2, 2, 2, 2, 2, 2, 2, 2);

        Transformer persistedTransformer2 = new Transformer("String name2", Transformer.TransformerType.AUTOBOT, 2, 2, 2, 2, 2, 2, 2, 2);
        persistedTransformer2.setId(2);

        Mockito.when(transformerRepository.save(newTransformer2)).thenReturn(persistedTransformer2);

        Response response = transformerService.updateTransformer(newTransformer2);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.hasEntity());
        assertTrue(response.getEntity() instanceof Integer);
        assertEquals(Integer.valueOf(2), (Integer) response.getEntity());
    }

    /**
     * Test update case when id does not exist. It has to insert a new transformer
     */
    @Test
    void updateTransformerIdNotExist() {
        Transformer newTransformer2 = new Transformer("String name2", Transformer.TransformerType.AUTOBOT, 2, 2, 2, 2, 2, 2, 2, 2);
        newTransformer2.setId(2);

        Transformer persistedTransformer2 = new Transformer("String name2", Transformer.TransformerType.AUTOBOT, 2, 2, 2, 2, 2, 2, 2, 2);
        persistedTransformer2.setId(1);

        Mockito.when(transformerRepository.findById(2)).thenReturn(Optional.empty());
        Mockito.when(transformerRepository.save(any(Transformer.class))).thenReturn(persistedTransformer2);

        Response response = transformerService.updateTransformer(newTransformer2);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.hasEntity());
        assertTrue(response.getEntity() instanceof Integer);
        assertEquals(Integer.valueOf(1), (Integer) response.getEntity());
    }

    /**
     * Test delete case
     */
    @Test
    void deleteTransformer() {
        Mockito.when(transformerRepository.existsById(1)).thenReturn(true, false);

        Response response = transformerService.deleteTransformer(1);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

        response = transformerService.deleteTransformer(1);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    /**
     * Test get transformers by ids.
     */
    @Test
    void testGetTransformersByIds() {
        Transformer transformer1 = new Transformer("Soundwave", Transformer.TransformerType.DECEPTICON, 8,9,2,6,7,5,6,10);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Bluestreak", Transformer.TransformerType.AUTOBOT, 6,6,7,9,5,2,9,7);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,4,4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerRepository.findAllById(ArgumentMatchers.any())).thenReturn(listAux);

        List<Transformer> list = transformerService.getTransformers(ids.toArray(new Integer[0]));

        assertEquals(listAux.size(), list.size());
        assertEquals(listAux.get(0).getName(), list.get(0).getName());
    }

}