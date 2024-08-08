package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace;
import pojo.Location;

public class TestDataBuild {
	
	public Addplace addPlacePayload(String name, String address, String language) {
		
		Addplace p= new Addplace();
		p.setAddress(address);
		p.setAccuracy(50);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("+61457347877");
		p.setWebsite("http://google.com");
		
		List<String> myList= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383484);
		l.setLng(33.427334);
		p.setLocation(l);
		
		return p;
	}

	public String deleteplace_paylod(String placeid) {
		
		return "{\n    \"place_id\":\""+placeid+"\"\n}";
	}
}
