package StreamsInterviewQuestions;

import StreamsInterviewQuestions.model.Employee;
import StreamsInterviewQuestions.model.Patient;

import java.util.*;
import java.util.stream.Collectors;

public class Executor {
    public static void main(String[] args) {

        List<Patient> patients = Arrays.asList(
                new Patient("P1",20,"Corona",18000),
                new Patient("P2",26,"Fever",6000),
                new Patient("P3",29,"Corona",8000),
                new Patient("P4",23,"Corona",12000)
        );

        //Print average amount for patients having disease as Corona
        double averageAmount = patients.stream().filter(
                patient -> patient.getDisease().equals("Corona")
        ).collect(Collectors.averagingInt(Patient::getAmount));

        System.out.println(averageAmount);

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("ABC",30,"Female","HR"));
        employees.add(new Employee("PQR",25,"Male","IT"));
        employees.add(new Employee("LMN",30,"Male","HR"));
        employees.add(new Employee("XYZ",28,"Female","IT"));

        //Print all the distinct departments
        List<String> depts = employees.stream()
                .map(e -> e.getDepartment())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(depts);

        //Count of Employees Dept wise
        Map<String,Long> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));

        System.out.println(map);

        //Average age of male and female employees
        Map<String,Double> avgage = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingInt(Employee::getAge)));

        System.out.println(avgage);

        List<Integer> list = Arrays.asList(1,4,5,6,22,3,90,89,2,1,3,4,55,6);

        // Sum of All Numbers
        //way1
        list.stream().reduce(
                (a,b) -> a+b
        ).ifPresent(System.out::println);

        //way2
        int sum = list.stream().mapToInt(e->e).sum();
        System.out.println(sum);

        //Average of All Numbers
        double average = list.stream().mapToInt(e->e).average().getAsDouble();
        System.out.println(average);

        //Get Squared Average
        double squaredAverage = list.stream().map(x -> x*x)
                .mapToInt(x->x).average().getAsDouble();
        System.out.println(squaredAverage);

        //GetSquare Average for numbers whose squared values are less than 500
        double squaredAveragelt500 = list.stream().map(x -> x*x)
                .filter(x -> x > 500)
                .mapToInt(x->x).average().getAsDouble();
        System.out.println(squaredAveragelt500);

        //Odd and Even numbers out of the list
        list.stream().filter(x -> x%2 == 0).forEach(x -> System.out.print(x+" ")); // Even
        System.out.println("");
        list.stream().filter(x -> x%2 == 1).forEach(x -> System.out.print(x+" ")); // Odd
        System.out.println("");

        //Numbers Staring with 5
        List<Integer> integers = list.stream().map(e -> String.valueOf(e)).filter(
                e -> e.startsWith("5") || e.startsWith("-5")
        ).map(e -> Integer.parseInt(e)).collect(Collectors.toList()); //or Integer::valueOf

        for(Integer integer:integers) {
            System.out.print(integer + " ");
        }
        System.out.println("");

        //Find Duplicate Numbers
        Set<Integer> frequencygt1 = list.stream().filter(e-> Collections.frequency(list,e) > 1).collect(Collectors.toSet());
        System.out.println(frequencygt1);

        Set<Integer> set = new HashSet<>();
        list.stream().filter(e -> !set.add(e)).forEach(e -> System.out.print(e+" "));

        //Max and Min Number
        list.stream().max(Comparator.comparing(Integer::valueOf)).ifPresent(System.out::println);
        int min = list.stream().min(Comparator.comparing(Integer::valueOf)).get();
        System.out.println(min);

        //Sort the number
        //Increasing
        List<Integer> sorted = list.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted);
        //Decreasing
        List<Integer> rsorted = list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        System.out.println(rsorted);

        // Get Sum of first 5 numbers
        //way1
        int total = list.stream().limit(5).mapToInt(e -> e).sum();
        System.out.println(total);

        //way2
        list.stream().limit(5).reduce((a,b) -> a+b).ifPresent(System.out::println);

        //skip the first five numbers and sum rest
        //way1
        list.stream().skip(5).reduce((a,b) -> a+b).ifPresent(System.out::println);

        //way2
        int skiptotal = list.stream().skip(5).mapToInt(e->e).sum();
        System.out.println(skiptotal);

        //get second highest and second lowest number
        //second lowest
        list.stream().sorted().distinct()
                .skip(1).findFirst().ifPresent(System.out::println);

        //second highest
        list.stream().sorted(Collections.reverseOrder()).distinct()
                .skip(1).findFirst().ifPresent(System.out::println);
    }
}
