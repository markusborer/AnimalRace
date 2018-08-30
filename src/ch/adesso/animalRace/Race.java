package ch.adesso.animalRace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import ch.adesso.animalRace.animal.Animal;

public class Race extends Thread {

	public Weather weather;
	private List<Animal> animals;
	private int place;
	private int index;

	public Race(Weather weather) {
		this.weather = weather;
		animals = new ArrayList<>();
		place = 1;
	}

	public Weather getWeather() {
		return weather;
	}

	/**
	 * Add animal
	 * 
	 * @param animal
	 */
	public void add(Animal animal) {
		animal.setPosition(0);
		animals.add(animal);
	}

	/**
	 * Start
	 */
	@Override
	public void start() {
		System.out.println("Race started");
		System.out.println("It is " + weather);
		System.out.println("");
		boolean finished = false;
		Random random = new Random();
		while (!finished) {
			index = random.nextInt(animals.size());
			run();
			print(animals.get(index).getPosition() >= 100);
			finished = true;
			for (Animal animal : animals) {
				if (animal.getPosition() < 100)
					finished = false;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		finished();
	}

	@Override
	public void run() {
		Animal animal = animals.get(index);
		if (animal.getPosition() < 100) {
			int step = animal.getStep(weather);
			animal.setPosition(animal.getPosition() + step);
		}
	}

	private void print(boolean arrived) {
		if (arrived) {
			Animal animal = animals.get(index);
			if (animal.getPosition() < 1000) {
				System.out.println(animal.getAnimalType().toString() + " "
						+ animal.getName() + " is arrived");
				animal.getFan().bravo();
				animal.setPosition(1000 + place);
				place++;
			}
		} else {
			Animal animal = animals.get(index);
			System.out.println(animal.getAnimalType().toString() + " "
					+ animal.getName() + " is at " + animal.getPosition());
			animal.getFan().applause(this);
		}
	}

	private void finished() {
		System.out.println("");
		System.out.println("Race finished");
		System.out.println("");
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).getPosition() == 1001) {
				System.out.println("First:  " + animals.get(i).getAnimalType()
						+ " " + animals.get(i).getName());
				animals.get(i).getFan().first();
			}
		}
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).getPosition() == 1002) {
				System.out.println("Second: " + animals.get(i).getAnimalType()
						+ " " + animals.get(i).getName());
				animals.get(i).getFan().second();
			}
		}
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).getPosition() == 1003) {
				System.out.println("Third:  " + animals.get(i).getAnimalType()
						+ " " + animals.get(i).getName());
				animals.get(i).getFan().third();
			}
		}
	}

}
