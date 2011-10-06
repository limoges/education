
public class ElipseShape extends Shapes {
	private int centreX, centreY, rayonH, rayonV;
	
	public ElipseShape(int nseq, int centreX, int centreY, int rayonH, int rayonV) {
		super(nseq);
		this.centreX = centreX;
		this.centreY = centreY;
		this.rayonH = rayonH;
		this.rayonV = rayonV;
	}

	public int getCentreX() {
		return centreX;
	}
	
	public boolean isCircle() {
		if(this.getRayonH() == this.getRayonV())
			return true;
		return false;
	}
	
	public void setCentreX(int centreX) {
		this.centreX = centreX;
	}

	public int getCentreY() {
		return centreY;
	}

	public void setCentreY(int centreY) {
		this.centreY = centreY;
	}

	public int getRayonH() {
		return rayonH;
	}

	public void setRayonH(int rayonH) {
		this.rayonH = rayonH;
	}
	
	public int getRayonV() {
		return rayonV;
	}

	public void setRayonV(int rayonV) {
		this.rayonV = rayonV;
	}
}
