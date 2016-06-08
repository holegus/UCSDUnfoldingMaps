
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet{

	UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	private void shadeCountries() {
		for (Marker marker : countryMarkers) {
				String countryId = marker.getId();
				if (lifeExpByCountry.containsKey(countryId)) {
					float lifeExp = lifeExpByCountry.get(countryId);
					int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
					marker.setColor(color(255-colorLevel, 100, colorLevel));
				}
				else {
					marker.setColor(color(150,150,150));
				}
		}
	}
	
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName)
	{
		Map<String, Float> lifeExpMap = new HashMap<String, Float>();
		String regex = ",(?!\\s)";
		String[] rows = loadStrings(fileName);
		for (String row : rows) {
			String[] columns = Pattern.compile(regex).split(row);
			if (!"..".equals(columns[4])) {
				float value = Float.parseFloat(columns[4]);
				lifeExpMap.put(columns[3], value);
				//System.out.println("Country: " + columns[2] + " value: " + columns[4]);
			}
			else
			{
				
			}
		}
		return lifeExpMap;		
	}
	
	
	public void setup()
	{
		size(1000,800, OPENGL);
		map = new UnfoldingMap(this,50,50,900,700,new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		lifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBankModule31.csv");
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		shadeCountries();
		
	}
	
	public void draw()
	{
		map.draw();
	}
}
