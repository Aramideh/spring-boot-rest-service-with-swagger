package controllers;

import java.util.concurrent.atomic.AtomicLong;


import com.example.restservice.services.Greeting;
import com.example.restservice.services.MathFun;
import com.example.restservice.services.MathPair;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/GSS/Rest/Consumer/Test")
@ApiOperation(value = "GSS Consumer Test Services")
public class WebServiceController implements WebMvcConfigurer {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setCaseSensitive(false);
		configurer.setPathMatcher(matcher);
		WebMvcConfigurer.super.configurePathMatch(configurer);
	}

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}


	@RequestMapping(value = "/json/multiply" , method = RequestMethod.GET , produces = "application/json")
	@ApiOperation(value = "Multiply x and y")
	public double multiply(
			@ApiParam(name = "x" , defaultValue = "0" , value = " First Param" , required =  true ,example = "5")
			@RequestBody MathPair mathPair
	) {
		MathFun mathFun =new MathFun(counter.incrementAndGet());
		return mathFun.multiply( mathPair);
	}


	@RequestMapping(value = "/httpParams/multiply" , method = RequestMethod.GET , produces = "application/json")
	@ApiOperation(value = "Multiply x and y")
	public double multiply(
			@RequestParam(name = "x" , required = true , defaultValue = "0") Double x,
			@RequestParam(name = "y", required = true ,defaultValue = "0") Double y
	) {
		MathFun mathFun =new MathFun(counter.incrementAndGet());
		return mathFun.multiply( x,y );
	}
}
