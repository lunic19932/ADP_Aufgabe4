package adp5;

/**
 * 
 * Die Klasse sortiert ein gegebenes Array
 * 
 * @author Jannes
 *
 */

public class Sortieren {

	// Counter
	long counter = 0;

	/**
	 * 
	 * Counter getter
	 * 
	 * @return counter
	 */
	public long getCounter() {
		return this.counter;
	}

	private Object[] elemente;

	public Sortieren() {
		elemente = new Element[0];
	}

	public Object insert(Element<?> element) {

		Object[] newElement = new Element[elemente.length + 1];

		for (int i = 0; i < newElement.length; i++) {
			if (i == newElement.length - 1) {
				newElement[i] = element;
			} else {
				newElement[i] = elemente[i];
			}
		}
		elemente = newElement;
		return elemente;
	}

	/**
	 * Methode erhoeht den Counter um 1
	 */
	public synchronized void erhoeheCounter() {
		this.counter++;
	}

	/**
	 * 
	 * Die Methode gibt den Median des Arrays zurück
	 * 
	 * @param linkes
	 *            Element
	 * @param mittleres
	 *            Element
	 * @param rechtes
	 *            Element
	 * @return
	 */
	private int median(int links, int mitte, int rechts) {

		if (mitte > rechts ^ rechts > links) {
			return mitte;
		}

		if (rechts > mitte ^ rechts > links) {
			return rechts;
		}

		return links;
	}

	/**
	 * 
	 * Die Methode wählt aufgrund des gegeben Parameters das Suchverfahren aus
	 * 
	 * @param das
	 *            Array
	 * @param linkes
	 *            Element
	 * @param rechtes
	 *            Element
	 * @param Pivotsuchverfahren
	 * 
	 * @return Pivotelement
	 */
	private int pivotauswahl(Integer[] array, int links, int rechts, Pivotsuchverfahren v) {
		int pivot = 0;
		switch (v) {

		case RECHTS:
			// rechtes Element
			pivot = array[rechts];
			break;
		case MEDIAN:
			// linkes Element, mittleres Element, rechtes Element
			pivot = median(array[links], array[(links + rechts) / 2], array[rechts]);
			break;
		case ZUFAELLIG:
			// eine zufällige Zahl im gueltigen Bereich wird gewaehlt
			pivot = array[(int) (Math.random() * (rechts - links + 1) + links)];
			break;
		default:
			break;
		}
		return pivot;
	}

	/**
	 * 
	 * Die Methode sortiert den jeweiligen Array Abschnitt
	 * 
	 * @param das
	 *            Array
	 * @param linkes
	 *            Element
	 * @param rechtes
	 *            Element
	 * @param Pivotsuchverfahren
	 * 
	 * @return neuer Abschnitt
	 */
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
				// sortiervorgang
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

	/**
	 * 
	 * Die Methode ruft sich selbst aus und teilt die zu sortierenden Abschnitte
	 * ein
	 * 
	 * @param das
	 *            Array
	 * @param linkes
	 *            Element
	 * @param rechtes
	 *            Element
	 * @param Pivotsuchverfahren
	 * @throws Exception
	 */
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

	public int getSize() throws Exception {
		return elemente.length;
	}

}
