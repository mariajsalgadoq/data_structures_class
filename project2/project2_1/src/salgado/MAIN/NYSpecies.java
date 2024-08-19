package salgado.MAIN;

import java.util.Scanner;
import salgado.LIST.SpeciesList;
import salgado.SPECIES.Species;
import java.io.File;
import java.io.FileNotFoundException;

public class NYSpecies {

	public static void main(String[] args) throws FileNotFoundException {
		if(args.length != 1) {
			System.err.print("Error: the program expects a filenme as an argument.");;
			System.exit(1);
		}
		// opens and reads file
		String filename = args[0];
		File file = new File(filename);
		SpeciesList speciesList = new SpeciesList();
		// create scanner to read file
		try {
			Scanner sc = new Scanner(file);
			
			if (sc.hasNextLine()) sc.nextLine();
			
		// read each line
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				
		// validate that, in fact, there are the 7 data point
				if (data.length >=7 && isValidData(data)) {
					String county = data[0];
					String category = data[1];
					String taxonomicGroup= data[2];
					String taxonomicSubgroup = data[3];
					String scientificName = data[4];
					String commonName = data[5];
					String NYListingStatus = data[6];
					
					Species species = new Species( category, taxonomicGroup, taxonomicSubgroup, scientificName, commonName, NYListingStatus);
					speciesList.add(species, county);
				}
				
				
			}
			sc.close();
		}
		catch( FileNotFoundException e){
			System.err.println("Error: the file" + filename+ " cannot be opened.");
			System.exit(1);
		}
		
		//input from user
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the keyword to search through species names, or \"quit\" to stop");
		String input = in.nextLine();
		
		if(input.equalsIgnoreCase("quit")) {
			System.exit(1);
		}
		
		SpeciesList matchingSpecies = speciesList.getByName(input);
		if (matchingSpecies == null || matchingSpecies.isEmpty()) {
			System.out.println("No matching species found.");
		} else {
			for (Species species : matchingSpecies) {
				System.out.println(species);
				System.out.println();
			}
		}
		
		in.close();
			
	}
	
	
	private static boolean isValidData(String[] data) {
		for(int i = 0; i < 7; i++) {
			if(data[i] == null || data[i].isEmpty()) {
				return false;
			}
		
	}
		return true;
	

	}

}
