package com.spring.mysql.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aerospike.client.Key;
import com.aerospike.client.command.Buffer;



@RestController
@RequestMapping("/test")
public class Controller {

	@Autowired
	private StateMachine<String, String> stateMachine;
	 @Autowired
	    public ServerRepository serverRepository;

	 @GetMapping("create")
	    public Server create() {
		 
		 Server server=new Server();
		 LocalDateTime myObj = LocalDateTime.now();
		 
		 Key key=new Key("test","test",myObj.toString());
		 server.setKey( Buffer.bytesToHexString(key.digest));
		 server.setName("New");
		 server.setRam(50);
		 server.setFreeMemory(50);
		 serverRepository.save(server);
		 return server;	 
	 }
	 
	 
	    @GetMapping("server")
	     public List<Server> getAllServers() {

	        List<Server> servers = 
	        (List<Server>) serverRepository.findAll();
	               
	        return servers;
	     }
	    
	    @GetMapping("allocate/{size}")
	    @Transactional(isolation=Isolation.SERIALIZABLE)
	     public Server free(@PathVariable int size ) {

	       Server server= serverRepository.findByFreeMemoryGreaterThanEqual(size); 
	       if(server!=null) {
	       server.setFreeMemory(server.getFreeMemory()-size);
	       serverRepository.save(server); 
	       
	       }else {
	    	   
	    	   stateMachine.start();
	       	stateMachine.sendEvent("wait");
	       if(stateMachine.getState().getId().toString()=="active") {
	    	  	  server=new Server();
	 	  		 LocalDateTime myObj = LocalDateTime.now(); 
	 	  		 Key key=new Key("test","test",myObj.toString());
	 	  		 server.setKey( Buffer.bytesToHexString(key.digest));
	 	  		 server.setName("New");
	 	  		 server.setRam(100);
	 	  		 server.setFreeMemory(100-size);
	 	  		 serverRepository.save(server);    
	 	  		 
	       }
      
	       }
	        return server;
	     }
	 
	 
    @GetMapping()
    public String gg() {

    	stateMachine.start();
    	stateMachine.sendEvent("wait");
    if(stateMachine.getState().getId().toString()=="active") {
    	
    	return "Active State";
    }
    	return "False";
    }
	
	
	
}
