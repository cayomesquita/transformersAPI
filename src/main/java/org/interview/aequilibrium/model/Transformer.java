package org.interview.aequilibrium.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Optional;

@Entity
@Table(name = "TRANSFORMER_TB")
public class Transformer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSFORMER")
    private Integer id;

    @Column(name = "TRANSFORMER_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private TransformerType type;

    @Column
    private String name;

    @Column
    private Integer strength = 0;

    @Column
    private Integer intelligence = 0;

    @Column
    private Integer speed = 0;

    @Column
    private Integer endurance = 0;

    @Column
    private Integer rank = 0;

    @Column
    private Integer courage = 0;

    @Column
    private Integer firepower = 0;

    public Transformer(){ super();}

    public Transformer(String name, TransformerType type, Integer strength, Integer intelligence, Integer speed, Integer endurance, Integer rank, Integer courage, Integer firepower) {
        this();
        this.name = name;
        this.type = type;
        this.strength = strength;
        this.intelligence = intelligence;
        this.speed = speed;
        this.endurance = endurance;
        this.rank = rank;
        this.courage = courage;
        this.firepower = firepower;
    }

    public Integer getSkill(){
        return getStrength() + getIntelligence() + getIntelligence() + getSpeed() + getEndurance() + getRank() + getCourage() + getFirepower();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransformerType getType() {
        return type;
    }

    public void setType(TransformerType type) {
        this.type = type;
    }


    public void setType(String type) {
        this.type = TransformerType.valueOfByCode(type).orElse(null);
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getCourage() {
        return courage;
    }

    public void setCourage(Integer courage) {
        this.courage = courage;
    }

    public Integer getFirepower() {
        return firepower;
    }

    public void setFirepower(Integer firepower) {
        this.firepower = firepower;
    }

    public enum TransformerType {
        AUTOBOT("A"), DECEPTICON("D");

        private String code;

        TransformerType(String code) {
            this.code = code;
        }

        public static Optional<TransformerType> valueOfByCode(String code){
            return Arrays.stream(TransformerType.values()).filter(transformerType -> transformerType.code.equalsIgnoreCase(code)).findFirst();
        }

    }
}

