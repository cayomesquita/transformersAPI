package org.interview.aequilibrium.service;

import org.interview.aequilibrium.api.hateoas.BattleResultResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.service.impl.BattleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Battle service test.
 */
@ExtendWith(SpringExtension.class)
class BattleServiceTest {
    /**
     * The type Battle service test context configuration.
     */
    @TestConfiguration
    static class BattleServiceTestContextConfiguration {

        /**
         * Battle service battle service.
         *
         * @return the battle service
         */
        @Bean
        public BattleService battleService() {
            return new BattleServiceImpl();
        }

    }

    @Autowired
    private BattleService battleService;

    @MockBean
    private TransformerService transformerService;

    /**
     * Basic battle result case.
     */
    @Test
    void getBattleResult() {
        Transformer transformer1 = new Transformer("Soundwave", Transformer.TransformerType.DECEPTICON, 8,9,2,6,7,5,6,10);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Bluestreak", Transformer.TransformerType.AUTOBOT, 6,6,7,9,5,2,9,7);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,4,4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Decepticons", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(1, losingTeam.size());
        assertEquals("Hubcap", losingTeam.get(0));
    }

    /**
     * Battle result case when opponent runs away.
     */
    @Test
    void getBattleResultOpponentRunsAway() {
        Transformer transformer1 = new Transformer("Soundwave", Transformer.TransformerType.DECEPTICON, 5,9,2,6,7,5,6,10);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Bluestreak", Transformer.TransformerType.AUTOBOT, 8,6,7,9,5,9,9,7);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,4,4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Autobots", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(0, losingTeam.size());
    }

    /**
     * Battle result case when Skill Criteria.
     */
    @Test
    void getBattleResultSkillCriteria() {
        Transformer transformer1 = new Transformer("Soundwave", Transformer.TransformerType.DECEPTICON, 4,4,4,4,4,4,4,7);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Bluestreak", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,4,4,4);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,5,4, 4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Decepticons", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(1, losingTeam.size());
        assertEquals("Bluestreak", losingTeam.get(0));
    }

    /**
     * Battle result case when OverAll Criteria. (Strength + Intelligence + Speed + Endurance + Firepower)
     */
    @Test
    void getBattleResultOverAllCriteria() {
        Transformer transformer1 = new Transformer("Soundwave", Transformer.TransformerType.DECEPTICON, 6,4,4,4,4,4,5,4);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Bluestreak", Transformer.TransformerType.AUTOBOT, 4,5,5,4,5,4,4,4);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,4, 4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Decepticons", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(1, losingTeam.size());
        assertEquals("Hubcap", losingTeam.get(0));
    }

    /**
     * Battle result case when Name Criteria. Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
     */
    @Test
    void getBattleResultNameCriteria() {
        Transformer transformer1 = new Transformer("Soundwave", Transformer.TransformerType.DECEPTICON, 6,4,4,4,4,4,5,4);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Optimus Prime", Transformer.TransformerType.AUTOBOT, 4,5,5,4,5,4,4,4);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,4, 4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Autobots", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(0, losingTeam.size());

        transformer1 = new Transformer("Predaking", Transformer.TransformerType.DECEPTICON, 4,4,4,4,4,4,5,4);
        transformer1.setId(1);
        transformer2 = new Transformer("Bluestreak", Transformer.TransformerType.AUTOBOT, 10,5,5,4,5,10,4,4);
        transformer2.setId(2);
        transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,4, 4,4);
        transformer3.setId(3);

        listAux = Arrays.asList(transformer1, transformer2, transformer3);
        ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        resource = (BattleResultResource) response.getBody();
        assertEquals("Decepticons", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        losingTeam = resource.getLosingSurvivers();

        assertEquals(1, losingTeam.size());
        assertEquals("Hubcap", losingTeam.get(0));
    }

    /**
     * Battle result case when Name Criteria. Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
     *
     * But named does not fight
     */
    @Test
    void getBattleResultNameNotFightCriteria() {
        Transformer transformer1 = new Transformer("Soundwave", Transformer.TransformerType.DECEPTICON, 6,4,4,4,4,4,5,4);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Optimus Prime", Transformer.TransformerType.AUTOBOT, 4,5,5,4,4,4,4,4);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,5,4, 4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Decepticons", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(1, losingTeam.size());
        assertEquals("Optimus Prime", losingTeam.get(0));
    }

    /**
     * Battle result case when Name Criteria. Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
     *
     * Face each other Optimus Prime x Predaking
     */
    @Test
    void getBattleResultNameFacedCriteria() {
        Transformer transformer1 = new Transformer("Predaking", Transformer.TransformerType.DECEPTICON, 6,4,4,4,4,4,5,4);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Optimus Prime", Transformer.TransformerType.AUTOBOT, 4,5,5,4,5,4,4,4);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,5, 4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Both destroyed", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(0, losingTeam.size());
    }

    /**
     * Battle result case when Name Criteria. Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
     *
     * When named transformer are duplicated
     */
    @Test
    void getBattleResultNameDuplicatedCriteria() {
        Transformer transformer1 = new Transformer("Predaking", Transformer.TransformerType.DECEPTICON, 6,4,4,4,4,4,5,4);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Optimus Prime", Transformer.TransformerType.AUTOBOT, 4,5,5,4,5,4,4,4);
        transformer2.setId(2);
        Transformer transformer3 = new Transformer("Predaking", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,5, 4,4);
        transformer3.setId(3);

        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Both destroyed", resource.getWinningTeam());
        assertEquals(0, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(0, losingTeam.size());
    }


    /**
     * Battle result case when Tie
     */
    @Test
    void getBattleResultTie() {
        Transformer transformer1 = new Transformer("Soundwave", Transformer.TransformerType.DECEPTICON, 4,4,4,4,4,4,4,4);
        transformer1.setId(1);
        Transformer transformer2 = new Transformer("Bluestreak", Transformer.TransformerType.AUTOBOT, 4,4,4,4,4,4,4,4);
        transformer2.setId(2);
        Transformer transformer12 = new Transformer("Soundwave2", Transformer.TransformerType.DECEPTICON, 4,4,4,4,4,4,4,4);
        transformer12.setId(1);
        Transformer transformer22 = new Transformer("Bluestreak2", Transformer.TransformerType.AUTOBOT, 4,4,4,6,4,4,4,4);
        transformer22.setId(2);
        Transformer transformer3 = new Transformer("Hubcap", Transformer.TransformerType.AUTOBOT, 4,4,4,4,5,4, 4,4);
        transformer3.setId(3);


        List<Transformer> listAux = Arrays.asList(transformer1, transformer2, transformer12, transformer22, transformer3);
        Set<Integer> ids = listAux.stream().map(Transformer::getId).collect(Collectors.toSet());

        Mockito.when(transformerService.getTransformers(ArgumentMatchers.any())).thenReturn(listAux);

        ResponseEntity response = battleService.getBattleResult(ids);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertTrue(response.getBody() instanceof BattleResultResource);
        BattleResultResource resource = (BattleResultResource) response.getBody();
        assertEquals("Both destroyed", resource.getWinningTeam());
        assertEquals(1, resource.getNumOfBattles());

        List<String> losingTeam = resource.getLosingSurvivers();

        assertEquals(0, losingTeam.size());
    }
}