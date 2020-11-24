package org.interview.aequilibrium.api.hateoas;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ResourceAssembler {

	private List<AbstractBaseResource> content;

	private List<Link> _link;
	
	private class CollectionResource extends AbstractBaseResource{
		CollectionResource(List<AbstractBaseResource> content, List<Link> list){
			getContent().addAll(content);
			add(list);
		}
	}
	
	public static ResourceAssembler getInstence() {
		return new ResourceAssembler();
	}

	private ResourceAssembler() {
		super();
	}
	
	public ResourceAssembler addResource(AbstractBaseResource resource) {
		getContent().add(resource);
		return this;
	}
	
	public ResourceAssembler addResource(AbstractBaseResource resource, Link... links) {
		resource.add(links);
		return this.addResource(resource);
	}
	
	public ResourceAssembler addLink(Link... links) {
		getLink().addAll(Arrays.asList(links));
		return this;
	}
	
	public AbstractBaseResource generate(){
		if(NumberUtils.INTEGER_ONE.equals(getContent().size())) {
			AbstractBaseResource resource = getContent().iterator().next();
			resource.add(getLink());
			return resource;
		}
		return new CollectionResource(getContent(), getLink());
	}

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
