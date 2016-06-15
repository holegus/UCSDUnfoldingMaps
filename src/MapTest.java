
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class MapTest extends PApplet{
	
	private static final long serialVersionUID = 1L;
	private UnfoldingMap map;
	
	public void setup() {
		size(1024, 800, OPENGL);

		map = new UnfoldingMap(this, 100, 50, 900, 700, new Google.GoogleMapProvider());
				
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
	}
	
	public void draw(){
		
		map.draw();
		drawButtons();
	}

	private void drawButtons(){
		
		fill(255,255,255);
		rect(120,70,25,25);
		fill(125,125,125);
		rect(120,120,25,25);
	}
	
	public void mouseReleased(){
		if(mouseX > 120 && mouseX < 145 && mouseY > 70 && mouseY < 95) {
			background(255,255,255);
		} else if (mouseX > 120 && mouseX < 145 && mouseY > 120 && mouseY < 145) {
			background(125,125,125);
		}
		
	}
}