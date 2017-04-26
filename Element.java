package adp5;

public class Element<T> {

	protected T element;
	public int key;

	public Element(T element, int key) {
		this.element = element;
		this.key = key;
	}

	public T getElement(){
		return this.element;
	}

}
