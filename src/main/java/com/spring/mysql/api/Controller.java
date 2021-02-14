package com.spring.mysql.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
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
		// Buffer.bytesToHexString(key.digest);
		 server.setKey( Buffer.bytesToHexString(key.digest));
		 server.setName("New");
		 server.setRAM(50);
		 server.setMemoryAllocated(0);
		 serverRepository.save(server);
		 return server;	 
	 }
	 
	 
	    @GetMapping("server")
	     public List<Server> getAllServers() {

	        List<Server> servers = new ArrayList<>();
	        serverRepository.findAll()
	                .forEach(servers::add);
	        return servers;
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
