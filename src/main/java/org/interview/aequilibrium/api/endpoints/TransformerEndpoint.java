package org.interview.aequilibrium.api.endpoints;

import org.interview.aequilibrium.model.Transformer;
import org.interview.aequilibrium.service.TransformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Transformer endpoint.
 * Path /transformers
 *
 */
@RestController
@RequestMapping("/api/transformers")
public class TransformerEndpoint {

	@Autowired
	private TransformerService transformerService;

	/**
	 * Gets all transformers.
	 *
	 * @return the transformers
	 */
	@GetMapping()
	public ResponseEntity getTransformers() {
		return transformerService.getTransformers();
	}

	/**
	 * Insert transformer.
	 *
	 * @param transformer the transformer
	 * @return the response
	 */
	@PostMapping
	public ResponseEntity insertTransformer(@RequestBody Transformer transformer) {
		return transformerService.insertTransformer(transformer);
	}

	/**
	 * Update transformer.
	 *
	 * @param transformerInput the transformer
	 * @param id               the transformer id
	 * @return the response
	 */
	@PutMapping("/{transformerId}")
	public ResponseEntity updateTransformer(@RequestBody Transformer transformerInput, @PathVariable("transformerId") Integer id) {
		transformerInput.setId(id);
		return transformerService.updateTransformer(transformerInput);
	}

	/**
	 * Delete transformer.
	 *
	 * @param id the transformer id
	 * @return the response
	 */
	@DeleteMapping("/{transformerId}")
	public ResponseEntity deleteTransformer(@PathVariable("transformerId") Integer id) {
		return transformerService.deleteTransformer(id);
	}

}
