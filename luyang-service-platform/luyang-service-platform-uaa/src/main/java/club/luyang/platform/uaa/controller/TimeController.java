package club.luyang.platform.uaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lu_yang
 */
@RestController
public class TimeController {

	@GetMapping("/time")
	public String retrieveTime() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTime = LocalTime.now();
		return dateTimeFormatter.format(localTime);
	}
}
