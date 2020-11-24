package org.interview.aequilibrium.api.hateoas;

import org.interview.aequilibrium.model.Transformer;

public class TransformerResource extends AbstractBaseResource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idTransformer;
	private Integer strength;
	private Integer intelligence;
	private Integer speed;
	private Integer endurance;
	private Integer rank;
	private Integer courage;
	private Integer firepower;

	public static TransformerResource getInstace(Transformer entity) {
		if (entity == null) {
			return null;
		}
		TransformerResource resource = new TransformerResource();
		resource.setIdTransformer(entity.getId());
		resource.setCourage(entity.getCourage());
		resource.setEndurance(entity.getEndurance());
		resource.setFirepower(entity.getFirepower());
		resource.setIntelligence(entity.getIntelligence());
		resource.setRank(entity.getRank());
		resource.setSpeed(entity.getSpeed());
		resource.setStrength(entity.getStrength());
		return resource;
	}

	public Integer getIdTransformer() {
		return idTransformer;
	}

	public void setIdTransformer(Integer idTransformer) {
		this.idTransformer = idTransformer;
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
}
