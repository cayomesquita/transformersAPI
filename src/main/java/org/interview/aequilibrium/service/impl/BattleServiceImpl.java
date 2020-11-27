package org.interview.aequilibrium.service.impl;

import org.interview.aequilibrium.api.hateoas.BattleResultResource;
import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.service.BattleService;
import org.interview.aequilibrium.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Battle service implementation.
 */
@Service
public class BattleServiceImpl implements BattleService {

    private static final int MIN_COURAGE_DIFF = 4;
    private static final int MIN_STRENGTH_DIFF = 3;
    private static final int MIN_SKILL_DIFF = 3;
    private static final String DECEPTICONS = "Decepticons";
    private static final String AUTOBOTS = "Autobots";

    @Autowired
    private TransformerService transformerService;

    @Override
    public ResponseEntity getBattleResult(Set<Integer> ids) {
        final List<Transformer> transformers = transformerService.getTransformers(ids.toArray(new Integer[0]));
        final Set<Transformer> col = new HashSet<>(transformers);
        AtomicInteger counterOptimusPrime = new AtomicInteger();
        AtomicInteger counterPredaking = new AtomicInteger();

        Comparator<Transformer> comparator = new TransformerComparatorPriority();
        final Queue<Transformer> autobotsQueue = new PriorityQueue<>(col.size(), comparator);
        final Queue<Transformer> decepticonsQueue = new PriorityQueue<>(col.size(), comparator);
        col.stream().forEach(transformer -> {
            if (transformer.isOptimusPrime()){
                counterOptimusPrime.getAndIncrement();
            }
            if (transformer.isPredaking()){
                counterPredaking.getAndIncrement();
            }
            switch (transformer.getType()) {
                case AUTOBOT:
                    autobotsQueue.offer(transformer);
                    break;
                case DECEPTICON:
                    decepticonsQueue.offer(transformer);
                    break;
                default:
                    break;
            }
        });
        if (counterOptimusPrime.get() > 1 || counterPredaking.get() > 1) {
            return ResponseEntity.ok(createBattleResourceAllDestroyed(0));
        }
        return runBattle(autobotsQueue, decepticonsQueue);
    }

    private ResponseEntity runBattle(Queue<Transformer> autobotsQueue, Queue<Transformer> decepticonsQueue) {
        int fights = 0;
        List<Transformer> autobotSurvivers = new ArrayList<>(autobotsQueue.size());
        List<Transformer> decepticonSurvivers = new ArrayList<>(decepticonsQueue.size());
        while (!autobotsQueue.isEmpty() && !decepticonsQueue.isEmpty()){
            fights++;
            Transformer autobot = autobotsQueue.poll();
            Transformer decepticon = decepticonsQueue.poll();
            boolean autobotWins = autobot.isOptimusPrime() || autobot.isPredaking();
            boolean decepticonWins = decepticon.isOptimusPrime() || decepticon.isPredaking();
            if (autobotWins && decepticonWins){
                return ResponseEntity.ok(createBattleResourceAllDestroyed(fights));
            }
            int fightResult = fight(autobot, decepticon);
            if (fightResult != 0) {
                if (fightResult > 0){
                    autobotSurvivers.add(autobot);
                } else {
                    decepticonSurvivers.add(decepticon);
                }
            }
        }
        int autobotWinCounter = autobotSurvivers.size();
        int decepticonWinCunter = decepticonSurvivers.size();
        while (!autobotsQueue.isEmpty()){
            autobotSurvivers.add(autobotsQueue.poll());
        }
        while (!decepticonsQueue.isEmpty()){
            decepticonSurvivers.add(decepticonsQueue.poll());
        }
        int battleResultInt = Integer.compare(autobotWinCounter,decepticonWinCunter);
        if (battleResultInt != 0) {
            BattleResultResource resultResource = null;
            if (battleResultInt > 0) {
                List<String> survivers = decepticonSurvivers.stream().map(transformer -> transformer.getName()).collect(Collectors.toList());
                resultResource = BattleResultResource.createInstance(fights, AUTOBOTS, survivers);
            } else {
                List<String> survivers = autobotSurvivers.stream().map(transformer -> transformer.getName()).collect(Collectors.toList());
                resultResource = BattleResultResource.createInstance(fights, DECEPTICONS, survivers);
            }
            return ResponseEntity.ok(resultResource);
        }
        return ResponseEntity.ok(createBattleResourceAllDestroyed(fights));
    }

    private BattleResultResource createBattleResourceAllDestroyed(int fights) {
        return BattleResultResource.createInstance(fights, "Both destroyed", Collections.emptyList());
    }

    private int fight(Transformer autobot, Transformer decepticon){
        if (autobot.isOptimusPrime() || autobot.isPredaking()) {
            return 1;
        }
        if (decepticon.isOptimusPrime() || decepticon.isPredaking()) {
            return -1;
        }
        int diffCourage = autobot.getCourage() - decepticon.getCourage();
        int diffStrength = autobot.getStrength() - decepticon.getStrength();
        if ((MIN_COURAGE_DIFF <= diffCourage  && MIN_STRENGTH_DIFF <= diffStrength)){
            return 1;
        }
        if ((MIN_COURAGE_DIFF <= -diffCourage && MIN_STRENGTH_DIFF <= -diffStrength)){
            return -1;
        }
        int diffSkill = autobot.getSkill() - decepticon.getSkill();
        if (MIN_SKILL_DIFF <= diffSkill){
            return 1;
        }
        if (MIN_SKILL_DIFF <= -diffSkill){
            return -1;
        }
        return autobot.getOverAllRating().compareTo(decepticon.getOverAllRating());
    }

    private class TransformerComparatorPriority implements Comparator<Transformer> {

        @Override
        public int compare(Transformer o1, Transformer o2) {
            return -o1.getRank().compareTo(o2.getRank());
        }
    }
}
