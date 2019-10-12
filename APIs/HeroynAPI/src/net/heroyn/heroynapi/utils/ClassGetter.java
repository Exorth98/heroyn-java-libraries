package net.heroyn.heroynapi.utils;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.plugin.java.JavaPlugin;


public class ClassGetter {
	
	
	   public static ArrayList<Class<?>> getClassesForPackage(JavaPlugin plugin, String pkgname)
	   {
	     ArrayList<Class<?>> classes = new ArrayList<>();
	     
	 
	 
	     CodeSource src = plugin.getClass().getProtectionDomain().getCodeSource();
	     if (src != null) {
	       URL resource = src.getLocation();
	       resource.getPath();
	       try {
			processJarfile(resource, pkgname, classes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	     }
	     ArrayList<String> names = new ArrayList<>();
	     ArrayList<Class<?>> classi = new ArrayList<>();
	     for (Class<?> classy : classes) {
	       names.add(classy.getSimpleName());
	       classi.add(classy);
	     }
	     classes.clear();
	     Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
	     for (String s : names) {
	       for (Class<?> classy : classi)
	         if (classy.getSimpleName().equals(s)) {
	           classes.add(classy);
	           break;
	         }
	     }
	     return classes;
	  }
	   
	   private static Class<?> loadClass(String className) {
	     try {
	       return Class.forName(className);
	     } catch (ClassNotFoundException e) {
	       throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
	     }
	   }
	   
	   @SuppressWarnings({ "unused", "resource", "null" })
	private static void processJarfile(URL resource, String pkgname, ArrayList<Class<?>> classes) throws IOException
	   {
	     String relPath = pkgname.replace('.', '/');
	     String resPath = resource.getPath().replace("%20", " ");
	     String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
	     JarFile jarfile = new JarFile(jarPath);
	     Enumeration<JarEntry> entries = jarfile.entries();
	     while (entries.hasMoreElements()) {
	       JarEntry entry = (JarEntry)entries.nextElement();
	       String entryName = entry.getName();
	       String className = null;
	       if ((entryName.endsWith(".class")) && (entryName.startsWith(relPath)) && 
	         (entryName.length() > relPath.length() + "/".length())) {
	         className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
	       }
	       if (className != null) {
	         classes.add(loadClass(className));
	       }
	     }
	   }
}