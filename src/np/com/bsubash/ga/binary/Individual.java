package np.com.bsubash.ga.binary;

/**
 * @author subash
 * @since Mar 27, 2021
 */
public class Individual {
	private Gene[] chromosome;
	private double fitness = -1;

	public Individual(Gene[] chromosome) {
		this.chromosome = chromosome;
	}

	public Individual(int chromosomeSize) {
		this.chromosome = new Gene[chromosomeSize];
		this.initRandomIndividual();
	}

	public Gene[] getChromosome() {
		return this.chromosome;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return fitness;
	}

	public void setGene(int index, Gene gene) {
		this.chromosome[index] = gene;
	}

	public Gene getGene(int index) {
		return this.chromosome[index];
	}

	private void initRandomIndividual() {
		if (this.chromosome == null)
			throw new NullPointerException("Chromosome is null !");
		for (int i = 0; i < this.chromosome.length; i++) {
			double rand = Math.random();
			this.setGene(i, new Gene(rand > 0.5 ? 1 : 0));
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Gene gene : this.chromosome) {
			sb.append(gene.toString());
		}
		return sb.toString();
	}
}
