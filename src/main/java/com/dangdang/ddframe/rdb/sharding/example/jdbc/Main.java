/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.example.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangdang.ddframe.rdb.sharding.example.jdbc.entity.Order;
import com.dangdang.ddframe.rdb.sharding.example.jdbc.service.OrderService;

// CHECKSTYLE:OFF
@Service
@Transactional
public class Main {
	
	static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
    @SuppressWarnings("resource")
	public static void main(final String[] args) throws Exception {
        // CHECKSTYLE:ON
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:META-INF/mybatisContext.xml");
        final OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.clear();
        //orderService.fooService();
        //orderService.select();
        //[order_id: 1, user_id: 10, status: UPDATED, order_id: 1, user_id: 11, status: UPDATED]
        /*orderService.clear();
        try {
            orderService.fooServiceWithFailure();
        } catch (final IllegalArgumentException e) {
            System.out.println("roll back");
        }
        //[]
        orderService.select();*/
        
        /*int taskSize = 5; 
        // 创建一个线程池 
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		// 创建多个有返回值的任务
		List<Future> list = new ArrayList<Future>();
		for (int i = 1; i <= taskSize; i++) {
			Callable c = new MyCallable(i,orderService);
			// 执行任务并获取Future对象
			Future f = pool.submit(c);
			// System.out.println(">>>" + f.get().toString());
			//list.add(f);
		}
		// 关闭线程池
		pool.shutdown();
		*/
        
       
        for(int i = 1;i <= 1000;i++){
        	final int orderId = i;
            cachedThreadPool.execute(new Runnable() {
				public void run() {
					long startTime = System.currentTimeMillis();
					Order criteria = new Order();
		            criteria.setUserId(10);
		            criteria.setOrderId(orderId);
		            criteria.setStatus("INSERT" + orderId);
		            orderService.addOrder(criteria);
		            long endTime = System.currentTimeMillis();
		    	    System.out.println(orderId + "===total=" + (endTime - startTime) + "====startTime=" + startTime + "===endTime=" + endTime);
				}
			});
        }
      
        
		/*for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(index * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(index);
				}
			});
		} */
    }
}

class MyCallable implements Callable<Object> {
	
	private int taskNum;
	
	private OrderService orderService;

	MyCallable(int taskNum,OrderService orderService) {
		this.taskNum = taskNum;
		this.orderService = orderService;
	}

	public Object call() throws Exception {
		System.out.println(">>>" + taskNum + "任务启动");
		Date dateTmp1 = new Date();
		Thread.sleep(1000);
		Date dateTmp2 = new Date();
		long time = dateTmp2.getTime() - dateTmp1.getTime();
		System.out.println(">>>" + taskNum + "任务终止");
		return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
	}
}
