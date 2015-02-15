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

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.korotkin.lakecombine.example.CombineProccessExample;
import com.korotkin.lakecombine.example.LakeDataExample;

public class Application {
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Application.class);
		
		logger.info("Starting application");
		
		logger.debug("Create configuration");
		CombineConfig combineConfig = new CombineConfig();

		logger.debug("Assign combine process");
		combineConfig.setCombineProcess(CombineProccessExample.class);
		
		logger.debug("Assign lake data");
		combineConfig.setLakeData(new LakeDataExample(3, 3));
		
		logger.info("Run combine");
		Combine.run(combineConfig);
		logger.info("Cobine done");

	}

}
