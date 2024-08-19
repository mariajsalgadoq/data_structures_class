package salgado.LIST;
import java.util.ArrayList;
import java.util.Collections;

import salgado.SPECIES.Species;

public class SpeciesList extends ArrayList<Species>{
	
	public SpeciesList() {
		super ();
	}
	
	
	
	//public void add( Species species, String county) (and exceptions)
	public void add( Species species, String county) {
		if(species == null || county == null || county.isEmpty()){
			throw new IllegalArgumentException("Species and county must not be null or empty.");
			
		}
		boolean speciesFound = false;
		for (Species s : this) {
			if(s.equals(species)) {
				
				s.addCounty(county);
				speciesFound = true;
				break;
			}
		}
		
		if(!speciesFound) {
			species.addCounty(county);
			this.add(species);
			
		}
	}
	
	//public SpeciesName getByName (string keyword)
	public SpeciesList getByName ( String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword must not be null or empty.");
        }
		
		SpeciesList matchingSpecies = new SpeciesList();
		for( Species species : this) {
			if (species.getCommonName().toLowerCase().contains(keyword.toLowerCase()) ||
	                species.getScientificName().toLowerCase().contains(keyword.toLowerCase())) {
	                matchingSpecies.add(species);
	            }
		}
		if(matchingSpecies.isEmpty()) {
			return null;
		}
		
		Collections.sort(matchingSpecies);
		return matchingSpecies;
		
	}
}
