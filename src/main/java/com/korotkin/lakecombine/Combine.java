/**
 * 
 * The MIT License (MIT) | Copyright (c) 2015 Korotkin <info@korotkin.co.il>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.korotkin.lakecombine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.korotkin.lakecombine.common.CombineProcess;
import com.korotkin.lakecombine.common.LakeData;

public class Combine {
	public static void run(CombineConfig config) {

		ExecutorService executor = Executors.newFixedThreadPool(config.getThreadPoolSize());

		LakeData<?> data = config.getLakeData();

		synchronized (data) {
			while (data.hasNext()) {

				try {
					CombineProcess newInst = config.getCombineProcess().newInstance();

					// set chunk of data
					newInst.setData(data.next());

					// set worker
					Runnable worker = newInst;

					// execute worker
					executor.execute(worker);

				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			executor.shutdown();
		}
	}

}
