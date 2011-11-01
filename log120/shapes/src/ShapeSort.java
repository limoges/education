
enum SortType { 
  SEQUENCE,     
  AREA,         
  TYPE,         
  DISTANCE,     
  WIDTH,        
  HEIGHT,       
  ORIGINAL      
};              

public class ShapeSort {

	public static void sort(LinkedList<Shape> list,
      SortType sortType, boolean ascending) {
		boolean sorted = false;
		int multiplicator = -1 + (ascending? 1 : 0)*2;
		int listSize = list.size();
		while(!sorted) {
			sorted = true;
			for(int i=0; i < listSize; i++) {
				if(i < listSize-1) {
					int currentElementValue =
            getSpecifiedGetter(list.get(i), sortType) * multiplicator;
					int nextElementValue =
            getSpecifiedGetter(list.get(i+1), sortType) * multiplicator;
					if(currentElementValue > nextElementValue) {
						list.set(i+1, list.set(i, list.get(i+1)));
						sorted = false;
					}
				}
			}
		}
	}
	
	public static int getSpecifiedGetter(Shape shape, SortType index) {
		if(index == SortType.SEQUENCE)
			return shape.getId();
		else if(index == SortType.AREA)
			return (int) shape.getArea();
		else if(index == SortType.TYPE)
			return shape.getShapeTypeValue();
		else if(index == SortType.DISTANCE)
			return (int) shape.getDistance();
		else if(index == SortType.WIDTH)
			return shape.getWidth();
		else if(index == SortType.HEIGHT)
			return shape.getHeight();
		else if(index == SortType.ORIGINAL)
			return shape.getOrderOfArrival();	
		return 0;
	}
}
