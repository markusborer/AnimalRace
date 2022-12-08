package ch.adesso.animalRace.animal;

import ch.adesso.animalRace.Weather;
import ch.adesso.animalRace.fan.Fan;


public class Animal {

	private AnimalType animalType;
	private String name;
	private Integer position;

	public Animal(AnimalType animalType, String name) {
		this.animalType = animalType;
		this.name = name;
	}
	
	public AnimalType getAnimalType() {
		return animalType;
	}

	public String getName() {
		return name;
	}

	public Fan getFan() {
		Fan fan = new Fan();
		fan.setAnimal(this);
		fan.setName("Fan of " + name);
		return fan;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public int getStep(Weather weather) {
		int step = 0;
		switch (getAnimalType()) {
		case ELEFANT:
			switch (weather) {
			case SUNNY:
				step = 4;
				break;
			case RAINY:
				step = 1;
				break;
			case STORMY:
				step = 2;
				break;
			}
			break;
		case HEDGEHOG:
			switch (weather) {
			case SUNNY:
				step = 2;
				break;
			case RAINY:
				step = 4;
				break;
			case STORMY:
				step = 4;
				break;
			}
			break;
		case TIGER:
			switch (weather) {
			case SUNNY:
				step = 8;
				break;
			case RAINY:
				step = 6;
				break;
			case STORMY:
				step = 4;
				break;
			}
			break;
		}
		return step;
	}

}
