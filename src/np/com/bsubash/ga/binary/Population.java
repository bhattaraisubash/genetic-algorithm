package np.com.bsubash.ga.binary;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author subash
 * @since Mar 27, 2021
 */
public class Population {
	private Individual[] individuals;
	private double fitness = -1;

	public Population(int populationSize) {
		this.individuals = new Individual[populationSize];
	}

	public Population(int populationSize, int chromosomeSize) {
		this.individuals = new Individual[populationSize];

		for (int i = 0; i < populationSize; i++) {
			this.individuals[i] = new Individual(chromosomeSize);
		}
	}

	public Individual[] getIndividuals() {
		return individuals;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public Individual getIndividual(int index) {
		return this.individuals[index];
	}

	public void setIndividual(int index, Individual individual) {
		this.individuals[index] = individual;
	}

	public Individual getFittestIndividual(int offset) {
		// sort the population so that first individual has highest fitness score
		Arrays.sort(this.individuals, new Comparator<Individual>() {
			@Override
			public int compare(Individual a, Individual b) {
				if (a.getFitness() < b.getFitness())
					return 1;
				if (a.getFitness() > b.getFitness())
					return -1;
				return 0;
			}
		});
		return this.individuals[offset];
	}

	public void sufflePopulation() {
		List<Individual> list = Arrays.<Individual>asList(this.individuals);
		Collections.shuffle(list);
		list.toArray(this.individuals);
	}

}
