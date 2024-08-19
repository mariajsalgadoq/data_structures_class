package salgado.SPECIES;

import java.util.ArrayList;
import java.util.List;

public class Species implements Comparable<Species> { 
	private String category;
	private String taxonomicGroup; 
	private String taxonomicSubGroup;
	private String scientificName;
	private String commonName;
	private String NYListingStatus;
	private List<String> counties = new ArrayList<>();
	
	
	public Species (String category,
					String taxonomicGroup,
					String taxonomicSubGroup,
					String scientificName,
					String commonName,
					String NYListingStatus) {
		//validate
		validateArgument(category, "Category");
		validateArgument(taxonomicGroup, "Taxonomix Group");
		validateArgument(taxonomicSubGroup, "Taxonomic Subgroup");
		validateArgument(scientificName, "Scientific Name");
		validateArgument(commonName, "Common Name");
		validateArgument(NYListingStatus, "NY Listing Status");
		
		//this
		this.category = category;
		this.taxonomicGroup = taxonomicGroup;
		this.taxonomicSubGroup = taxonomicSubGroup;
		this.scientificName = scientificName;
		this.commonName = commonName;
		this.NYListingStatus = NYListingStatus;
		
		
	}
	//create validate argument method			
	private void validateArgument(String arg, String fieldName) {
		if(arg == null || arg.isEmpty()) {
			throw new IllegalArgumentException( fieldName + "cannot be nullor empty.");
			
		}
		
	}
	//getters
	public String getCategory() {
		return category;
	}
	
	public String getTaxonomicGroup() {
		return taxonomicGroup;	
	}
	
	public String getTaxonomicSubGroup() {
		return taxonomicSubGroup;	
	}
	
	public String getScientificName() {
		return scientificName;
	}
	
	public String getCommonName() {
		return commonName;
	}
	
	public String getNYListingStatus() {
		return NYListingStatus;
	}
	// isPresent method (and exception)
	public boolean addCounty( String county) {
		validateArgument(county, "County");
		return counties.add(county);
	}
	//county method (and exception)
	public boolean isPresentIn(String county) {
		validateArgument(county, "County");
		return counties.contains(county);
	}
	
	//@Override equals method
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if( o == null || getClass() != o.getClass())
			return false;
		Species species = (Species) o;
		return category.equalsIgnoreCase(species.category) &&
				taxonomicGroup.equalsIgnoreCase(species.taxonomicGroup) &&
				taxonomicSubGroup.equalsIgnoreCase(species.taxonomicSubGroup)&& 
				scientificName.equalsIgnoreCase(species.scientificName) &&
				commonName.equalsIgnoreCase(species.commonName)
				;
	}
	
	//@Override toString method
	public String toString() {
		int numCounties = counties.size();
		return String.format("%s (%s)\n%s, %s\nNY Listing Status: %s\nPresent in %d / 62 counties", 
                commonName, scientificName, taxonomicGroup, taxonomicSubGroup, 
                NYListingStatus, numCounties);
	}
	
	// @Override compareTo and exception
	public int compareTo(Species other) {
		int comparison = commonName.compareToIgnoreCase(other.commonName);
		if (comparison == 0) {
	        return scientificName.compareToIgnoreCase(other.scientificName);
	    }
	    return comparison;
	}
	
	
}
