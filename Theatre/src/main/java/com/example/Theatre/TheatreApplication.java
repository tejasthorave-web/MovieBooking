package com.example.Theatre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Complete microservice for managing theatres, screens, and seat layouts in BookMyShow-style architecture.
@SpringBootApplication
public class TheatreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheatreApplication.class, args);
	}

}
/*## APIs


### Theatre
```
POST /theatres
GET /theatres
GET /theatres/{id}
```
### Screen
```
POST /screens
GET /screens/theatre/{theatreId}
```



## Integration Flow


movie-service → fetch shows


theatre-service → provides theatre + screen + seat metadata


booking-service → reserves seats*/
