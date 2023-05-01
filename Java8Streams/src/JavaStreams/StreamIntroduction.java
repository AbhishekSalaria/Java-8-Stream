package JavaStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamIntroduction {
	
	public static void main(String args[]) throws IOException {
		
		// IntStream, forEach
		IntStream.range(1, 10).forEach(System.out::print);
		System.out.println("");
		
		// IntStream, Skip, forEach
		IntStream.range(1, 10).skip(5).forEach(x -> System.out.print(x + " "));
		System.out.println("");
		
		// Stream.of, sorted, ifPresent and findFirst
		Stream.of("third","second","first")
			.sorted()
			.findFirst()
			.ifPresent(x -> System.out.print(x + " "));
		System.out.println("");
		
		
		// Arrays.stream, sorted, filter, forEach
		String [] names = {"third","second","first","second_second"};
		Arrays.stream(names)
			.filter(x -> x.startsWith("s"))
			.sorted()
			.forEach(x -> System.out.print(x + " "));
		System.out.println("");
		
		
		// Arrays.stream, average, map, ifPresent
		Arrays.stream(new int[] {1,2,3,4,5})
			.map(x -> x * 2)
			.average()
			.ifPresent(System.out::println);
		
		// Stream from List, filter, map, stream, forEach
		List<String> order = Arrays.asList("third","second","first","second_second");
		order.stream()
		.map(x -> x.toLowerCase()) // second way .map(String::toLowerCase)
		.filter(x -> x.startsWith("s"))
		.forEach(x -> System.out.print(x + " "));
		System.out.println("");
		
		// reduce, Stream.of
		double total = Stream.of(1.3,5.5,6.3)
				.reduce(0.0, (Double a, Double b) -> a + b);
		System.out.println(total);
		
		// summary Statistics
		IntSummaryStatistics summary = IntStream.of(1,2,3,4,5,6,7)
				.summaryStatistics();
		System.out.println(summary);
		
		// Stream from text file
		Stream<String> numbers = Files.lines(Paths.get("/Users/abhisheksalaria/Documents/workspace-spring-tool-suite-4-4.18.0.RELEASE/Java8Streams/src/JavaStreams/numbers.txt"));
		
		// #1
		numbers.sorted()
		.filter(x -> x.length() > 5)
		.forEach(x -> System.out.print(x + " "));
		System.out.println();
		numbers.close();
		
		// #2
		Stream<String> nums = Files.lines(Paths.get("/Users/abhisheksalaria/Documents/workspace-spring-tool-suite-4-4.18.0.RELEASE/Java8Streams/src/JavaStreams/numbers.txt"));
		List<String> filtered = nums.filter(x -> x.contains("ird"))
		.collect(Collectors.toList());
		
		filtered.forEach(x -> System.out.print(x + " "));
		System.out.println("");
		nums.close();
		
		// Stream rows from csv and count
		Stream<String> rows = Files.lines(Paths.get("/Users/abhisheksalaria/Documents/workspace-spring-tool-suite-4-4.18.0.RELEASE/Java8Streams/src/JavaStreams/data.txt"));
		int count = (int) rows.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.count();
		System.out.println("RowCount = " + count);
		rows.close();
		
		// Parsing Rows
		Stream<String> parseRows = Files.lines(Paths.get("/Users/abhisheksalaria/Documents/workspace-spring-tool-suite-4-4.18.0.RELEASE/Java8Streams/src/JavaStreams/data.txt"));
		parseRows.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.filter(x -> Integer.parseInt(x[1]) > 20)
				.forEach(x -> System.out.println(x[0] + " " + x[1] + " "+ x[2]));
		
		parseRows.close();		
		
		// Parsing Rows and Storing in HashMap
		Stream<String> parseAndStore = Files.lines(Paths.get("/Users/abhisheksalaria/Documents/workspace-spring-tool-suite-4-4.18.0.RELEASE/Java8Streams/src/JavaStreams/data.txt"));
		
		Map<String,Integer> map = new HashMap<>();
		
		map = parseAndStore.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.filter(x -> Integer.parseInt(x[1]) > 20)
				.collect(Collectors.toMap(
						x -> x[0],
						x -> Integer.parseInt(x[1])));
		parseAndStore.close();		
		for(String key: map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
	}

}
