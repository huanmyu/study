package com.huanyu.study.example;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


public class LambdaExample {

	public LambdaExample() {
	}

	public static void main(String[] args) {
		Runnable helloworld = () -> System.out.println("hello world");
		helloworld.run(); // hello world
		
		Predicate<Integer> atLeast5 = x -> x > 5;
		System.out.println(atLeast5.test(2)); // false
		
		Consumer<Integer> consumerValue = x -> System.out.println(x);
		consumerValue.accept(2); // 2
		
		Function<Integer, String> func = x -> x+"func";
		System.out.println(func.apply(4)); // 4func
		
		Supplier<Integer> supplier = () -> 4;
		System.out.println(supplier.get()); // 4
		
		UnaryOperator<Integer> unary = x -> x + 2;
		System.out.println(unary.apply(5)); // 7
		
		BinaryOperator<Long> add = (x, y) -> x + y;
		System.out.println(add.apply(1L, 2L)); // 3
		
		ThreadLocal<Integer> local =  ThreadLocal.withInitial(() -> 10);
		System.out.println(local.get()); // 10
	}

}
