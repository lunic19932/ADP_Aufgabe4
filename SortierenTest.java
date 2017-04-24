package aufgabe5;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class SortierenTest {

	@Test
	public void pivotRechts() throws Exception {
		Integer[] a = { 20, 54, 28, 32, 5, 24, 39, 14, 1, 15 };
		Integer[] atest = { 1, 5, 14, 15, 20, 24, 28, 32, 39, 54 };
		Sortieren sort = new Sortieren();
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.RECHTS);
		assertTrue("Pivotrechts Fehler", Arrays.equals(a, atest));

		Integer[] b = { 54, 20 };
		Integer[] btest = { 20, 54 };
		sort.quicksort(b, 0, b.length - 1, Pivotsuchverfahren.RECHTS);
		assertTrue("Pivotrechts Fehler", Arrays.equals(b, btest));

		Integer[] c = { 6, 20, 65, 100 };
		Integer[] ctest = { 6, 20, 65, 100 };
		sort.quicksort(c, 0, c.length - 1, Pivotsuchverfahren.RECHTS);
		assertTrue("Pivotrechts Fehler", Arrays.equals(c, ctest));

	}

	@Test
	public void pivotMedian() throws Exception {
		Integer[] a = { 20, 54, 28, 31, 5, 24, 39, 14, 1, 15 };
		Integer[] atest = { 1, 5, 14, 15, 20, 24, 28, 31, 39, 54 };
		Sortieren sort = new Sortieren();
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.MEDIAN);
		assertTrue("Pivotmdeian Fehler", Arrays.equals(a, atest));

		Integer[] b = { 54, 20 };
		Integer[] btest = { 20, 54 };
		sort.quicksort(b, 0, b.length - 1, Pivotsuchverfahren.MEDIAN);
		assertTrue("Pivotmdeian Fehler", Arrays.equals(b, btest));

		Integer[] c = { 6, 20, 65, 100 };
		Integer[] ctest = { 6, 20, 65, 100 };
		sort.quicksort(c, 0, c.length - 1, Pivotsuchverfahren.MEDIAN);
		assertTrue("Pivotmdeian Fehler", Arrays.equals(c, ctest));
	}

	@Test
	public void pivotZufaellig() throws Exception {
		Integer[] a = { 20, 54, 28, 31, 5, 24, 39, 14, 1, 15 };
		Integer[] atest = { 1, 5, 14, 15, 20, 24, 28, 31, 39, 54 };
		Sortieren sort = new Sortieren();
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.ZUFAELLIG);
		assertTrue("Pivotzufaellig Fehler", Arrays.equals(a, atest));

		Integer[] b = { 54, 20 };
		Integer[] btest = { 20, 54 };
		sort.quicksort(b, 0, b.length - 1, Pivotsuchverfahren.ZUFAELLIG);
		assertTrue("Pivotzufaellig Fehler", Arrays.equals(b, btest));

		Integer[] c = { 6, 20, 65, 100 };
		Integer[] ctest = { 6, 20, 65, 100 };
		sort.quicksort(c, 0, c.length - 1, Pivotsuchverfahren.ZUFAELLIG);
		assertTrue("Pivotzufaellig Fehler", Arrays.equals(c, ctest));
	}

	@Test
	public void einElement() throws Exception {
		Integer[] a = { 20 };
		Integer[] atest = { 20 };
		Sortieren sort = new Sortieren();
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.ZUFAELLIG);
		assertTrue("ein Element Fehler zufall", Arrays.equals(a, atest));
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.MEDIAN);
		assertTrue("ein Element Fehler median", Arrays.equals(a, atest));
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.RECHTS);
		assertTrue("ein Element Fehler rechts", Arrays.equals(a, atest));
	}

	@Test
	public void keinElement() throws Exception {
		Integer[] a = {};
		Integer[] atest = {};
		Sortieren sort = new Sortieren();
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.ZUFAELLIG);
		assertTrue("ein Element Fehler zufall", Arrays.equals(a, atest));
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.MEDIAN);
		assertTrue("ein Element Fehler median", Arrays.equals(a, atest));
		sort.quicksort(a, 0, a.length - 1, Pivotsuchverfahren.RECHTS);
		assertTrue("ein Element Fehler rechts", Arrays.equals(a, atest));
	}

	@Test
	public void leereListe() {
		Sortieren sort = new Sortieren();
		try {
			sort.quicksort(null, 0, 0, Pivotsuchverfahren.ZUFAELLIG);
			assertTrue("Fehler: Es wurde keine Exception geworfen!", false);
		} catch (Exception e) {
			// Alles richtig
		}
	}

}
