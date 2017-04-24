package aufgabe5;

public class Sortieren {

	long counter = 0;

	public long getCounter() {
		return this.counter;
	}

	public synchronized void erhoeheCounter() {
		this.counter++;
	}

	private int median(int links, int mitte, int rechts) {

		if (mitte > rechts ^ rechts > links) {
			return mitte;
		}

		if (rechts > mitte ^ rechts > links) {
			return rechts;
		}

		return links;
	}

	private int pivotauswahl(Integer[] array, int links, int rechts, Pivotsuchverfahren v) {
		int pivot = 0;
		switch (v) {

		case RECHTS:
			pivot = array[rechts];
			break;
		case MEDIAN:
			pivot = median(array[links], array[(links + rechts) / 2], array[rechts]);
			break;
		case ZUFAELLIG:
			pivot = array[(int) (Math.random() * (rechts - links + 1) + links)];
			break;
		default:
			break;
		}
		return pivot;
	}

	private int[] partitioniere(Integer[] array, int links, int rechts, Pivotsuchverfahren v) {

		int[] ij = new int[2];
		int i = links;
		int j = rechts;

		int p = pivotauswahl(array, links, rechts, v);

		while (i <= j) {
			while (array[i] < p) {
				i++;
			}

			while (array[j] > p) {
				j--;
			}

			if (i <= j) {
				int tmp = array[i];
				array[i] = array[j];
				array[j] = tmp;
				i++;
				j--;
			}
		}
		ij[0] = j;
		ij[1] = i;
		return ij;
	}

	public void quicksort(Integer[] array, int links, int rechts, Pivotsuchverfahren v) throws Exception {

		if (array == null) {
			throw new Exception();
		}

		if (links < rechts && rechts >= 0 && links >= 0) {

			int[] pq = new int[2];
			int p, q;

			pq = partitioniere(array, links, rechts, v);
			p = pq[0];
			q = pq[1];

			quicksort(array, links, p, v);
			quicksort(array, q, rechts, v);
		}
	}

}
