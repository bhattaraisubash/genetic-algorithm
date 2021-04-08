package np.com.bsubash.ga.binary;

/**
 * @author subash
 * @since Mar 28, 2021
 */
public class Main {

	public static void main(String[] args) {
		Algorithm alg = new Algorithm(100, 0.001, 0.95, 2);
		Population population = alg.initPopulation(25);
		alg.evaluateFitness(population);
		int generation = 1;

		while (!alg.solutionFound(population)) {
			// evaluate fitness
			alg.evaluateFitness(population);
			
			System.out.println(generation + ": " + population.getFittestIndividual(0) + ": "
					+ population.getFittestIndividual(0).getFitness());
			
			// crossover
			population = alg.crossover(population);
			// mutation
			population = alg.mutate(population);

			generation++;
		}

		System.out.println("-------------------------------");
		System.out.printf("FOUND SOLUTION IN %d GENERATIONS !\n", generation);
		System.out.println("SOLUTION: " + population.getFittestIndividual(0).toString() + ": "
				+ population.getFittestIndividual(0).getFitness());
	}
}
