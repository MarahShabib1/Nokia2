package com.spring.mysql.api;

import javax.annotation.Generated;


import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.aerospike.mapping.Field;
import org.springframework.data.annotation.Id;

import com.aerospike.client.Key;

@Document
public class Server {

	@Id
	private String key;

		public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
		@Field
	    private String name;
		@Field
	    private int RAM;
	    @Field
	    private int memoryAllocated;
	    
	    
	    
	    public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getRAM() {
			return RAM;
		}
		public void setRAM(int rAM) {
			RAM = rAM;
		}
		public int getMemoryAllocated() {
			return memoryAllocated;
		}
		public void setMemoryAllocated(int memoryAllocated) {
			this.memoryAllocated = memoryAllocated;
		}
	
	
	
	
}
