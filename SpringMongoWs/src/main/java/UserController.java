

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	
	//GET
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public ResponseEntity< List<User> > listAllUsers() {
		
		return new ResponseEntity< List<User> >
		(userService.listAllUsers(), HttpStatus.OK);
		
	}

	@RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String id) {
		
		User user = userService.getById(id);
		
		return user == null ? 
				new ResponseEntity<User>(HttpStatus.NOT_FOUND) : 
					new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//POST
	
	@RequestMapping(value="/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody User user) {

		try {
			userService.save(user);
			return new ResponseEntity<String>(HttpStatus.CREATED);

		 } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//REMOVE
	@RequestMapping(value="/user/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<User> removeUser(@RequestBody String id){
		User user = userService.getById(id);
		userService.remove(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	
	//PUT
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody String id) {
		User user = userService.getById(id);
		userService.update(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
    }	
	
	public UserService getUserService() {
		return userService;
	}
}
