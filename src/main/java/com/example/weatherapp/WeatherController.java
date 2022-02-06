package com.example.weatherapp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {
	
	@Autowired
    private WeatherService weatherService;
	

	
	
	@GetMapping("/")
	public String getIndex(Model model) {
	
		    Request request= new Request();
		    request.setZipCode("90210");
		    model.addAttribute("request", request);
		    return "index";
	}
	@PostMapping("/")
	public String postIndex(Request request, Model model) {
		
	   try { Response data = weatherService.getForecast(request);
	    model.addAttribute("data", data);
	    List<Request> recentSearches = weatherService.getRecentSeaches();
	    model.addAttribute("recent",recentSearches);
	    String url=weatherService.backgroundImg(data);
	    model.addAttribute("url", url);
	   }
	   catch(Exception e) { }
	  return "index";
	}
}
