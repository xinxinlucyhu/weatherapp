package com.example.weatherapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

	@Value("${api_key}")
	private String apiKey;
	@Autowired
	private ZipCodeRepository zipCodeRepository;
	


	public Response getForecast(Request request) {
		String url = "https://api.openweathermap.org/data/2.5/weather?zip=" + request.getZipCode() + "&units=imperial&appid="
				+ apiKey;
		
		zipCodeRepository.save(request);
		RestTemplate restTemplate = new RestTemplate();
		Response response;

		try {
			response =
					restTemplate.getForObject(url, Response.class);
		} catch (HttpClientErrorException ex) {
			ex.printStackTrace();
			response = new Response();
			response.setName("error");
			return response;
		}
		return response;
	}
	public List<Request> getRecentSeaches(){
	    return zipCodeRepository.findTop10ByOrderByIdDesc();
	}
	
	public String backgroundImg(Response response) {
		
		String icon=(response.getWeather()).get(0).get("icon");
		String url="";
		System.out.println(icon);

		if(icon.equals("01d")||icon.equals("01n")) {
			url="https://media.istockphoto.com/photos/blue-sky-background-and-white-clouds-soft-focus-and-copy-space-picture-id1128410927?s=612x612";
		}
			else if(icon.equals("02d")||icon.equals("02n")) {
			url="https://media.istockphoto.com/photos/few-little-fluffy-white-clouds-in-blue-sky-picture-id492866927?s=612x612";
		}
			else if(icon.equals("03d")||icon.equals("03n")) {
		    url="https://media.istockphoto.com/photos/cirrocumulus-and-altocumulus-clouds-over-alaska-picture-id1290172613?s=612x612";
		}
			else if(icon.equals("04d")||icon.equals("04n")) {
		    url="https://images.unsplash.com/photo-1533736405784-798e2e103a3f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2342&q=80";
		}
			else if(icon.equals("09d")||icon.equals("09n")) {
			url="https://unsplash.com/photos/mYOea-xnu-k";
		}
			else if(icon.equals("10d")||icon.equals("10n")) {
			url="https://images.unsplash.com/photo-1518803194621-27188ba362c9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fHJhaW58ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60";
		}
			else if(icon.equals("11d")||icon.equals("11n")) {
			url="https://media.istockphoto.com/photos/lightnings-during-summer-storm-picture-id1187133109?b=1&k=20&m=1187133109&s=170667a&w=0&h=WRBATuoEhOQKsttCFjN6iOYTPjWCCr-zAV1BrrXWixA=";
		}
			else if(icon.equals("13d")||icon.equals("13n")) {
			url="https://media.istockphoto.com/photos/three-snowflakes-isolated-on-white-background-picture-id1284095399?b=1&k=20&m=1284095399&s=170667a&w=0&h=FDUXFInsKkI83Oz4u1zMff87VYqFyNxyBD7PLAyAL6k=";
		}
			else if(icon.equals("50d")||icon.equals("50n")) {
			url="https://media.istockphoto.com/photos/fog-and-clouds-on-mountain-picture-id1160438555?b=1&k=20&m=1160438555&s=170667a&w=0&h=K-BiNam5YFkh9Bk8uAizMj3Q0juIpQoqizl_UJs8qwQ=";
		}
		
		return url;
	}
	
}
