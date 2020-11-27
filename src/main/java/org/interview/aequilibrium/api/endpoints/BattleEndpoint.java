package org.interview.aequilibrium.api.endpoints;

import org.interview.aequilibrium.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Transformer endpoint.
 * Path /transformers
 *
 */
@RestController
@RequestMapping("/api/battles")
public class BattleEndpoint {

	@Autowired
	private BattleService battleService;

	/**
	 * Gets all transformers.
	 *
	 * @return the transformers
	 */
	@GetMapping("/result")
	public ResponseEntity getBattleResult(@RequestParam("id") List<Integer> ids) {
		Set<Integer> idCol = new HashSet<>(ids);
		return battleService.getBattleResult(idCol);
	}

}
