package org.interview.aequilibrium.api.hateoas;

import org.interview.aequilibrium.api.endpoints.BattleEndpoint;
import org.interview.aequilibrium.api.endpoints.TransformerEndpoint;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;

import java.util.Collections;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Battle result resource.
 */
public class BattleResultResource extends AbstractBaseResource {

	private static final long serialVersionUID = 1L;

	private Integer numOfBattles;
	private String winningTeam;
	private List<String> losingSurvivers;

	/**
	 * Create instance battle result resource.
	 *
	 * @param numOfBattles    the num of battles
	 * @param winningTeam     the winning team
	 * @param losingSurvivers the losing survivers
	 * @return the battle result resource
	 */
	public static BattleResultResource createInstance(Integer numOfBattles, String winningTeam, List<String> losingSurvivers) {
		BattleResultResource resource = new BattleResultResource(numOfBattles, winningTeam, losingSurvivers);
		resource.add(linkTo(methodOn(TransformerEndpoint.class).getTransformers()).withRel("transformer").withType("GET").withName("All tranformers"));
		return resource;
	}

	/**
	 * Instantiates a new Battle result resource.
	 *
	 * @param numOfBattles    the num of battles
	 * @param winningTeam     the winning team
	 * @param losingSurvivers the losing survivers
	 */
	BattleResultResource(Integer numOfBattles, String winningTeam, List<String> losingSurvivers) {
		this.numOfBattles = numOfBattles;
		this.winningTeam = winningTeam;
		this.losingSurvivers = losingSurvivers;
	}

	/**
	 * Gets num of battles.
	 *
	 * @return the num of battles
	 */
	public Integer getNumOfBattles() {
		return numOfBattles;
	}

	/**
	 * Sets num of battles.
	 *
	 * @param numOfBattles the num of battles
	 */
	public void setNumOfBattles(Integer numOfBattles) {
		this.numOfBattles = numOfBattles;
	}

	/**
	 * Gets winning team.
	 *
	 * @return the winning team
	 */
	public String getWinningTeam() {
		return winningTeam;
	}

	/**
	 * Sets winning team.
	 *
	 * @param winningTeam the winning team
	 */
	public void setWinningTeam(String winningTeam) {
		this.winningTeam = winningTeam;
	}

	/**
	 * Gets losing survivers.
	 *
	 * @return the losing survivers
	 */
	public List<String> getLosingSurvivers() {
		return losingSurvivers;
	}

	/**
	 * Sets losing survivers.
	 *
	 * @param losingSurvivers the losing survivers
	 */
	public void setLosingSurvivers(List<String> losingSurvivers) {
		this.losingSurvivers = losingSurvivers;
	}
}
