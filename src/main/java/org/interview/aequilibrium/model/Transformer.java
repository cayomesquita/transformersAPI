package org.interview.aequilibrium.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Entity Transformer.
 */
@Entity
@Table(name = "TRANSFORMER_TB")
public class Transformer {

    private static final String OPTIMUS_PRIME_NAME = "optimus prime";
    private static final String PREDAKING_NAME = "predaking";

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

    @Column
    private Integer skill = 0;

    /**
     * Instantiates a new Transformer.
     */
    public Transformer(){ super();}

    /**
     * Instantiates a new Transformer.
     *
     * @param name         the name
     * @param type         the type
     * @param strength     the strength
     * @param intelligence the intelligence
     * @param speed        the speed
     * @param endurance    the endurance
     * @param rank         the rank
     * @param courage      the courage
     * @param firepower    the firepower
     * @param skill        the skill
     */
    public Transformer(String name, TransformerType type, Integer strength, Integer intelligence, Integer speed, Integer endurance, Integer rank, Integer courage, Integer firepower, Integer skill) {
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
        this.skill = skill;
    }

    /**
     * Get over-all rating.
     *
     * @return the integer
     */
    public Integer getOverAllRating(){
        return Stream.of(getStrength(), getIntelligence(), getSpeed(), getEndurance(), getFirepower())
                .reduce(0, (a,b) -> (b == null ? a : a + b));
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public TransformerType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(TransformerType type) {
        this.type = type;
    }


    /**
     * Sets type based on a String type code.
     *
     * @param type the type code
     */
    public void setType(String type) {
        this.type = TransformerType.valueOfByCode(type).orElse(null);
    }

    /**
     * Gets strength.
     *
     * @return the strength
     */
    public Integer getStrength() {
        return strength;
    }

    /**
     * Sets strength.
     *
     * @param strength the strength
     */
    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    /**
     * Gets intelligence.
     *
     * @return the intelligence
     */
    public Integer getIntelligence() {
        return intelligence;
    }

    /**
     * Sets intelligence.
     *
     * @param intelligence the intelligence
     */
    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public Integer getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    /**
     * Gets endurance.
     *
     * @return the endurance
     */
    public Integer getEndurance() {
        return endurance;
    }

    /**
     * Sets endurance.
     *
     * @param endurance the endurance
     */
    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    /**
     * Gets rank.
     *
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * Gets courage.
     *
     * @return the courage
     */
    public Integer getCourage() {
        return courage;
    }

    /**
     * Sets courage.
     *
     * @param courage the courage
     */
    public void setCourage(Integer courage) {
        this.courage = courage;
    }

    /**
     * Gets firepower.
     *
     * @return the firepower
     */
    public Integer getFirepower() {
        return firepower;
    }

    /**
     * Sets firepower.
     *
     * @param firepower the firepower
     */
    public void setFirepower(Integer firepower) {
        this.firepower = firepower;
    }

    /**
     * Gets skill.
     *
     * @return the skill
     */
    public Integer getSkill() {
        return skill;
    }

    /**
     * Sets skill.
     *
     * @param skill the skill
     */
    public void setSkill(Integer skill) {
        this.skill = skill;
    }

    public boolean isOptimusPrime() {
        return OPTIMUS_PRIME_NAME.equalsIgnoreCase(getName());
    }

    public boolean isPredaking() {
        return PREDAKING_NAME.equalsIgnoreCase(getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transformer that = (Transformer) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * The enum Transformer type.
     */
    public enum TransformerType {
        /**
         * Autobot transformer type.
         */
        AUTOBOT("A"),
        /**
         * Decepticon transformer type.
         */
        DECEPTICON("D");

        private String code;

        TransformerType(String code) {
            this.code = code;
        }

        /**
         * Value of by code.
         *
         * @param code the code
         * @return the optional
         */
        public static Optional<TransformerType> valueOfByCode(String code){
            return Arrays.stream(TransformerType.values()).filter(transformerType -> transformerType.code.equalsIgnoreCase(code)).findFirst();
        }

    }
}

