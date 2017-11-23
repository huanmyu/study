//package com.huanyu.study.example;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.springframework.lang.Nullable;
//
//public class SreamExample {
//	private static List<Artist> allArtists = new ArrayList<>();
//	private static List<Track> tracks;
//
//	public SreamExample() {
//	}
//
//	static {
//		Artist a = new Artist("huanhuan", null, "shanghai");
//		Artist b = new Artist("huanyu", null, "hangzhou");
//		allArtists.add(a);
//		allArtists.add(b);
//
//		tracks = Arrays.asList(new Track("Bakai", 524),
//							   new Track("Furs", 378),
//							   new Track("Time Was", 451));
//	}
//
//	public static void main(String[] args) {
//		// for 循环
//		int count = 0;
//		for (Artist artist : allArtists) {
//			if (artist.getOrigin() == "shanghai") {
//				count ++;
//			}
//		}
//		System.out.println(count); // 1
//
//		// 迭代器（外部迭代， for循环的工作原理）
//		int countIterator = 0;
//		Iterator<Artist> iterator = allArtists.iterator();
//		while(iterator.hasNext()) {
//			Artist artist = iterator.next();
//			if (artist.getOrigin() == "shanghai") {
//				countIterator ++;
//			}
//		}
//		System.out.println(countIterator); // 1
//
//		// 内部迭代
//		long countStream = allArtists.stream().filter(artist -> {
//			if (artist.getOrigin() == "shanghai") {
//				return true;
//			}
//			return false;
//		}).count();
//		System.out.println(countStream); // 1
//
//		// collect(toList())方法由Stream里的值生成一个列表
//		List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
//		assertEquals(Arrays.asList("a", "b", "c"), collected); // 成功
//
//		// map操作                                               此处Lambda表达式需要是Function接口的一个实例
//		List<String> collectedMap = Stream.of("a", "b", "c").map(string -> string.toUpperCase()).collect(Collectors.toList());
//		assertEquals(Arrays.asList("A", "B", "C"), collectedMap); // 成功
//
//		// filter操作
//		List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1").filter(value -> Character.isDigit(value.charAt(0))).collect(Collectors.toList());
//		assertEquals(Arrays.asList("1abc"), beginningWithNumbers); // 成功
//
//		// flatMap方法可用Stream替换值，然后将多个Stream连接成一个Stream
//		List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
//		assertEquals(Arrays.asList(1, 2, 3, 4), together); // 成功
//
//		// max and min
//		Track shortestTrack = tracks.stream().min(Comparator.comparing(track -> track.getLength())).get();
//		assertEquals(tracks.get(1), shortestTrack); // 成功
//	}
//
//	public static class Artist {
//		private String name;
//
//		@Nullable //A common Spring annotation to declare that annotated elements can be null under some circumstance.
//		private ArrayList<String> members;
//
//		private String origin;
//
//		public Artist(String name, ArrayList<String> members, String origin) {
//			super();
//			this.setName(name);
//			this.members = members;
//			this.setOrigin(origin);
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public String getOrigin() {
//			return origin;
//		}
//
//		public void setOrigin(String origin) {
//			this.origin = origin;
//		}
//	}
//
//	public static class Track {
//		private String name;
//
//		private int length;
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public int getLength() {
//			return length;
//		}
//
//		public void setLength(int length) {
//			this.length = length;
//		}
//
//		public Track(String name, int length) {
//			super();
//			this.name = name;
//			this.length = length;
//		}
//
//		public Track() {
//			super();
//		}
//
//	}
//}
