package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

class Main {

	private  String fileName;
	private HashMap<String, String> map;

	public Main(String fileName) {
		this.map = new HashMap<>();
		if(fileName.equalsIgnoreCase("production")){
			this.fileName = "C:\\Users\\victo\\Desktop\\InputOutput\\src\\com\\company\\config.txt";
		}
		if(fileName.equalsIgnoreCase("staging")){
			this.fileName = "C:\\Users\\victo\\Desktop\\InputOutput\\src\\com\\company\\config.txt.staging";
		}
		if(fileName.equalsIgnoreCase("development")){
			this.fileName = "C:\\Users\\victo\\Desktop\\InputOutput\\src\\com\\company\\config.txt.dev";
		}

	}



	public void ReadInput() {
		String line;

		FileReader fin = null;
		BufferedReader br = null;
		InputStreamReader isr = null;
		int b;


		try {
			fin = new FileReader(this.fileName);
			br = new BufferedReader(fin);
			while ((line = br.readLine()) != null) {

				if (!line.isEmpty() && !line.contains("[application]")) {

					if (line.contains("name") && !line.contains("dbname") || line.contains("port") || line.contains("context")) {
						line = "aplication." + line;

					}
					String[] newArr = line.split("=");
					System.out.println(line);

					System.out.println(Arrays.toString(newArr));
					map.put(newArr[0], newArr[1]);

				}


			}
			System.out.println(map);

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				if (fin != null)
					fin.close();
			} catch (Exception e) {
				System.err.println("Exception: " + e.getMessage());
			}
		}
	}
	private HashMap<String, String> getMap() {
		return map;
	}
	public String getValues(String key){
		return getMap().get(key);
	}


	public static void main(String[] args){
		String name;
		if(args.length == 0){
			name = "production";
		}else{
			name = args[0];
		}
			Main me = new Main(name);
			me.ReadInput();
			String s = me.getValues("dbname");
		  System.out.println(s);

		}

}
