package com.example.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.ToString;

public class Exercises {
	
	static class RabbitData{
		String parent;
		String child;
		
		RabbitData(String parent, String child){
			this.parent = parent;
			this.child = child;
		}
	}
	
	static List<RabbitData> l = new ArrayList<>();
	
	
	
	public static void main(String[] args) {
		
		
		l.add(new RabbitData("jose", "jeff"));
		l.add(new RabbitData("jose", "john"));
		l.add(new RabbitData("jeff", "totoy"));
		
		
		
		
		
		
		
		
		
		
		
		
//		System.err.println(Arrays.toString(howSum(8, new int[] {3,4,2,1})));
		
//		System.err.println(canSum(8, new int[] {5,6,9}));
//		
//		System.err.println(fib(8, new HashMap<>()));
//		String str = "c";
		
//		System.err.println(canConstruct("abc", new String[] {"a", "b", "d"}));
		
//		System.err.println(str.substring(str.indexOf("a") + 1, str.length()));
		
		
		
//		
//		Map<String, List<String>> m = new HashMap<>();
//		
//		List<String> p = new ArrayList<>();
//		p.add("joseph");
//		p.add("flor");
//		
//		m.put("john", p);
//		m.put("jeffrey", p);
//		
//		List<String> bp = new ArrayList<>();
//		p.add("jeffrey");
//		p.add("bebe");
//		
//		m.put("totoy", bp);
//		
//		
//		System.err.println(ancestors(m, "john"));
		
		
		
		String[] names = {"jeff", "bb", "joseph", "flor", "maximo", "r", "papabb", "mamabb"};
		Node r = sbst(names);
		
		traverse(r);
		
	}
	
	static void traverse (Node root){ // Each child of a tree is a root of its subtree.
	    
		 
		if (root.l != null){
	        traverse (root.l);
	    }
		System.out.println(root.name);
	    if (root.r != null){
	        traverse (root.r);
	    }
	    
	}
	
	
//    public static List<String> ancestors(Map<String, List<String>> genealogy, String person) {
//        List<String> result = new ArrayList<>();
//        if (genealogy.containsKey(person)) {
//            List<String> parents = genealogy.get(person);
//            result.addAll(parent s);
//            for (String parent : parents) {
//                result.addAll(ancestors(genealogy, parent));
//            }
//        }
//        return result;
//    }
//    
    public static List<String> ancestors(Map<String, List<String>> genealogy, String person) {
    	
       
        if (genealogy.containsKey(person)) {
        	List<String> parents = genealogy.get(person);
        	
        	List<String> result = parents;
            for (String parent : parents) {
            	result.addAll(ancestors(genealogy, parent));
//                result.addAll(ancestors(genealogy, parent));
            }
            return result;
        }
        return new ArrayList<>();
    }

    
    
    
    
    
    static class Node{
    	String name;
    	
    	Node l;
    	Node r;
    	
    	Node(String name){
    		this.name = name;
    	}
    	
    	@Override
    	public String toString() {
    		// TODO Auto-generated method stub
    		return this.name;
    	}
    }
    
    
    static Node sbst(String[] names) {
    	
    	if(names == null || names.length == 0) {
    		return null;
    	}
    	return buildTree(names, 0, names.length - 1);
    }
    
    
	private static Node buildTree(String[] names, int i, int length) {
		// TODO Auto-generated method stub
		
		if(i > length) {
			return null;
		}
		
		int mid = i + (length - i) / 2;
		Node n = new Node(names[mid]);
		n.l = buildTree(names, i, mid - 1);
		n.r = buildTree(names, mid + 1, length);
		
		return n;
		
	}


	static boolean canConstruct(String t, String[] arr) {
		if(t.length() == 0) {
			return true;
		}
		
		
		for(String s : arr) {
			String rem = t.substring(t.indexOf(s) + 1, t.length());
			if(rem.length() == 0) {
				return true;
			}
			
			
			
			
			
		}
		
		return false;
	}
	
	
	
	static int[] howSum(int t, int[] arr) {
		if(t == 0) {
			return new int[] {};
		}
		if(t < 0) {
			return null;
		}
		
		
		for(int n : arr) {
			int rem = t - n;
			int[] remResult = howSum(rem, arr);
			if(remResult != null) {
				int[] res = new int[remResult.length + 1];
				for(int i = 0; i < remResult.length; i++) {
					res[i] = remResult[i];
				}
				res[remResult.length] = n;
				return res;
			}
		}
		
		
		return null;
	}
	
	static boolean canSum(int t, int[] arr) {
		if(t == 0) {
			return true;		
		}
		if(t < 0) {
			return false;
		}
		
		
		for(int n  : arr) {
			int rem = t - n;
			
			boolean remResult = canSum(rem, arr);
			if(remResult) {
				return true;
			}
			
		}
		
		
		return false;
	}

	
	static int fib(int n, Map<Integer, Integer> map) {
		if(n == 0 || n == 1) {
			return n;
		}
		
		if(map.containsKey(n)) {
			return map.get(n);
		}
		
		int r = fib(n - 1, map) + fib(n - 2, map);
		map.put(n, r);
		return r;
	}

}


//1 1 2 3 5 8 12 