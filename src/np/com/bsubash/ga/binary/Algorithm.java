package np.com.bsubash.ga.binary;

/**
 * @author subash
 * @since Mar 28, 2021
 */
public class Algorithm {
	private int popolationSize;
	private double mutationRate, crossoverRate;
	private int elitismCount;

	public Algorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
		this.popolationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.elitismCount = elitismCount;
	}

	public Population initPopulation(int chromosomeLength) {
		return new Population(this.popolationSize, chromosomeLength);
	}

	public void evaluateFitness(Individual individual) {
		int correct = 0;
		for (int i = 0; i < individual.getChromosome().length; i++) {
			if (individual.getGene(i).getNumber() == 1) {
				correct++;
			}
		}
		double fitness = (double) correct / individual.getChromosome().length;
		individual.setFitness(fitness);
	}

	public void evaluateFitness(Population population) {
		double fitness = 0.0;
		for (Individual individual : population.getIndividuals()) {
			evaluateFitness(individual);
			fitness += individual.getFitness();
		}
		population.setFitness(fitness);
	}

	public boolean solutionFound(Population population) {
		return population.getFittestIndividual(0).getFitness() == 1;
	}

	public Individual selectParentUsingRouletteWheel(Population population) {
		Individual[] individuals = population.getIndividuals();
		double wheelPosition = population.getFitness() * Math.random();
		double wheel = 0;
		for (Individual individual : individuals) {
			wheel += individual.getFitness();
			if (wheel >= wheelPosition) {
				return individual;
			}
		}
		return individuals[individuals.length - 1];
	}

	public Population crossover(Population population) {
		Population newPopulation = new Population(population.getIndividuals().length);
		for (int i = 0; i < population.getIndividuals().length; i++) {
			Individual firstParent = population.getFittestIndividual(i);
			// do crossover if not elite
			if (crossoverRate > Math.random() && i > elitismCount) {
				// perform crossover
				Individual secondParent = selectParentUsingRouletteWheel(population);
				Individual offspring = new Individual(firstParent.getChromosome().length);

				for (int j = 0; j < firstParent.getChromosome().length; j++) {
					offspring.setGene(j, Math.random() < 0.5 ? firstParent.getGene(j) : secondParent.getGene(j));
				}
				newPopulation.setIndividual(i, offspring);
			} else {
				// no crossover
				newPopulation.setIndividual(i, firstParent);
			}
		}
		return newPopulation;
	}

	public Population mutate(Population population) {
		Population newPopulation = new Population(population.getIndividuals().length);
		for (int i = 0; i < population.getIndividuals().length; i++) {
			Individual individual = population.getFittestIndividual(i);
			for (int j = 0; j < individual.getChromosome().length; j++) {
				// skip for elite individual
				if (i > elitismCount && Math.random() > mutationRate) {
					Gene mutatedGene = new Gene(1);
					if (individual.getGene(j).getNumber() == 1) {
						mutatedGene.setNumber(0);
					}
					individual.setGene(j, mutatedGene);
				}
			}
			newPopulation.setIndividual(i, individual);
		}
		return newPopulation;
	}
}
