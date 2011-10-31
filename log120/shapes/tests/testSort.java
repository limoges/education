
public class testSort {
	private LinkedList<Forme> list = new LinkedList<Forme>();
	
	public testSort() {
		for(int i=0; i<10; i++)
			list.add(new Forme((int)(Math.random()*100), (int)(Math.random()*100), (int)(Math.random()*100)));
	}
		
	public void sort(int sortType, boolean ascending) {
		//@sortType 1 = par grandeur 2 = par largeur 3 = par no. de Sequence
		boolean sorted = false;
		int multiplicator = -1 + (ascending? 1 : 0)*2;
		int listSize = list.size();
		while(!sorted) {
			sorted = true;
			for(int i=0; i < listSize; i++) {
				if(i < listSize-1) {
					int currentElementValue = getSpecifiedGetter(list.get(i), sortType)*multiplicator;
					int nextElementValue = getSpecifiedGetter(list.get(i+1), sortType)*multiplicator;
					if(currentElementValue > nextElementValue) {
						list.set(i+1, list.set(i, list.get(i+1)));
						sorted = false;
					}
				}
			}
		}
	}
	
	//Je trouve que avec les int pour trouver le get c'est pas clean
	public int getSpecifiedGetter(Forme forme, int index) {
		if(index == 1)
			return forme.getGrandeur();
		else if(index == 2)
			return forme.getLargeur();
		else if(index == 3)
			return forme.getNoSeq();
		
		return 0;
	}
	
	public static void main(String[] argv) {
		testSort t = new testSort();
		for(int i =0; i< t.list.size(); i++)
			System.out.println(t.list.get(i).getNoSeq());
		
		System.out.println("-------------------");
		
		t.sort(3,false);
		
		for(int i =0; i< t.list.size(); i++)
			System.out.println(t.list.get(i).getNoSeq());
	}
}
