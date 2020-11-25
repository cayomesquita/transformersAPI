package org.interview.aequilibrium.api.hateoas;

import org.interview.aequilibrium.model.Transformer;
import org.springframework.hateoas.Link;

/**
 * The Transformer resource.
 * <p>
 * Reponsable to map the Transformer entity to a well-formed restful json resource
 */
public class TransformerResource extends AbstractBaseResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idTransformer;
	private String name;
	private String type;
	private Integer strength;
	private Integer intelligence;
	private Integer speed;
	private Integer endurance;
	private Integer rank;
	private Integer courage;
	private Integer firepower;
	private  Integer skill;

	/**
	 * Create instance.
	 *
	 * @param entity the entity
	 * @return the instance or null if entity was null
	 */
	public static TransformerResource createInstance(Transformer entity) {
		if (entity == null) {
			return null;
		}
		TransformerResource resource = new TransformerResource();
		resource.setIdTransformer(entity.getId());
		resource.setName(entity.getName());
		resource.setType(entity.getType().getCode());
		resource.setCourage(entity.getCourage());
		resource.setEndurance(entity.getEndurance());
		resource.setFirepower(entity.getFirepower());
		resource.setIntelligence(entity.getIntelligence());
		resource.setRank(entity.getRank());
		resource.setSpeed(entity.getSpeed());
		resource.setStrength(entity.getStrength());
		resource.setSkill(entity.getSkill());
		return resource;
	}

	/**
	 * Gets id transformer.
	 *
	 * @return the id transformer
	 */
	public Integer getIdTransformer() {
		return idTransformer;
	}

	/**
	 * Sets id transformer.
	 *
	 * @param idTransformer the id transformer
	 */
	public void setIdTransformer(Integer idTransformer) {
		this.idTransformer = idTransformer;
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
	public String getType() {
		return type;
	}

	/**
	 * Sets type.
	 *
	 * @param type the type
	 */
	public void setType(String type) {
		this.type = type;
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
}
