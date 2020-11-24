package org.interview.aequilibrium.api.hateoas;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Resource assembler.
 *
 * Responsible to map the resources and to decorate with hyperlinks
 */
public final class ResourceAssembler {

	private List<AbstractBaseResource> content;

	private List<Link> _link;
	
	private class CollectionResource extends AbstractBaseResource{
		/**
		 * Instantiates a new Collection resource.
		 *
		 * @param content the content
		 * @param list    the list
		 */
		CollectionResource(List<AbstractBaseResource> content, List<Link> list){
			getContent().addAll(content);
			add(list);
		}
	}

	/**
	 * Gets instence.
	 *
	 * @return the instence
	 */
	public static ResourceAssembler getInstence() {
		return new ResourceAssembler();
	}

	private ResourceAssembler() {
		super();
	}

	/**
	 * Add resource resource assembler.
	 *
	 * @param resource the resource
	 * @return the resource assembler
	 */
	public ResourceAssembler addResource(AbstractBaseResource resource) {
		getContent().add(resource);
		return this;
	}

	/**
	 * Add resource resource assembler.
	 *
	 * @param resource the resource
	 * @param links    the links
	 * @return the resource assembler
	 */
	public ResourceAssembler addResource(AbstractBaseResource resource, Link... links) {
		resource.add(links);
		return this.addResource(resource);
	}

	/**
	 * Add link resource assembler.
	 *
	 * @param links the links
	 * @return the resource assembler
	 */
	public ResourceAssembler addLink(Link... links) {
		getLink().addAll(Arrays.asList(links));
		return this;
	}

	/**
	 * Generate abstract base resource.
	 *
	 * @return the abstract base resource
	 */
	public AbstractBaseResource generate(){
		if(NumberUtils.INTEGER_ONE.equals(getContent().size())) {
			AbstractBaseResource resource = getContent().iterator().next();
			resource.add(getLink());
			return resource;
		}
		return new CollectionResource(getContent(), getLink());
	}

	/**
	 * Gets content.
	 *
	 * @return the content
	 */
	List<AbstractBaseResource> getContent() {
		if(content == null) {
			this.content = new ArrayList<>();
		}
		return content;
	}

	private List<Link> getLink() {
		if(_link == null) {
			this._link = new ArrayList<>();
		}
		return _link;
	}

}
